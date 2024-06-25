//package com.example.finez_activities
//
//import android.content.Intent
//import android.graphics.Color
//import android.graphics.Typeface
//import android.os.Bundle
//import android.text.InputType
//import android.widget.Button
//import android.widget.EditText
//import android.widget.RadioGroup
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.res.ResourcesCompat
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//
//class ViewItemActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_view_item)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        val prevBtn = findViewById<FloatingActionButton>(R.id.prevAct)
//        prevBtn.setOnClickListener {
//            val intent = Intent(this, Activity2::class.java)
//            startActivity(intent)
//        }
//
//        val itemManagement = ItemManagement
//
//        val searchOptionGroup = findViewById<RadioGroup>(R.id.searchOptionGroup)
//        val searchInput = findViewById<EditText>(R.id.searchInput)
//        val viewItemButton = findViewById<Button>(R.id.viewItemButton)
//        val itemDetailsTextView = findViewById<TextView>(R.id.itemDetailsTextView)
//
//
//        // Set OnCheckedChangeListener for the RadioGroup
//        searchOptionGroup.setOnCheckedChangeListener { _, checkedId ->
//            when (checkedId) {
//                R.id.searchById -> {
//                    searchInput.hint = "Enter Item ID"
//                    searchInput.inputType = InputType.TYPE_CLASS_NUMBER
//                }
//                R.id.searchByName -> {
//                    searchInput.hint = "Enter Item Name"
//                    searchInput.inputType = InputType.TYPE_CLASS_TEXT
//                }
//            }
//        }
//
//        viewItemButton.setOnClickListener {
//            val selectedOption = searchOptionGroup.checkedRadioButtonId
//            val query = searchInput.text.toString().trim()
//
//            if (query.isEmpty()) {
//                Toast.makeText(this, "Please enter a search query.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            when (selectedOption) {
//                R.id.searchById -> {
//                    val id = query.toIntOrNull()
//                    if (id != null) {
//                        val item = itemManagement.viewItem(id)
//                        if (item != null) {
//                            itemDetailsTextView.text = "VIEW ITEM INFORMATION \n\n Item ID: ${item.id} \n ${item.name} \n ₱${item.price}"
//                            itemDetailsTextView.visibility = TextView.VISIBLE
//                        } else {
//                            itemDetailsTextView.text = "Item with ID $id not found."
//                            itemDetailsTextView.visibility = TextView.VISIBLE
//                        }
//                    } else {
//                        Toast.makeText(this, "Invalid ID format.", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                R.id.searchByName -> {
//                    val item = itemManagement.items.values.find { it.name.equals(query, ignoreCase = true) }
//                    if (item != null) {
//                        itemDetailsTextView.text = "VIEW ITEM INFORMATION \n\n Item ID: ${item.id} \n ${item.name} \n ₱${item.price}"
//                        itemDetailsTextView.visibility = TextView.VISIBLE
//                    } else {
//                        itemDetailsTextView.text = "Item with name \"$query\" not found."
//                        itemDetailsTextView.visibility = TextView.VISIBLE
//                    }
//                }
//            }
//        }
//    }
//}