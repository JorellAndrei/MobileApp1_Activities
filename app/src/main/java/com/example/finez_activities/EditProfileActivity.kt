package com.example.finez_activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Initialize views
        usernameEditText = findViewById(R.id.newusername)
        passwordEditText = findViewById(R.id.newpass)
        emailEditText = findViewById(R.id.newemail)
        nameEditText = findViewById(R.id.newname)
        saveButton = findViewById(R.id.doneEdit)
        cancelButton = findViewById(R.id.cancelEdit)

        // Get data passed from homepage
        val username = intent.getStringExtra("USERNAME").toString()
        val password = intent.getStringExtra("PASSWORD").toString()
        val email = intent.getStringExtra("EMAIL").toString()
        val name = intent.getStringExtra("NAME").toString()

        // Populate EditText fields with current user data
        usernameEditText.setText(username)
        passwordEditText.setText(password)
        emailEditText.setText(email)
        nameEditText.setText(name)

        // Save button click listener
        saveButton.setOnClickListener {
            showConfirmationDialog("Save Changes", "Are you sure you want to save changes?") {
                // Retrieve updated data from EditText fields
                val updatedUsername = usernameEditText.text.toString()
                val updatedPassword = passwordEditText.text.toString()
                val updatedEmail = emailEditText.text.toString()
                val updatedName = nameEditText.text.toString()

                // Example: Update data in database or shared preferences
                // For simplicity, we'll just show a Toast message
                Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()

                // Navigate back to homepage with updated data
                val intent = Intent(this, homepage::class.java).apply {
                    putExtra("USERNAME", updatedUsername)
                    putExtra("PASSWORD", updatedPassword)
                    putExtra("EMAIL", updatedEmail)
                    putExtra("NAME", updatedName)
                }
                startActivity(intent)
                finish()
            }
        }

        // Cancel button click listener
        cancelButton.setOnClickListener {
            showConfirmationDialog("Cancel Editing", "Are you sure you want to cancel editing?") {
                // Display a Toast message or handle other actions
                Toast.makeText(this, "Edit canceled", Toast.LENGTH_SHORT).show()

                // Optionally, navigate back to the previous activity
                finish()
            }
        }
    }

    private fun showConfirmationDialog(title: String, message: String, onConfirm: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                // Call the lambda function passed as parameter
                onConfirm.invoke()
                dialogInterface.dismiss()
            }
            .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            .show()
    }
}
