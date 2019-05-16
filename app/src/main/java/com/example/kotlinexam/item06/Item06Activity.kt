package com.example.kotlinexam.item06

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinexam.R
import kotlinx.android.synthetic.main.activity_item06.*

class Item06Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item06)

        registerForContextMenu(here_text)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.item06, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_item01 -> {
//                toast("아이템 01")
                return true
            }
            R.id.action_item02 -> {
//                toast("아이템 02")
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

}
