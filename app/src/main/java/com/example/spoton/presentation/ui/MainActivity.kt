package com.example.spoton.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spoton.R
import com.example.spoton.databinding.ActivityMainBinding
import com.example.spoton.presentation.adapter.CryptoDataAdapter
import com.example.spoton.presentation.viemodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.DividerItemDecoration




@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CryptoDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.cryptoRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CryptoDataAdapter()
        binding.cryptoRecyclerView.adapter = adapter
        binding.cryptoRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.cryptoRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        displayCryptoData()
        initSwipe()
    }

    private fun initSwipe() {
        if (!binding.swiperefresh.isRefreshing) {
            binding.swiperefresh.setOnRefreshListener {
                var response = vm.addMoreData()
                binding.cryptoProgressBar.visibility = View.VISIBLE
                response.observe(
                    this,
                    Observer {
                        if (it != null) {
                            adapter.setList(it)
                            adapter.notifyDataSetChanged()
                            binding.cryptoProgressBar.visibility = View.GONE
                            binding.swiperefresh.isRefreshing=false
                        } else {
                            binding.cryptoProgressBar.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "No data available",
                                Toast.LENGTH_LONG
                            ).show()
                            binding.swiperefresh.isRefreshing=false
                        }
                    }
                )
            }
        }
    }

    private fun displayCryptoData() {
        binding.cryptoProgressBar.visibility = View.VISIBLE
        val responseLiveData = vm.getCryptoData()
        responseLiveData.observe(
            this,
            Observer {
                if (it != null) {
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    binding.cryptoProgressBar.visibility = View.GONE
                } else {
                    binding.cryptoProgressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "No data available", Toast.LENGTH_LONG)
                        .show()
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateCryptoData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateCryptoData() {
        binding.cryptoProgressBar.visibility = View.VISIBLE
        val response = vm.updateCryptoData()
        response.observe(
            this,
            Observer {
                if (it != null) {
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    binding.cryptoProgressBar.visibility = View.GONE
                } else {
                    binding.cryptoProgressBar.visibility = View.GONE
                }
            }
        )
    }
}
