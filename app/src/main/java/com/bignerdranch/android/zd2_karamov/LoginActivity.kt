package com.bignerdranch.android.zd2_karamov

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.core.content.edit

const val PREFS = "LoginPrefs"
const val KEY_EMAIL = "Email"
const val  KEY_PASSWORD = "Password"
class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.buttonEnter)
        val emailText = findViewById<EditText>(R.id.emailText)
        val passwordText = findViewById<EditText>(R.id.passwordText)

        sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE)

        val savedEmail = sharedPreferences.getString(KEY_EMAIL, "")
        val savedPassword = sharedPreferences.getString(KEY_PASSWORD, "")

        emailText.setText(savedEmail)
        passwordText.setText(savedPassword)

        button.setOnClickListener {
            if (!emailText.text.toString().isNullOrEmpty() && !passwordText.text.toString().isNullOrEmpty())
            {

                sharedPreferences.edit {
                    putString(KEY_EMAIL, emailText.text.toString())
                    putString(KEY_PASSWORD, passwordText.text.toString())
                    apply()
                }

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