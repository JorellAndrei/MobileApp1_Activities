package com.example.finez_activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Activity4 : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signupRedirectText: TextView

    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        loginButton = findViewById(R.id.login_button)
        usernameEditText = findViewById(R.id.login_username)
        passwordEditText = findViewById(R.id.login_password)
        signupRedirectText = findViewById(R.id.signupRedirectText)

        // Add predefined user
        saveUserInfo("Jorell Andrei", "jorellandrei23@gmail.com", "JorellAndrei23", "Admin")

        signupRedirectText.setOnClickListener {
            Toast.makeText(this, "Sign Up Clicked", Toast.LENGTH_SHORT).show()
            showSignUpDialog()
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Check login credentials
            if (isValidLogin(username, password)) {
                val user = getUserInfo(username)
                if (user != null) {
                    val intent = Intent(this, homepage::class.java).apply {
                        putExtra("USERNAME", user.username)
                        putExtra("PASSWORD", user.password)
                        putExtra("EMAIL", user.email)
                        putExtra("NAME", user.name)
                    }
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Logged in as $username", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showSignUpDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_signup, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.signup_name)
        val emailEditText = dialogView.findViewById<EditText>(R.id.signup_email)
        val usernameEditText = dialogView.findViewById<EditText>(R.id.signup_username)
        val passwordEditText = dialogView.findViewById<EditText>(R.id.signup_password)
        val signupButton = dialogView.findViewById<Button>(R.id.signup_button)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Sign Up")
            .setView(dialogView)
            .create()

        signupButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                // Check if username already exists
                if (isExistingUsername(username)) {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
                } else {
                    // Save the user info
                    saveUserInfo(name, email, username, password)
                    Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    private fun isValidLogin(username: String, password: String): Boolean {
        // Validate login credentials
        val user = getUserInfo(username)
        return user?.password == password
    }

    private fun getUserInfo(username: String): User? {
        // Retrieve user info
        return users.find { it.username == username }
    }

    private fun isExistingUsername(username: String): Boolean {
        // Check if username already exists
        return users.any { it.username == username }
    }

    private fun saveUserInfo(name: String, email: String, username: String, password: String) {
        // Save user info
        val user = User(name, email, username, password)
        users.add(user)
    }

    data class User(val name: String, val email: String, val username: String, val password: String)
}
