package com.shubham.brainyexplorers.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shubham.brainyexplorers.databinding.ActivityHomeBinding
import com.shubham.brainyexplorers.ui.labs.LabsAdapter
import com.shubham.brainyexplorers.ui.labs.LabsViewModel
import com.shubham.brainyexplorers.ui.webview.WebViewActivity
import androidx.recyclerview.widget.LinearLayoutManager


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: LabsViewModel by viewModels()
    private lateinit var adapter: LabsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView and Adapter
        adapter = LabsAdapter { labItem ->
            // On item click, open WebViewActivity
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(WebViewActivity.EXTRA_URL, labItem.url)
            intent.putExtra(WebViewActivity.EXTRA_TITLE, labItem.title)
            startActivity(intent)
        }
        binding.labsRecyclerView.adapter = adapter
        binding.labsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Observe labs data
        viewModel.labs.observe(this) { labs ->
            binding.labsProgressBar.visibility = View.GONE
            adapter.submitList(labs)
        }
        viewModel.error.observe(this) { errorMsg ->
            binding.labsProgressBar.visibility = View.GONE
            Log.e("Error", "$errorMsg")
            // Only show Toast if errorMsg is not null or blank
            if (!errorMsg.isNullOrBlank()) {
                Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
        // Show progress bar and load data
        binding.labsProgressBar.visibility = View.VISIBLE
        viewModel.loadLabs()
    }
}