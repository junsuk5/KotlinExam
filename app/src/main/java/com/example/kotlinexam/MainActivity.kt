package com.example.kotlinexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kotlinexam.databinding.ItemPersonBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PersonAdapter { person ->
            toast(person.toString())
        }
        recycler_view.adapter = adapter

        val people = arrayListOf<Person>()
        for (i in 0..10) {
            people.add(Person("사람 $i", 20))
        }

        adapter.items = people
        adapter.notifyDataSetChanged()
    }

    private fun 문법연습() {
        val str = """
                안녕하세요.
                반갑습니다.
                ㅇㄹㅁ;ㅓㄴㅇㄹ ㅁㄴㅇ
                ㅁㄴㅇㄹ ㅇㄴ
            """.trimIndent()

        // Anko
        debug(str)

        val name = "오준석"
        val age = 39

        // 기존 방법
        debug("제 이름은 " + name + "이고, 나이는 " + age + "입니다")
        // 새로운 방법
        debug("제 이름은 ${name}이고, 나이는 ${age}입니다")

        // 1~5 까지 들어간 배열
        val numbers = arrayOf(1, 2, 3, 4, 5)
        // 1~5 까지 들어간 ArrayList
        val numberList = arrayListOf(1, 2, 3, 4, 5)
        // Map
        val hashMap = hashMapOf(
            "오준석" to "천재",
            "장혁재" to "다이어트중?"
        )


        val a = 10
        val b = 20
        val max = if (a > b) a else b       // int max = a > b ? a : b

        val x = 1
        when (x) {
            1 -> debug(x)
            2 -> debug(2 * x)
            in 3..5 -> debug("3~5")
            in 8..10 -> debug("8~10")
            else -> {
                debug("else")
                debug("else")
                debug("else")
            }
        }


        val numStr = when (x % 2) {
            0 -> "짝"
            else -> "홀"
        }

        // for(int i = 0; i < 10; i++)
        for (i in 0..9) {

        }

        var text: String? = null

        // 안전한 호출 ?.
        debug(text?.trim())
        // 강제 호출 !!     안 좋아!!!!!
        //        debug(text!!.trim())

        // 컬렉션
        // ArrayList
        val numList = arrayListOf(1, 2, 3, 4, 5)

        // for (int num : numList) {
        //      if (num % 2 == 0) {
        //          log(num + "번");
        //      }
        // }
        val filterd = numList
            .filter { it % 2 == 0 }
            .map { "${it}번" }

        info { filterd }
    }

    // public String isEven(int number) { }
    fun isEven(number: Int): String = when (number % 2) {
        0 -> "짝"
        else -> "홀"
    }
}

// 모델
data class Person(var name: String, var age: Int)

// Adapter
class PersonAdapter(val callback: (person: Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    var items = arrayListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        val holder = PersonViewHolder(ItemPersonBinding.bind(view))
        view.setOnClickListener {
            callback.invoke(items[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.person = items[position]
    }


    // ViewHolder
    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)

}

