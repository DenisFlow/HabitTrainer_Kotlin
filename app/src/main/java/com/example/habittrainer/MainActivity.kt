package com.example.habittrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvDescriprion: TextView
    private var tvTitle: TextView? = null
    private lateinit var ivIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivIcon = findViewById<ImageView>(R.id.iv_icon)
        ivIcon.setImageResource(R.drawable.water)
        tvDescriprion = findViewById(R.id.tv_descriprion)
        tvDescriprion.text = getString(R.string.drink_water_description)
        tvTitle = findViewById(R.id.tv_title)
        tvTitle?.text = getString(R.string.drink_water)
    }
}