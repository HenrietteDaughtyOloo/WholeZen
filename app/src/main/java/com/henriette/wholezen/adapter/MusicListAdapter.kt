package com.henriette.wholezen.adapter

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henriette.wholezen.R
import com.henriette.wholezen.databinding.MusicListBinding
import com.henriette.wholezen.dataclass.MusicDataClass

class MusicListAdapter(var musicListItem: List<MusicDataClass>):RecyclerView.Adapter<MusicListAdapter.MusicViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayingPosition: Int = -1


    inner class MusicViewHolder(val binding: MusicListBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item: MusicDataClass,position: Int) {
            binding.ivIcon.setImageResource(item.icon)
            binding.tvQuote.text = item.descriptions

            itemView.setOnClickListener {
                if (mediaPlayer != null) {
                    mediaPlayer?.stop()
                    mediaPlayer?.release()
                    mediaPlayer = null
                }

                if (currentPlayingPosition != position) {
                    mediaPlayer = MediaPlayer.create(itemView.context, item.music)
                    mediaPlayer?.start()
                    currentPlayingPosition = position
                } else {
                    currentPlayingPosition = -1
                }
            }

        }
    }
    
        override fun onCreateViewHolder(parent: ViewGroup,viewType:Int):MusicViewHolder{
            val binding = MusicListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return MusicViewHolder(binding)
        }

        override fun onBindViewHolder(holder:MusicViewHolder, position: Int){
            val currentItem = musicListItem[position]
            holder.bind(currentItem, position)
        }

        override fun getItemCount() = musicListItem.size

    fun updateData(newMusicList: List<MusicDataClass>) {
        musicListItem = newMusicList
        notifyDataSetChanged()
    }

    override fun onViewRecycled(holder: MusicViewHolder) {
        super.onViewRecycled(holder)
        if (holder.adapterPosition == currentPlayingPosition) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            currentPlayingPosition = -1
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mediaPlayer?.release()
        mediaPlayer = null
    }




}