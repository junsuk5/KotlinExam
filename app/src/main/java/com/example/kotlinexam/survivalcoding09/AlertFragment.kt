package com.example.kotlinexam.survivalcoding09


import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

// "권한이 필요한 이유"
// "사진 정보를 얻으려면 외부 저장소 권한이 필수입니다."
class AlertFragment(
    private val title: String,
    private val message: String,
    private val init: AlertDialogBuilder.() -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialogBuilder(requireContext(), title, message)
        init.invoke(builder)
        return builder.create()
    }

    class AlertDialogBuilder(context: Context, title: String, message: String) {
        private val builder: AlertDialog.Builder by lazy {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
        }

        fun positiveButton(
            text: String, callback: DialogInterface.() -> Unit
        ) {
            builder.setPositiveButton(text) { dialog, _ ->
                callback.invoke(dialog)
            }
        }

        fun negativeButton(text: String, callback: DialogInterface.() -> Unit) {
            builder.setNegativeButton(text) { dialog, _ ->
                callback.invoke(dialog)
            }
        }

        fun show() {
            builder.show()
        }

        fun create(): AlertDialog = builder.create()
    }
}
