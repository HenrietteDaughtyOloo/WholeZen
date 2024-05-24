package com.henriette.wholezen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.henriette.wholezen.databinding.ActivityWelcomeBackBinding

class WelcomeBackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWelcomeBackBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnCheckIn.setOnClickListener {
            val intent = Intent(this,CHeckInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnReflect.setOnClickListener {
            val intent = Intent(this,ReflectActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnSet.setOnClickListener {
            val intent = Intent(this,SetActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}