package com.example.finez_activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Activity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        // Set the Toolbar as the action bar
        setSupportActionBar(findViewById(R.id.tbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            R.id.action_calculate -> {
                startActivity(Intent(this, Activity1::class.java))
                true
            }
            R.id.action_exit -> {
                showExitConfirmationDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                finishAffinity() // Close all activities of the app
            }
            .setNegativeButton("No", null)
            .create()
            .show()
    }
}
