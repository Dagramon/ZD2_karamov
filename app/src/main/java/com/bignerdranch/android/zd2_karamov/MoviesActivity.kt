package com.bignerdranch.android.zd2_karamov

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Movie(
    val label : String,
    val image : Int
)


class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val moviesRecyclerView = findViewById<RecyclerView>(R.id.moviesRecyclerView)

        val backText = findViewById<TextView>(R.id.label2)

        backText.setOnClickListener {

            val intent = Intent(this@MoviesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        val movieList = mutableListOf<Movie>(
            Movie("Witcher", R.drawable.witcher),
            Movie("Jessica Jones", R.drawable.jessica_jones),
            Movie("Magicians", R.drawable.magicians)
        )

        moviesRecyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int,
            ): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val textView = holder.itemView.findViewById<TextView>(R.id.movieLabel)
                val imageView = holder.itemView.findViewById<ImageView>(R.id.movieImage)

                textView.text = movieList[position].label
                imageView.setBackgroundResource(movieList[position].image)

            }

            override fun getItemCount() = movieList.size

        }
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}