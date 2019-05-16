package com.example.kotlinexam.survivalcoding05


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.kotlinexam.R

class BmiMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi_main, container, false)

        val button = view.findViewById<Button>(R.id.result_button)
        button.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_bmiMainFragment_to_bmiResultFragment)
        }

        return view
    }


}
