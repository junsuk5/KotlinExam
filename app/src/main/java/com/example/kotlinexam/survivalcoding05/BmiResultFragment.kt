package com.example.kotlinexam.survivalcoding05


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.kotlinexam.R
import kotlinx.android.synthetic.main.fragment_bmi_result.*

class BmiResultFragment : Fragment() {
    val args: BmiResultFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bmi_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bmi = args.weight / Math.pow(args.height / 100.0, 2.0)

        when {
            bmi >= 35 -> result_text.text = "고도 비만"
            bmi >= 30 -> result_text.text = "2단계 비만"
            bmi >= 25 -> result_text.text = "1단계 비만"
            bmi >= 23 -> result_text.text = "과체중"
            bmi >= 18.5 -> result_text.text = "정상"
            else -> result_text.text = "저체중"
        }

        Log.d("BmiResultFragment", "${args.height}, ${args.weight}")
    }


}
