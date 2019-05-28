package com.example.kotlinexam.survivalcoding13


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlinexam.R
import com.example.kotlinexam.databinding.FragmentEditBinding
import kotlinx.android.synthetic.main.fragment_edit.*
import java.util.*

class EditFragment : Fragment() {
//    val viewModel: TodoListViewModel by lazy {
//        ViewModelProviders.of(requireActivity())
//            .get(TodoListViewModel::class.java)
//    }
    private var date: Long = 0

    val args: EditFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEditBinding.bind(view)

        val viewModel = ViewModelProviders.of(requireActivity())
            .get(TodoListViewModel::class.java)

        date = calendarView.date

        val todo = args.todo

        binding.todo = args.todo

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val timeInMillis = calendar.timeInMillis
            date = timeInMillis
        }

        done_button.setOnClickListener {
            val title = todo_edit.text.toString()

            todo.title = title
            todo.date = date

            viewModel.update(todo)
            findNavController().popBackStack()
        }

        delete_button.setOnClickListener {
            viewModel.delete(todo)
            findNavController().popBackStack()
        }
    }


}

class MyClick : View.OnClickListener {
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class MyClick2 : View.OnClickListener by MyClick() {

}