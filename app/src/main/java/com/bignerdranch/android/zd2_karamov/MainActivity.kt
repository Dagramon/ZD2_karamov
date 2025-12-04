package com.bignerdranch.android.zd2_karamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val discussionButton = findViewById<LinearLayout>(R.id.discussionButton)
        val moviesButton = findViewById<LinearLayout>(R.id.moviesButton)
        val favMoviesButton = findViewById<LinearLayout>(R.id.favMoviesButton)

        discussionButton.setOnClickListener{
            val intent = Intent(this@MainActivity, DiscussionsActivity::class.java)
            startActivity(intent)
            finish()
        }
        moviesButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MoviesActivity::class.java)
            startActivity(intent)
            finish()
        }
        favMoviesButton.setOnClickListener {
            val intent = Intent(this@MainActivity, FavoriteMoviesActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}