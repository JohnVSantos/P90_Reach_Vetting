package com.example.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
    }

    fun performNowPlaying(v: View) {

        val button = findViewById<ImageButton>(R.id.NowPlayingButton)

        button.setOnClickListener {
            val intent = Intent(this, NowPlaying::class.java)

            startActivity(intent)
        }
    }
}