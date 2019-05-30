package com.example.kotlinexam.survivalcoding13


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexam.R
import com.example.kotlinexam.databinding.ItemTodoBinding
import com.example.kotlinexam.survivalcoding13.db.Todo
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(requireActivity())
            .get(TodoListViewModel::class.java)

        val adapter = TodoAdapter(
            clickListener = {
                // 수정화면
                val action = MainFragmentDirections.actionMainFragmentToEditFragment(it)
                findNavController().navigate(action)
            },
            deleteClickListener = {
                launch {
                    viewModel.delete(it)
                    viewModel.getAll()
                }
            },
            updateClickListener = {
                // 수정화면
                val action = MainFragmentDirections.actionMainFragmentToEditFragment(it)
                findNavController().navigate(action)
            }
        )


        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.items.observe(this, Observer {
            adapter.submitList(it)
        })

        add_button.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }

        launch {
            viewModel.getAll()
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}

class TodoAdapter(
    private val clickListener: (todo: Todo) -> Unit,
    private val deleteClickListener: (todo: Todo) -> Unit,
    private val updateClickListener: (todo: Todo) -> Unit
) :
    ListAdapter<Todo, TodoAdapter.TodoViewHolder>(DiffCallback()) {

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        val viewHolder = TodoViewHolder(ItemTodoBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(getItem(viewHolder.adapterPosition))
        }
        viewHolder.binding.moreImageView.setOnClickListener {
            val popupMenu = PopupMenu(parent.context, it)
            popupMenu.inflate(R.menu.popup_todo)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener { menuItem ->
                val item = getItem(viewHolder.adapterPosition)
                when (menuItem.itemId) {
                    R.id.action_delete -> {
                        deleteClickListener.invoke(item)
                        true
                    }
                    R.id.action_update -> {
                        updateClickListener.invoke(item)
                        true
                    }
                    else -> false
                }
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.todo = getItem(position)
    }
}
