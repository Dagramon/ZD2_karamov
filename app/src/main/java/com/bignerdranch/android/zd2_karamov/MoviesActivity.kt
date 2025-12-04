package com.bignerdranch.android.zd2_karamov

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.delay
import org.json.JSONObject
import kotlin.concurrent.thread

const val API_KEY = "5d7eec32"

val titlesList = arrayOf(
    "The Witcher",
    "Magicians",
    "Batman",
    "Star Wars",
    "Suicide Squad",
    "Interstellar"
)

var movieList = mutableListOf<Movie>()


class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val backText = findViewById<TextView>(R.id.label2)

        backText.setOnClickListener {

            val intent = Intent(this@MoviesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        movieList.clear()
        for (i in 0..titlesList.size-1)
        {
            requestMovie(titlesList[i])
        }
        Thread.sleep(1000)
        initRecyclerView()
    }
    private fun initRecyclerView()
    {
        val moviesRecyclerView = findViewById<RecyclerView>(R.id.moviesRecyclerView)

        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
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
                val favButton = holder.itemView.findViewById<ImageButton>(R.id.favoriteButton)

                if (movieList[position].favorite)
                {
                    favButton.isEnabled = false
                    favButton.setBackgroundResource(R.drawable.heart_clicked)
                }

                favButton.setOnClickListener {
                    favButton.isEnabled = false
                    movieList[position].favorite = true
                    favButton.setBackgroundResource(R.drawable.heart_clicked)
                    val item = movieList[position]
                    val db = MainDB.getDb(applicationContext)
                    Thread{
                        db.getDao().insertItem(item)
                    }.start()
                }

                textView.text = movieList[position].title
                Picasso
                    .get()
                    .load(movieList[position].image)
                    .fit()
                    .into(imageView)

            }

            override fun getItemCount() = movieList.size

        }
    }


    private fun requestMovie(title : String)
    {
        val url = "https://www.omdbapi.com/?apikey=$API_KEY&type=movie&s=$title"

        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                result -> parseMovieData(result)
            },
            {
                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }
    private fun parseMovieData(result : String) {
        val mainObject = JSONObject(result)
        val item = Movie(
            null,
            mainObject.getJSONArray("Search").getJSONObject(0).getString("Title"),
            mainObject.getJSONArray("Search").getJSONObject(0).getString("Poster"),
            favorite = false
        )
        val db = MainDB.getDb(this@MoviesActivity)
        Thread {
            if (db.getDao().movieisFav(item.title))
            {
                item.favorite = true
            }
            movieList.add(item)
        }.start()
    }
}