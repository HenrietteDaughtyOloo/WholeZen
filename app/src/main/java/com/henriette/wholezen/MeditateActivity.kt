package com.henriette.wholezen

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.henriette.wholezen.databinding.ActivityMeditateBinding

class MeditateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMeditateBinding
    private  lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeditateBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.sooth)

        binding.ivPlayIcon.setOnClickListener {
//            initializeMediaPlayer()
            togglePlayPause()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.ivNext.setOnClickListener {
            val intent = Intent(this@MeditateActivity, WelcomeBackActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

        private fun togglePlayPause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            binding.ivPlayIcon.setImageResource(R.drawable.play_icon)

        // Set play icon
        } else {
            mediaPlayer.start()
            binding.ivPlayIcon.setImageResource(R.drawable.pause)
            mediaPlayer.pause()
        // Set pause icon
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Release the MediaPlayer resources
    }

}