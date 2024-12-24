package com.myjar.jarassignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListAdapter
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myjar.jarassignment.data.JarViewModelFactory
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.data.repository.JarRepository
import com.myjar.jarassignment.data.repository.JarRepositoryImpl
import com.myjar.jarassignment.ui.adapter.ItemAdapter
import com.myjar.jarassignment.ui.vm.JarViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<JarViewModel>{
        JarViewModelFactory(JarRepositoryImpl(createRetrofit()))
    }
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupUi()
        observeFlows()
    }

    private fun observeFlows() {
        lifecycleScope.launch {
            viewModel.listStringData.collectLatest {
                adapter.submitList(it)
            }
        }

        lifecycleScope.launch {
                viewModel.navigateToItem.filterNotNull().collectLatest {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("itemId", it)
                    startActivity(intent)
                }
        }
    }

    private fun setupUi() {
        val recyclerView: RecyclerView = findViewById(R.id.item_list)
        adapter = ItemAdapter { selectedItem ->
            viewModel.navigateToItemDetail(selectedItem.id)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }
}