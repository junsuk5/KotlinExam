package com.example.kotlinexam.survivalcoding13


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.kotlinexam.R
import com.example.kotlinexam.alert
import com.example.kotlinexam.logd
import com.example.kotlinexam.survivalcoding13.db.Todo
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.coroutines.*
import java.util.*

class AddFragment : Fragment(), CoroutineScope by CoroutineScope(Dispatchers.IO) {
    private var date: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(requireActivity())
            .get(TodoListViewModel::class.java)

        date = calendarView.date

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val timeInMillis = calendar.timeInMillis
            date = timeInMillis
        }

        done_button.setOnClickListener {
            requireContext().alert("test", "test") {
                positiveButton("yes") {
                    logd("yes")
                }
                negativeButton("no") {
                    logd("no")
                }
            }
            val title = todo_edit.text.toString()

            logd("시작")

            launch {
                delay(2000)
                viewModel.insert(Todo(title, date))

                launch(Dispatchers.Main) {
                    logd("끝")
                    findNavController().popBackStack()
                }
            }

        }
    }

    override fun onDestroyView() {
        cancel()
        super.onDestroyView()
    }

}
