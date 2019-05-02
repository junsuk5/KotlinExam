package com.example.kotlinexam.item01


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinexam.R
import com.example.kotlinexam.databinding.ItemPersonBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PersonAdapter { person ->
            val intent = Intent(requireContext(), Item01TargetActivity::class.java)
            intent.putExtra("person", person)
            startActivity(intent)
        }
        recycler_view.adapter = adapter

        val people = arrayListOf<Person>()
        for (i in 0..10) {
            people.add(Person("사람 $i", 20))
        }

        adapter.items = people
        adapter.notifyDataSetChanged()
    }


}

// 모델
data class Person(var name: String, var age: Int) : Serializable

// Adapter
class PersonAdapter(val callback: (person: Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    var items = arrayListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        val holder = PersonViewHolder(ItemPersonBinding.bind(view))
        view.setOnClickListener {
            callback.invoke(items[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.person = items[position]
    }


    // ViewHolder
    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)

}