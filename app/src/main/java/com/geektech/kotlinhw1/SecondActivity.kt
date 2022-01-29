package com.geektech.kotlinhw1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.geektech.kotlinhw1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(MainActivity.MAIN_ACTIVITY_EXTRA)) {
            val data = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_EXTRA)
            binding.etSend.setText(data)
        }

        binding.btnSend.setOnClickListener {
            val userMsgError: String = binding.etSend.text.toString()

            if (userMsgError.trim().isEmpty()) {
                binding.etSend.error = "Пусто"
                Toast.makeText(this, "Введите данные ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Данные успешно переданы в MainActivity", Toast.LENGTH_SHORT).show()

                val intent = Intent()
                intent.putExtra(SECOND_ACTIVITY_EXTRA, binding.etSend.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    companion object {
        const val SECOND_ACTIVITY_EXTRA = "editTextData"
    }
}