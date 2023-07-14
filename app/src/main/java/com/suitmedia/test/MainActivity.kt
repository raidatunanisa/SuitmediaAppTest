package com.suitmedia.test

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.suitmedia.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        emptyTextValidation()
        emptyNameValidation()
        btnNextSetup()
        btnCheckSetup()
    }

    private fun btnNextSetup(){
        binding.btnNext.setOnClickListener {
            val name = binding.edName.text.toString()
            if (name.isEmpty()){
                binding.nameEditTextLayout.error = getString(R.string.empty_name_error)
            }else{
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(EXTRA_NAME, name)
                startActivity(intent)
            }
        }
    }

    private fun btnCheckSetup(){
        binding.btnCheck.setOnClickListener {
            val text = binding.edText.text.toString().trim().lowercase()
            val palindrome = isPalindrome(text)
            val builder = AlertDialog.Builder(this)
            when {
                text.isEmpty() -> {
                    binding.TextEditTextLayout.error = getString(R.string.empty_text_error)
                }
                palindrome -> {
                    builder.setTitle("Pengecekan kalimat")
                        .setMessage("isPalindrome")
                        .setNegativeButton("Ya", null)
                        .show()
                }
                !palindrome -> {
                    builder.setTitle("Pengecekan kalimat")
                        .setMessage("not palindrome")
                        .setNegativeButton("Ya", null)
                        .show()
                }
            }
        }
    }

    private fun emptyTextValidation(){
        binding.edText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s.toString()
                if (text.isEmpty()) {
                    binding.TextEditTextLayout.error = "Tolong isi text terlebih dahulu"
                }else{
                    binding.TextEditTextLayout.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }

    private fun emptyNameValidation(){
        binding.edName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = s.toString()
                if (name.isEmpty()) {
                    binding.nameEditTextLayout.error = "Tolong isi text terlebih dahulu"
                }else{
                    binding.nameEditTextLayout.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun isPalindrome(word: String): Boolean{
        val reversed = word.reversed()
        return word == reversed
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
    }
}