package com.example.finez_activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.roundToInt


class Activity1 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Hide the keyboard
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)

        val edt1 = findViewById<EditText>(R.id.editTextNumberDecimal)
        val edt2 = findViewById<EditText>(R.id.editTextNumberDecimal2)

        val addBtn = findViewById<Button>(R.id.button)
        val minusBtn = findViewById<Button>(R.id.button2)
        val multiplyBtn = findViewById<Button>(R.id.button3)
        val divideBtn = findViewById<Button>(R.id.button4)

        val res = findViewById<TextView>(R.id.textView)

        addBtn.setOnClickListener {
            val num1 = edt1.text.toString().toDouble()
            val num2 = edt2.text.toString().toDouble()
            val result = num1 + num2

            res.text = "${Math.round(result * 100.0) / 100.0}"
        }

        minusBtn.setOnClickListener {
            val num1 = edt1.text.toString().toDouble()
            val num2 = edt2.text.toString().toDouble()
            val result = num1 - num2

            res.text = "${Math.round(result * 100.0) / 100.0}"
        }

        multiplyBtn.setOnClickListener {
            val num1 = edt1.text.toString().toDouble()
            val num2 = edt2.text.toString().toDouble()
            val result = num1 * num2

            res.text = "${Math.round(result * 100.0) / 100.0}"
        }

        divideBtn.setOnClickListener {
            val num1 = edt1.text.toString().toDouble()
            val num2 = edt2.text.toString().toDouble()
            val result = num1 / num2

            res.text = "${Math.round(result * 100.0) / 100.0}"
        }

        findViewById<Button>(R.id.returnback).setOnClickListener {
            finish()
        }

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