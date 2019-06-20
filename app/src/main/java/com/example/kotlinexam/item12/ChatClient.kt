package com.example.kotlinexam.item12

import com.example.kotlinexam.item12.models.MsgInfo
import com.google.gson.Gson
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket
import java.net.UnknownHostException

class ChatClient(
    private val nickName: String,
    private val onReadMessage: (MsgInfo) -> Unit
) {

    private var mSocket: Socket? = null
    private var mName: String? = null

    lateinit var writer: ClientWrite

    fun connect() {
        try {
            mSocket = Socket(SERVER_HOST, SERVER_PORT)
            val clientRead = ClientRead()
            clientRead.start()

            writer = ClientWrite(nickName)
        } catch (e: UnknownHostException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    internal inner class ClientRead : Thread() {
        private var mInputStream: DataInputStream? = null

        override fun run() {
            try {
                mInputStream = DataInputStream(mSocket!!.getInputStream())
            } catch (e: IOException) {
                e.printStackTrace()
            }

            super.run()
            try {
                // 계속 듣기만
                while (mInputStream != null) {
                    // json 파싱
                    val json = mInputStream!!.readUTF()
                    try {
                        val msgInfo = Gson().fromJson(json, MsgInfo::class.java)
                        println(json)
                        onReadMessage.invoke(msgInfo)
                    } catch (e: Exception) {
                        println(e.localizedMessage)
                    }

                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                // 접속 종료시
                mSocket = null
            }
        }
    }

    inner class ClientWrite(nickName: String) {

        private var mOutputStream: DataOutputStream? = null

        init {
            try {
                   mName = nickName
                mOutputStream = DataOutputStream(mSocket!!.getOutputStream())
                mOutputStream!!.writeUTF(nickName)
                mOutputStream!!.flush()
                println("id : " + nickName + "접속 완료")
            } catch (e: IOException) {
                e.printStackTrace()
                println("writeUTF IOException")
            }

        }

        fun write(message: String) {

            try {
                val msgInfo = MsgInfo(nickName, message, System.currentTimeMillis())
                mOutputStream!!.writeUTF(Gson().toJson(msgInfo))
                mOutputStream!!.flush()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    companion object {
        private val SERVER_HOST = "192.168.0.222"
        private val SERVER_PORT = 5000
    }
}