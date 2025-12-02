package com.bignerdranch.android.zd2_karamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.buttonEnter)
        val emailText = findViewById<EditText>(R.id.emailText)
        val passwordText = findViewById<EditText>(R.id.passwordText)

        var email = emailText.text.toString()
        var password = passwordText.text.toString()

        button.setOnClickListener {
            if (!emailText.text.toString().isNullOrEmpty() && !emailText.text.toString().isNullOrEmpty())
            {

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
            else
            {
                Toast.makeText(
                    this,
                    "Неверный ввод почты или пароля",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}