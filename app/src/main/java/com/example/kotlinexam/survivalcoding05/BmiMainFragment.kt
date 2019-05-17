package com.example.kotlinexam.survivalcoding05


import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.kotlinexam.R

class BmiMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi_main, container, false)
        val height_edit = view.findViewById<EditText>(R.id.height_edit)
        val weight_edit = view.findViewById<EditText>(R.id.weight_edit)

        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val height = pref.getFloat("height", 0f)
        val weight = pref.getFloat("weight", 0f)

        if (height != 0f && weight != 0f) {
            height_edit.setText(height.toString())
            weight_edit.setText(weight.toString())
        }

        val button = view.findViewById<Button>(R.id.result_button)
        button.setOnClickListener {
            val action = BmiMainFragmentDirections.actionBmiMainFragmentToBmiResultFragment(
                height_edit.text.toString().toFloat(),
                weight_edit.text.toString().toFloat()
            )
            it.findNavController()
                .navigate(action)
        }

        return view
    }


}
