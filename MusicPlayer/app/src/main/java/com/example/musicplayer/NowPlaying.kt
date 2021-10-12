package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar

class NowPlaying : AppCompatActivity() {

    //Change the seekbar position while the song is playing.
    //Create a runnable object and handler.

    lateinit var runnable: Runnable
    private var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nowplaying)

        //***Media Player Object***//

        //Raw folder inside a res directory and import music
        val mediaplayer = MediaPlayer.create(this, R.raw.music)

        //***Media Player Object***//

        //***Seekbar***//
        val seekbar = findViewById<SeekBar>(R.id.seekbar)
        //Seekbar Functionalities.
        seekbar.progress = 0
        //Maximum value of the seekbar = duration of the music.
        seekbar.max = mediaplayer.duration
        //***Seekbar***//

        //***Play Button***//
        //Play Button Event
        val play_button = findViewById<ImageButton>(R.id.play_btn)

        //When we click the button
        play_button.setOnClickListener {
            //Check if the media player is not playing.
            if(!mediaplayer.isPlaying) {
                mediaplayer.start()

                //Change the button's image
                play_button.setImageResource(R.drawable.ic_baseline_pause_24)

            }

            else {
                //The media player is playing and we can pause it.
                mediaplayer.pause()
                play_button.setImageResource(R.drawable.ic_baseline_play_arrow_24)

            }

        }
        //***Play Button***//

        //Seek Bar Event
        //Changing seek bar progress will change song position.
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun  onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                //Position of SeekBar reflects music file position.
                if(changed){
                    mediaplayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0:SeekBar?) {

            }

            override fun onStopTrackingTouch(p0:SeekBar?) {

            }
        })

        runnable = Runnable {
            seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable, 1000)
        //Once the song ends, the seekbar resets to 0 and the button image changes.
        mediaplayer.setOnCompletionListener {
            play_button.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekbar.progress = 0
        }
    }
}