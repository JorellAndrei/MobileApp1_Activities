package com.example.finez_activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserProfile : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val username = intent.getStringExtra("USERNAME")
        val password = intent.getStringExtra("PASSWORD")
        val email = intent.getStringExtra("EMAIL")
        val name = intent.getStringExtra("NAME")

        val usernameTextView: TextView = findViewById(R.id.username)
        val passwordTextView: TextView = findViewById(R.id.password)
        val emailTextView: TextView = findViewById(R.id.email)
        val nameTextView: TextView = findViewById(R.id.name)


        usernameTextView.text = username
        passwordTextView.text = password
        emailTextView.text = email
        nameTextView.text = name

        findViewById<ImageView>(R.id.returnback2).setOnClickListener {
            finish()
        }
    }
}
