package com.henriette.wholezen.activies

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.henriette.wholezen.R
import com.henriette.wholezen.adapter.MusicListAdapter
import com.henriette.wholezen.databinding.ActivityMeditateBinding
import com.henriette.wholezen.dataclass.MusicDataClass

class MeditateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMeditateBinding
    private  lateinit var mediaPlayer: MediaPlayer
    private lateinit var adapter: MusicListAdapter
    private lateinit var cardItems: List<MusicDataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeditateBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.sooth)


        binding.ivPlayIcon.setOnClickListener {
            mediaPlayer.start()
            if (mediaPlayer.isPlaying) {
//                mediaPlayer.start()
                binding.ivPlayIcon.setImageResource(R.drawable.pause)

                // Set play icon
            } else {
                mediaPlayer.pause()
                binding.ivPlayIcon.setImageResource(R.drawable.play_icon)
            }

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

        cardItems = listOf(
            MusicDataClass(R.drawable.peace, "Life is too short to cry about","all",R.raw.sooth),
            MusicDataClass(R.drawable.peace, "Stay positive, work hard,Do your best","all",R.raw.sooth),
            MusicDataClass(R.drawable.peace, "Good things take time, Don't fret","mine",R.raw.sooth),
            MusicDataClass(R.drawable.peace, "Life is too short to cry about. So live","mine",R.raw.sooth),
            MusicDataClass(R.drawable.peace, "Stay positive, work hard","angry",R.raw.sooth),
            MusicDataClass(R.drawable.peace, "Good things take time","anxious",R.raw.sooth),
            MusicDataClass(R.drawable.peace, "Stay positive, work hard","angry",R.raw.sooth),
            MusicDataClass(R.drawable.peace, "Good things take time","sleep",R.raw.sooth),

            )

        adapter = MusicListAdapter(cardItems)
        binding.rvMusicList.layoutManager = GridLayoutManager(this, 2)
        binding.rvMusicList.adapter = adapter

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.recycler_view_spacing)
        binding.rvMusicList.addItemDecoration(GridSpacingItemDecoration(2, spacingInPixels, true))

        setupCardViewListeners()

    }


    private fun setupCardViewListeners() {
        binding.cvAll.setOnClickListener { filterItemsByCategory("all") }
        binding.cvMine.setOnClickListener { filterItemsByCategory("mine") }
        binding.cvAngry.setOnClickListener { filterItemsByCategory("angry") }
        binding.cvSleep.setOnClickListener { filterItemsByCategory("sleep") }
        binding.cvAnxious.setOnClickListener { filterItemsByCategory("anxious") }
    }

    private fun filterItemsByCategory(category: String) {
        val filteredList = if (category == "all") {
            cardItems
        } else {
            cardItems.filter { it.category == category }
        }
        adapter.updateData(filteredList)
    }

        private fun togglePlayPause() {
//        if (mediaPlayer.isPlaying) {
//            mediaPlayer.pause()
//            binding.ivPlayIcon.setImageResource(R.drawable.play_icon)
//
//        // Set play icon
//        } else {
//            mediaPlayer.start()
//            binding.ivPlayIcon.setImageResource(R.drawable.pause)
//            mediaPlayer.pause()
//        // Set pause icon
//        }
    }




    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Release the MediaPlayer resources
    }


}