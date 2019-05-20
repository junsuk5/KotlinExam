package com.example.kotlinexam.survivalcoding09


import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AlertFragment(private val onClickListener: () -> Unit) : DialogFragment() {
//    interface OnClickLister {
//        fun onClick()
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("권한이 필요한 이유")
        builder.setMessage("사진 정보를 얻으려면 외부 저장소 권한이 필수입니다.")
        builder.setPositiveButton("수락") { _, _ ->
            onClickListener.invoke()
        }
        builder.setNegativeButton("거부", null)
        return builder.create()
    }

}
