package com.myjar.jarassignment

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Get the itemId passed from the MainActivity
        val itemId = intent.getStringExtra("itemId")

        // Find the TextView to display the details
        val detailTextView: TextView = findViewById(R.id.detail_text)

        // Display the item details
        detailTextView.text = "Item Details for ID: $itemId"
    }
}
