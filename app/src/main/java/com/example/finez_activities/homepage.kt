package com.example.finez_activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class homepage : AppCompatActivity() {

    private lateinit var username: String
    private lateinit var password: String
    private lateinit var email: String
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        // Get user data from intent
        username = intent.getStringExtra("USERNAME").toString()
        password = intent.getStringExtra("PASSWORD").toString()
        email = intent.getStringExtra("EMAIL").toString()
        name = intent.getStringExtra("NAME").toString()

        // Set up toolbar
        val toolbar: Toolbar = findViewById(R.id.tbar)
        setSupportActionBar(toolbar)

        // Display welcome message
        supportActionBar?.title = "$username"

        // Display user info or any other UI elements
        val userInfoTextView: TextView = findViewById(R.id.welcome_message)
        userInfoTextView.text = "Welcome to Mobile Development, $name!"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                showProfileDialog()
                true
            }
            R.id.action_settings -> {
                showEditDialog()
                true
            }
            R.id.action_logout -> {
                showLogoutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showProfileDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Profile")
            .setMessage("Are you sure you want to view your profile?")
            .setPositiveButton("Yes") { _, _ ->
                openProfile()
            }
            .setNegativeButton("No", null)
            .create()

        dialog.show()
    }

    private fun showEditDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Edit Profile")
            .setMessage("Are you sure you want to edit your profile?")
            .setPositiveButton("Yes") { _, _ ->
                openEdit()
            }
            .setNegativeButton("No", null)
            .create()

        dialog.show()
    }

    private fun showLogoutDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Yes") { _, _ ->
                logout()
            }
            .setNegativeButton("No", null)
            .create()

        dialog.show()
    }

    private fun openProfile() {
        // Implement opening the profile activity or fragment
        // Example: Navigate to ProfileActivity
        val intent = Intent(this, UserProfile::class.java).apply {
            putExtra("USERNAME", username)
            putExtra("PASSWORD", password)
            putExtra("EMAIL", email)
            putExtra("NAME", name)
        }
        startActivity(intent)
    }

    private fun openEdit() {
        val intent = Intent(this, EditProfileActivity::class.java).apply {
            putExtra("USERNAME", username)
            putExtra("PASSWORD", password)
            putExtra("EMAIL", email)
            putExtra("NAME", name)
        }
        startActivity(intent)
    }

    private fun logout() {
        // Display a Toast message for logging out
        Toast.makeText(this, "Goodbye! $name", Toast.LENGTH_SHORT).show()

        // Clear username and password to indicate logout state
        username = ""
        password = ""
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // Navigate back to login screen
        startActivity(Intent(this, Activity4::class.java))
        finish()
    }
}
