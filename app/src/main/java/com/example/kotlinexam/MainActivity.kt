package com.example.kotlinexam

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinexam.databinding.ItemSubjectBinding
import com.example.kotlinexam.item01.Item01Activity
import com.example.kotlinexam.item02.Item02Activity
import com.example.kotlinexam.item03.Item03Activity
import com.example.kotlinexam.item03java.Item03JavaActivity
import com.example.kotlinexam.item04.Item04Activity
import com.example.kotlinexam.item04java.Item04JavaActivity
import com.example.kotlinexam.item05.Item05Activity
import com.example.kotlinexam.item06.Item06Activity
import com.example.kotlinexam.item07java.Item07JavaActivity
import com.example.kotlinexam.item08java.Item08JavaActivity
import com.example.kotlinexam.item09java.Item09JavaActivity
import com.example.kotlinexam.item10.Item10Activity
import com.example.kotlinexam.item11.Item11Activity
import com.example.kotlinexam.survivalcoding05.BmiCalculatorMainActivity
import com.example.kotlinexam.survivalcoding06.StopWatchMainActivity
import com.example.kotlinexam.survivalcoding09.MyGalleryActivity
import com.example.kotlinexam.survivalcoding11.TorchMainActivity
import com.example.kotlinexam.survivalcoding13.TodoListMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SubjectAdapter { subject ->
            val intent = Intent(this, subject.clazz)
            startActivity(intent)
        }
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

        val subjects = arrayListOf<Subject>()
        subjects.add(Subject("프래그먼트에서 액티비티에 값 전달", Item01Activity::class.java))
        subjects.add(Subject("Pagination", Item02Activity::class.java))
        subjects.add(Subject("SearchView", Item03Activity::class.java))
        subjects.add(Subject("SearchView - Java", Item03JavaActivity::class.java))
        subjects.add(Subject("WorkManager", Item04Activity::class.java))
        subjects.add(Subject("WorkManager - Java", Item04JavaActivity::class.java))
        subjects.add(Subject("RxJava", Item05Activity::class.java))
        subjects.add(Subject("ContextMenu + RecyclerView", Item06Activity::class.java))
        subjects.add(Subject("Undo", Item07JavaActivity::class.java))
        subjects.add(Subject("터치", Item08JavaActivity::class.java))
        subjects.add(Subject("센서", Item09JavaActivity::class.java))
        subjects.add(Subject("생존코딩: 5장 비만도 계산기", BmiCalculatorMainActivity::class.java))
        subjects.add(Subject("스탑워치", StopWatchMainActivity::class.java))
        subjects.add(Subject("구글캘린더 조작", Item10Activity::class.java))
        subjects.add(Subject("생존코딩: 9장 전자액자", MyGalleryActivity::class.java))
        subjects.add(Subject("생존코딩: 11장 손전등(AppWidget)", TorchMainActivity::class.java))
        subjects.add(Subject("생존코딩: 13장 TodoList", TodoListMainActivity::class.java))
        subjects.add(Subject("Retrofit + Coroutine", Item11Activity::class.java))

        adapter.items = subjects
        adapter.notifyDataSetChanged()
    }

}

data class Subject(val title: String, val clazz: Class<out Activity>)

class SubjectAdapter(private val clickListener: (person: Subject) -> Unit) :
    androidx.recyclerview.widget.RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {
    var items = arrayListOf<Subject>()

    class SubjectViewHolder(val binding: ItemSubjectBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

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