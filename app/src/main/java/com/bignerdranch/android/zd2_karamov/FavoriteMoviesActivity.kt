package com.bignerdranch.android.zd2_karamov

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

var favoriteMovieList = mutableListOf<Movie>()

class FavoriteMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)

        val favMoviesRecyclerView = findViewById<RecyclerView>(R.id.favMoviesRecyclerView)
        val backText = findViewById<TextView>(R.id.label3)
        val db = MainDB.getDb(this.applicationContext)

        db.getDao().getAllItems().asLiveData().observe(this){

            favoriteMovieList.clear()
            it.forEach {
                favoriteMovieList.add(Movie(it.id, label = it.label, image = it.image, favorite = it.favorite))
            }

            favMoviesRecyclerView.layoutManager = LinearLayoutManager(this)
            favMoviesRecyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                    val favButton = holder.itemView.findViewById<ImageButton>(R.id.favoriteButton)

                    favButton.setBackgroundResource(R.drawable.heart_clicked)

                    favButton.setOnClickListener {
                        if (favoriteMovieList[position].favorite)
                        {
                            favoriteMovieList[position].favorite = false
                            favButton.setBackgroundResource(R.drawable.heart)
                            val item = favoriteMovieList[position]
                            Thread{
                                db.getDao().delete(item)
                            }.start()
                        }
                        else
                        {
                            favoriteMovieList[position].favorite = true
                            favButton.setBackgroundResource(R.drawable.heart_clicked)
                            val item = favoriteMovieList[position]
                            Thread{
                                db.getDao().insertItem(item)
                            }.start()
                        }
                    }

                    textView.text = favoriteMovieList[position].label
                    Picasso
                        .get()
                        .load(favoriteMovieList[position].image)
                        .fit()
                        .into(imageView)

                }

                override fun getItemCount() = favoriteMovieList.size

            }

        }

        backText.setOnClickListener {

            val intent = Intent(this@FavoriteMoviesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

}