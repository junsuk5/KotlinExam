package com.example.kotlinexam.item15


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexam.R
import com.example.kotlinexam.databinding.ItemFilterImageBinding
import kotlinx.android.synthetic.main.fragment_filters.*

class FiltersFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

        val adapter = FilterAdapter {

        }

        recyclerView.adapter = adapter

        viewModel.bitmap.observe(this, Observer {bitmap ->
            adapter.items = viewModel.filterList
                .map { filter ->
                    FilterItem(bitmap, filter)
                }
        })
    }

    class FilterAdapter(private val clickListener: (item: FilterItem) -> Unit) :
        RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

        var items = listOf<FilterItem>()

        class FilterViewHolder(val binding: ItemFilterImageBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filter_image, parent, false)
            val viewHolder = FilterViewHolder(ItemFilterImageBinding.bind(view))
            view.setOnClickListener {
                clickListener.invoke(items[viewHolder.adapterPosition])
            }
            return viewHolder
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
            holder.binding.filterItem = items[position]
        }
    }
}
