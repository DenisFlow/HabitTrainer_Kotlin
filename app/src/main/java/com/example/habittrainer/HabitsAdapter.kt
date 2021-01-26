package com.example.habittrainer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.single_card.*
import kotlinx.android.synthetic.main.single_card.view.*

class HabitsAdapter(val habits: List<Habit>) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {
    class HabitViewHolder(val iv: View) : RecyclerView.ViewHolder(iv)

    // Create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_card, parent, false)
        return HabitViewHolder(view)

    }

    override fun getItemCount() = habits.size

    // Specifies the contents for the shown habit
    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        if (holder != null) {
            val habit = habits[position]
            holder.iv.tv_title.text = habit.title
            holder.iv.tv_descriprion.text = habit.description
            holder.iv.iv_icon.setImageBitmap(habit.image)
        }
    }
}