//package com.example.finez_activities
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//
//class EditItemActivity : AppCompatActivity() {
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_edit_item)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        val prevBtn = findViewById<FloatingActionButton>(R.id.prevAct)
//        prevBtn.setOnClickListener{
//            val intent = Intent(this, Activity2::class.java)
//            startActivity(intent)
//        }
//
//        findViewById<Button>(R.id.editItemButton).setOnClickListener {
//            val id = findViewById<EditText>(R.id.itemId).text.toString().toInt()
//            val name = findViewById<EditText>(R.id.itemName).text.toString()
//            val price = findViewById<EditText>(R.id.itemPrice).text.toString().toDouble()
//
//            // Use the singleton instance of ItemManagement
////            ItemManagement.editItem(id, name, price)
//
//            Toast.makeText(this, "Item added successfully!", Toast.LENGTH_SHORT).show()
//            finish()
//        }
//
//    }
//}