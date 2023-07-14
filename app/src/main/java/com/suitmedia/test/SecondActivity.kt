package com.suitmedia.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.suitmedia.test.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Second Screen")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnChooseUser.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }

        nameSetup()
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

    private fun nameSetup(){
        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        if (!name.isNullOrEmpty()){
            binding.textUsername.text = name
        }


        val fullName = intent.getStringExtra(UsersAdapter.EXTRA_ID)
        if (!fullName.isNullOrEmpty()) {
            binding.selectedName.text = fullName
            binding.textUsername.text = fullName
        }
    }
}