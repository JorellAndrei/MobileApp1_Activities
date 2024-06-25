//package com.example.finez_activities
//
//import android.graphics.Typeface
//import android.os.Bundle
//import android.util.TypedValue
//import android.view.Gravity
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.LinearLayout
//import android.widget.RadioGroup
//import android.widget.Spinner
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContextCompat
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//
//class ViewAllItemsActivity : AppCompatActivity() {
//
//    private lateinit var itemsContainer: LinearLayout
//    private lateinit var sortOrderGroup: RadioGroup
//    private lateinit var sortBySpinner: Spinner
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_view_all_items)
//
//        // Initialize views
//        itemsContainer = findViewById(R.id.itemsContainer)
//        sortOrderGroup = findViewById(R.id.sortOrderGroup)
//        sortBySpinner = findViewById(R.id.sortBySpinner)
//
//        // Apply insets to main layout
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        // Initialize Spinner with sorting options
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.sort_by_options,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            sortBySpinner.adapter = adapter
//        }
//
//        // Handle sorting when radio group selection changes
//        sortOrderGroup.setOnCheckedChangeListener { _, _ ->
//            sortAndDisplayItems()
//        }
//
//        // Handle sorting when spinner selection changes
//        sortBySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                sortAndDisplayItems()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//
//        // Handle previous button click
//        val prevAct = findViewById<FloatingActionButton>(R.id.prevAct)
//        prevAct.setOnClickListener {
//            onBackPressed()
//        }
//
//        // Initially load and display items based on default sorting
//        sortAndDisplayItems()
//    }
//
//    // Function to sort items based on current selections and update UI
//    private fun sortAndDisplayItems() {
//        val items = ItemManagement.viewAllItems()
//
//        val selectedSortBy = sortBySpinner.selectedItemPosition
//        val ascending = sortOrderGroup.checkedRadioButtonId == R.id.sortAscending
//
//        // Perform sorting based on selected options
//        val sortedItems = when (selectedSortBy) {
//            0 -> if (ascending) items.sortedBy { it.id } else items.sortedByDescending { it.id }
//            1 -> if (ascending) items.sortedBy { it.name } else items.sortedByDescending { it.name }
//            2 -> if (ascending) items.sortedBy { it.price } else items.sortedByDescending { it.price }
//            else -> items
//        }
//
//        displayAllItems(sortedItems)
//    }
//
//    // Function to display all items
//    private fun displayAllItems(items: List<Item>) {
//        itemsContainer.removeAllViews()
//
//        // Iterate through each item and create a TextView for it
//        for (item in items) {
//            val itemTextView = TextView(this).apply {
//                text = ItemManagement.itemToString(item)
//                textSize = 20f
//                setPadding(6, 6, 6, 6)
//
//                // Customize the appearance to match itemDetailsTextView
//                layoutParams = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//                )
//                background = resources.getDrawable(R.drawable.item_text_background)
//                gravity = Gravity.CENTER
//                setTextColor(ContextCompat.getColor(this@ViewAllItemsActivity, R.color.item_text_color))
//                setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
//                setTypeface(typeface, Typeface.BOLD)
//            }
//
//            // Add the TextView to the itemsContainer
//            itemsContainer.addView(itemTextView)
//        }
//
//        // Show itemDetailsTextView if there are no items
//        if (items.isEmpty()) {
//            val itemDetailsTextView = findViewById<TextView>(R.id.itemDetailsTextView)
//            itemDetailsTextView.visibility = View.VISIBLE
//        } else {
//            val itemDetailsTextView = findViewById<TextView>(R.id.itemDetailsTextView)
//            itemDetailsTextView.visibility = View.GONE
//        }
//    }
//}
