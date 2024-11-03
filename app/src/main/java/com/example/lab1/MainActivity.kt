package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputName: EditText
    private lateinit var inputPhone: EditText
    private lateinit var inputEmail: EditText
    private lateinit var btnSaveContact: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputName = findViewById(R.id.inputName)
        inputPhone = findViewById(R.id.inputPhone)
        inputEmail = findViewById(R.id.inputEmail)
        btnSaveContact = findViewById(R.id.btnSaveContact)


        btnSaveContact.setOnClickListener {
            val savedContact = Contact(
                inputName.text.toString(),
                inputPhone.text.toString(),
                inputEmail.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra("savedContact", savedContact)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
