package com.henriette.wholezen.activies

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.henriette.wholezen.R
import com.henriette.wholezen.databinding.ActivityCheckInBinding

class CHeckInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtonListeners()

        binding.fabBack.setOnClickListener {
            val intent = Intent(this, WelcomeBackActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnBeginCheckIn.setOnClickListener{
            val intent = Intent(
                this,WelcomeBackActivity::class.java)
            startActivity(intent)
        }

    }


    private fun setupButtonListeners() {
        binding.btn9am.tag = "9am"
        binding.btn3pm.tag = "3pm"
        binding.btn10pm.tag = "10pm"

        binding.btn9am.setOnClickListener { onButtonClicked(binding.btn9am) }
        binding.btn3pm.setOnClickListener { onButtonClicked(binding.btn3pm) }
        binding.btn10pm.setOnClickListener { onButtonClicked(binding.btn10pm) }
    }

    private fun onButtonClicked(button: Button) {
        resetButtons()

        button.text = ""
        button.setBackgroundResource(R.drawable.green_background_layout)
        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tick, 0, 0, 0)
    }

    private fun resetButtons() {
        val buttons = arrayOf(binding.btn9am, binding.btn3pm, binding.btn10pm)
        for (button in buttons) {
            val originalText = button.tag?.toString() ?: ""
            button.text = originalText
            button.setBackgroundResource(R.drawable.round_button)
            button.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        }


    }

}
