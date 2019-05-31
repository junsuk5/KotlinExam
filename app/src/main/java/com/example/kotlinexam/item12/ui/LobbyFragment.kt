package com.example.kotlinexam.item12.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlinexam.R
import kotlinx.android.synthetic.main.fragment_lobby.*

class LobbyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lobby, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enter_button.setOnClickListener {
            val nickName = id_edit.text.toString()

            if (nickName.isNotEmpty()) {
                val action =
                    LobbyFragmentDirections
                        .actionLobbyFragmentToChatFragment(nickName)
                findNavController().navigate(action)
            }
        }
    }


}
