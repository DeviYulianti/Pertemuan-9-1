package com.devi.mediaplayer2

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
class MainActivity : AppCompatActivity() {
    private lateinit var txt: MediaPlayer
    private lateinit var tx: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = MediaPlayer.create(this, R.raw.txt)
        tx = MediaPlayer.create(this, R.raw.romantic)
        btnStart.setOnClickListener {
            runBlocking {
                launch { playBeats("x-x-x-x-x-x-x-x-x-x-x-x-", R.raw.romantic)
                }
                playBeats("x-----x-----x-----x-----", R.raw.txt)
            }
        }
    }
    suspend fun playBeats(beats: String, fileId: Int){
        val parts = beats.split("x")
        var count = 0
        for(part in parts){
            count += part.length + 1
            if(part == ""){
                if(fileId == R.raw.txt)
                    txt.start()
                else
                    tx.start()
            }else{
                delay(1000 * (part.length + 1L))
                if(count < beats.length){
                    if(fileId == R.raw.romantic)
                        txt.start()
                    else
                        tx.start()
                }
            }
        }
    }
    override fun onStop() {
        super.onStop()
        txt.stop()
        tx.stop()
    }
}