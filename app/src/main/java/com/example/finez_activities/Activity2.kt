package com.example.finez_activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {

    // Data structures to hold selected products
    private val selectedTshirts = mutableListOf<Product>()
    private val selectedLaces = mutableListOf<Product>()
    private val selectedOthers = mutableListOf<Product>()
    private val selectedImageButtons = mutableSetOf<ImageButton>()

    private lateinit var selectedProductsTextView: TextView
    private lateinit var toggleButton: ToggleButton
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        selectedProductsTextView = findViewById(R.id.selectedProductsTextView)
        toggleButton = findViewById(R.id.toggleButton)
        scrollView = findViewById(R.id.scrollView)

        setupImageButtons()

        // Toggle button listener
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                scrollView.post { scrollView.fullScroll(View.FOCUS_DOWN) }
                updateSelectedProductsView()
                selectedProductsTextView.visibility = View.VISIBLE
            } else {
                selectedProductsTextView.visibility = View.GONE
            }
        }

        // Button to clear selections
        val clearButton = findViewById<Button>(R.id.clearButton)
        clearButton.setOnClickListener {
            clearSelections()
        }
    }

    private fun setupImageButtons() {
        // ImageButtons for selecting products visually
        val imageButtons: Array<ImageButton> = arrayOf(
            findViewById(R.id.imageButton1), findViewById(R.id.imageButton2),
            findViewById(R.id.imageButton3), findViewById(R.id.imageButton4),
            findViewById(R.id.imageButton5), findViewById(R.id.imageButton6),
            findViewById(R.id.imageButton7), findViewById(R.id.imageButton8),
            findViewById(R.id.imageButton9), findViewById(R.id.imageButton10),
            findViewById(R.id.imageButton11)
        )

        imageButtons.forEach { imageButton ->
            imageButton.setOnClickListener {
                toggleImageButtonSelection(imageButton)
                updateSelectedProductsView()
            }
        }
    }

    private fun toggleImageButtonSelection(imageButton: ImageButton) {
        val productName = imageButton.contentDescription?.toString() ?: return
        val product = findProductByName(productName)

        if (selectedImageButtons.contains(imageButton)) {
            selectedImageButtons.remove(imageButton)
            product?.let { removeProductFromList(it) }
            imageButton.setBackgroundResource(R.drawable.image_button_background)
        } else {
            val category = getCategoryForImageButton(imageButton)
            val price = getPriceForProduct(productName)
            val variation = getVariationForProduct(productName)
            val color = getColorForProduct(imageButton)

            val newProduct = Product(
                name = productName,
                variation = variation,
                price = price,
                color = color,
                category = category,
                size = null  // Initialize size as null
            )

            selectedImageButtons.add(imageButton)
            addProductToList(newProduct)
            imageButton.setBackgroundResource(R.drawable.image_button_background_selected)
        }

        setupSizeCheckboxes(imageButton)  // Setup checkboxes for size selection
    }

    private fun toggleCheckboxSelection(checkbox: CheckBox, productName: String) {
        val product = findProductByName(productName) ?: return

        if (checkbox.isChecked) {
            product.size = checkbox.text.toString()
        } else {
            product.size = null
        }
    }

    private fun setupSizeCheckboxes(imageButton: ImageButton) {
        val sizeCheckbox1 = findViewById<CheckBox>(R.id.size_checkbox1)
        val sizeCheckbox2 = findViewById<CheckBox>(R.id.size_checkbox2)
        val sizeCheckbox3 = findViewById<CheckBox>(R.id.size_checkbox3)
        val sizeCheckbox4 = findViewById<CheckBox>(R.id.size_checkbox4)

        sizeCheckbox1.setOnCheckedChangeListener { _, isChecked ->
            toggleCheckboxSelection(sizeCheckbox1, imageButton.contentDescription?.toString() ?: return@setOnCheckedChangeListener)
            updateSelectedProductsView()
        }

        sizeCheckbox2.setOnCheckedChangeListener { _, isChecked ->
            toggleCheckboxSelection(sizeCheckbox2, imageButton.contentDescription?.toString() ?: return@setOnCheckedChangeListener)
            updateSelectedProductsView()
        }

        sizeCheckbox3.setOnCheckedChangeListener { _, isChecked ->
            toggleCheckboxSelection(sizeCheckbox3, imageButton.contentDescription?.toString() ?: return@setOnCheckedChangeListener)
            updateSelectedProductsView()
        }

        sizeCheckbox4.setOnCheckedChangeListener { _, isChecked ->
            toggleCheckboxSelection(sizeCheckbox4, imageButton.contentDescription?.toString() ?: return@setOnCheckedChangeListener)
            updateSelectedProductsView()
        }
    }


    private fun addProductToList(product: Product) {
        when (product.category) {
            "TSHIRT" -> selectedTshirts.add(product)
            "LACE" -> selectedLaces.add(product)
            "OTHERS" -> selectedOthers.add(product)
        }
    }

    private fun removeProductFromList(product: Product) {
        when (product.category) {
            "TSHIRT" -> selectedTshirts.remove(product)
            "LACE" -> selectedLaces.remove(product)
            "OTHERS" -> selectedOthers.remove(product)
        }
    }

    private fun findProductByName(name: String): Product? {
        return when {
            selectedTshirts.any { it.name == name } -> selectedTshirts.find { it.name == name }
            selectedLaces.any { it.name == name } -> selectedLaces.find { it.name == name }
            selectedOthers.any { it.name == name } -> selectedOthers.find { it.name == name }
            else -> null
        }
    }

    private fun updateSelectedProductsView() {
        val sb = StringBuilder()
        sb.append("ORDER/S:\n\n")

        appendProductsToStringBuilder(sb, selectedTshirts, "T-SHIRTS")
        appendProductsToStringBuilder(sb, selectedLaces, "LACES")
        appendProductsToStringBuilder(sb, selectedOthers, "OTHERS")

        sb.append("--------------------------------------------\n")
        sb.append("TOTAL\n")
        sb.append("\n")
        sb.append("AMOUNT: P${calculateTotalAmount()}")

        selectedProductsTextView.text = sb.toString()
        selectedProductsTextView.visibility = if (toggleButton.isChecked) View.VISIBLE else View.GONE
    }

    private fun appendProductsToStringBuilder(sb: StringBuilder, products: List<Product>, category: String) {
        if (products.isNotEmpty()) {
            sb.append("$category:\n")
            products.forEach { product ->
                sb.append("${product.name}")
                if (!product.size.isNullOrBlank()) {
                    sb.append(" - ${product.size}")
                }
                sb.append("\n")
                if (!product.color.isNullOrBlank()) {
                    sb.append("Color: ${product.color}\n")
                }
                sb.append("Price: P${product.price}\n\n")
            }
        }
    }

    private fun getProductDetails(products: List<Product>): String {
        val sb = StringBuilder()
        products.forEach { product ->
            sb.append("${product.name}\n")
            if (!product.color.isNullOrBlank()) {
                sb.append("Color: ${product.color}\n")
            }
            sb.append("Price: P${product.price}\n\n")
        }
        return sb.toString()
    }

    private fun calculateTotalAmount(): Int {
        var total = 0

        total += calculateTotalPrice(selectedTshirts)
        total += calculateTotalPrice(selectedLaces)
        total += calculateTotalPrice(selectedOthers)

        return total
    }

    private fun calculateTotalPrice(products: List<Product>): Int {
        return products.sumBy { it.price.removePrefix("P").toInt() }
    }

    private fun clearSelections() {
        selectedTshirts.clear()
        selectedLaces.clear()
        selectedOthers.clear()
        selectedImageButtons.clear()
        selectedProductsTextView.text = ""
        selectedProductsTextView.visibility = View.GONE

        val imageButtons: Array<ImageButton> = arrayOf(
            findViewById(R.id.imageButton1), findViewById(R.id.imageButton2),
            findViewById(R.id.imageButton3), findViewById(R.id.imageButton4),
            findViewById(R.id.imageButton5), findViewById(R.id.imageButton6),
            findViewById(R.id.imageButton7), findViewById(R.id.imageButton8),
            findViewById(R.id.imageButton9), findViewById(R.id.imageButton10),
            findViewById(R.id.imageButton11)
        )

        imageButtons.forEach { imageButton ->
            imageButton.setBackgroundResource(R.drawable.image_button_background)
        }
    }

    private fun getCategoryForImageButton(imageButton: ImageButton): String {
        // Determine category based on imageButton ID or other criteria
        // For example:
        return when (imageButton.id) {
            R.id.imageButton1, R.id.imageButton2, R.id.imageButton3, R.id.imageButton4 -> "TSHIRT"
            R.id.imageButton5, R.id.imageButton6, R.id.imageButton7 -> "LACE"
            R.id.imageButton8, R.id.imageButton9, R.id.imageButton10, R.id.imageButton11 -> "OTHERS"
            else -> ""
        }
    }

    private fun getPriceForProduct(productName: String): String {
        // Implement logic to get price based on productName
        // Example:
        return when (productName) {
            "EMBRO SHIRT" -> "1000"
            "POLO SHIRT" -> "1200"
            "URBAN SHIRT" -> "1100"
            "STRIPES SHIRT" -> "1300"
            "FEU MINIMALIST ID LACE" -> "500"
            "FEU TECH Circuit ID LACE" -> "600"
            "FEU Aztec ID LACE" -> "550"
            "FEU Tamaraw Keychain" -> "200"
            "FEU Insulated Tumbler" -> "300"
            "FEU Tote Bag" -> "250"
            "Mask and Mirrors" -> "150"
            else -> "0"
        }
    }

    private fun getVariationForProduct(productName: String): String? {
        // Implement logic to get variation based on productName
        // Example:
        return when (productName) {
            "EMBRO SHIRT" -> "Long Sleeve"
            "POLO SHIRT" -> "Regular Fit"
            "URBAN SHIRT" -> "Slim Fit"
            // Add more variations as needed
            else -> null
        }
    }

    private fun getColorForProduct(imageButton: ImageButton): String {
        // Implement logic to get color based on imageButton or product
        // Example:
        return when (imageButton.id) {
            R.id.imageButton5 -> "Black"  // Example for FEU MINIMALIST ID LACE
            R.id.imageButton6 -> "Green-Black"  // Example for FEU TECH Circuit ID LACE
            R.id.imageButton7 -> "Red-Black"  // Example for FEU Aztec ID LACE
            // Add more colors as needed
            else -> ""
        }
    }
}
