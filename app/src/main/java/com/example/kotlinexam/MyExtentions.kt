package com.example.kotlinexam

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun Any.logd(text: String) {
    Log.d(this::class.java.simpleName, text)
}

fun Context.alert(
    message: String,
    title: String,
    init: AlertDialogBuilder.() -> Unit
) {
    val dialog = AlertDialogBuilder(this, title, message)
    init.invoke(dialog)
    dialog.show()
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
}
