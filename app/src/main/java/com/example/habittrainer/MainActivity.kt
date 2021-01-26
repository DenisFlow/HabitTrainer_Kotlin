package com.example.habittrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habittrainer.db.HabitDbTable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.single_card.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = HabitsAdapter(HabitDbTable(this).readAllHabits())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_habit) {
            val intent = Intent(this, CreateHabitActivity::class.java)
            switch(CreateHabitActivity::class.java)
        }
        return true
    }

    private fun switch(c: Class<*>) {
        val intent = Intent(this, c)
        startActivity(intent)
    }
}