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

class Item(var id: Int, var name: String, var price: Double)

object ItemManagement {
    val items = mutableMapOf<Int, Item>()
    val itemNames = mutableListOf<String>()

    fun addItem(id: Int, name: String, price: Double) {
        if (items.containsKey(id)) {
            println("\n\t > Item with ID $id already exists.")
        } else {
            val item = Item(id, name, price)
            items[id] = item
            itemNames.add(name)
            println("\n\t Item added: ${itemToString(item)}")
        }
    }

    fun deleteItem(id: Int) {
        val item = items.remove(id)
        if (item != null) {
            itemNames.remove(item.name)
            println("\n\t Item deleted: ${itemToString(item)}")
        } else {
            println("\n\t > Item with ID $id not found.")
        }
    }

    fun editItem(id: Int, newId: Int, newName: String, newPrice: Double) {
        val item = items[id]
        if (item != null) {
            val oldName = item.name
            item.name = newName
            item.price = newPrice

            if (newId != id) {
                items.remove(id)
                item.id = newId
                items[newId] = item
            }

            itemNames.remove(oldName)
            itemNames.add(newName)

            println("\n\t Item edited: ${itemToString(item)}")
        } else {
            println("\n\t > Item with ID $id not found.")
        }
    }

    fun viewItem(id: Int): Item? {
        return items[id]
    }

    fun viewAllItems(): List<Item> {
        return items.values.toList()
    }

    fun itemToString(item: Item): String {
        return "\n\t\t   ID: ${item.id} " +
                "\n\t\t  Name: ${item.name}" +
                "\n\t\t  Price: ${item.price}"
    }
}

class ItemManagementSystem : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item_management_system)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nextBtn = findViewById<FloatingActionButton>(R.id.nextAct)
        nextBtn.setOnClickListener{
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }

        val prevBtn = findViewById<FloatingActionButton>(R.id.prevAct)
        prevBtn.setOnClickListener{
            val intent = Intent(this, Activity1::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            val intent = Intent(this, DeleteItemActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.editButton).setOnClickListener {
            val intent = Intent(this, EditItemActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.viewButton).setOnClickListener {
            val intent = Intent(this, ViewItemActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.viewAllButton).setOnClickListener {
            val intent = Intent(this, ViewAllItemsActivity::class.java)
            startActivity(intent)
        }


    }
}