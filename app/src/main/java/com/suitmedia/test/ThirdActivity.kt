package com.suitmedia.test

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suitmedia.test.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var viewModel: ThirdActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Third Screen")

        showRecyclerView()
        viewModelSetup()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun viewModelSetup(){
        viewModel = ViewModelProvider(this)[ThirdActivityViewModel::class.java]
        viewModel.getUsers().observe(this){ users ->
            usersAdapter.submitData(lifecycle, users)
            usersAdapter.addLoadStateListener { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        binding.tvEmpty.visibility = View.VISIBLE
                    }
                    is LoadState.NotLoading -> {
                        binding.tvEmpty.visibility = View.GONE

                    }
                    is LoadState.Error -> {
                        Toast.makeText(this, "Gagal mendapatkan data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = false
                usersAdapter.refresh()
            }
        }

    }

    private fun showRecyclerView(){
        recyclerView = binding.rv
        usersAdapter = UsersAdapter()

        recyclerView.adapter = usersAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                usersAdapter.retry()
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }


}