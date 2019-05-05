package com.example.kotlinexam

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kotlinexam.databinding.ItemSubjectBinding
import com.example.kotlinexam.item01.Item01Activity
import com.example.kotlinexam.item02.Item02Activity
import com.example.kotlinexam.item03.Item03Activity
import com.example.kotlinexam.item03java.Item03JavaActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SubjectAdapter { subject ->
            val intent = Intent(this, subject.clazz)
            startActivity(intent)
        }
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val subjects = arrayListOf<Subject>()
        subjects.add(Subject("프래그먼트에서 액티비티에 값 전달", Item01Activity::class.java))
        subjects.add(Subject("Pagination", Item02Activity::class.java))
        subjects.add(Subject("SearchView", Item03Activity::class.java))
        subjects.add(Subject("SearchView - Java", Item03JavaActivity::class.java))

        adapter.items = subjects
        adapter.notifyDataSetChanged()
    }

}

data class Subject(val title: String, val clazz: Class<out Activity>)

class SubjectAdapter(private val clickListener: (person: Subject) -> Unit) :
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {
    var items = arrayListOf<Subject>()

    class SubjectViewHolder(val binding: ItemSubjectBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subject, parent, false)
        val viewHolder = SubjectViewHolder(ItemSubjectBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.binding.subject = items[position]
    }
}