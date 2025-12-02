package com.bignerdranch.android.zd2_karamov

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DiscussionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discussions)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val backText = findViewById<TextView>(R.id.label1)

        backText.setOnClickListener {

            val intent = Intent(this@DiscussionsActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        val dataList = listOf(
            "Lucifer",
            "The witcher",
            "LOST",
            "The Magicians"
        )

        recyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int,
            ): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discussion, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val textView = holder.itemView.findViewById<TextView>(R.id.textView)

                textView.text = dataList[position]

            }

            override fun getItemCount() = dataList.size

        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}