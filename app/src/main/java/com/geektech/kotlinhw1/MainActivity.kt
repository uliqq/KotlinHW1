package com.geektech.kotlinhw1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.geektech.kotlinhw1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {

                val data = result.data?.getStringExtra(SecondActivity.SECOND_ACTIVITY_EXTRA)
                binding.etSend.setText(data)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            val userMsgError: String = binding.etSend.text.toString()

            if (userMsgError.trim().isEmpty()) {
                binding.etSend.error = "Пусто"
                Toast.makeText(this, "Введите данные", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Данные успешно переданы в SecondActivity", Toast.LENGTH_SHORT).show()

                val i = Intent(this, SecondActivity::class.java)
                i.putExtra(MAIN_ACTIVITY_EXTRA, binding.etSend.text.toString())
                startForResult.launch(i)
            }
        }
    }

    companion object {
        const val MAIN_ACTIVITY_EXTRA = "editTextData"
    }
}
