package com.example.finez_activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Activities : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_activities)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val act5 = findViewById<Button>(R.id.act5)
        act5.setOnClickListener{
            val intent = Intent(this, Activity1::class.java)
            startActivity(intent)
        }

        val act6 = findViewById<Button>(R.id.act6)
        act6.setOnClickListener{
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }

        val act7 = findViewById<Button>(R.id.act7)
        act7.setOnClickListener{
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }

        val act8 = findViewById<Button>(R.id.act8)
        act8.setOnClickListener{
            val intent = Intent(this, Activity4::class.java)
            startActivity(intent)
        }
    }
}