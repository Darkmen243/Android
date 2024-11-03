package com.example.lab1

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button

class ContactsActivity : AppCompatActivity() {

    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddContact: Button
    private val contacts = mutableListOf<Contact>()

    private val addContactLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result.resultCode == RESULT_OK) {
            val newContact = result.data?.getParcelableExtra<Contact>("savedContact")
            newContact?.let {
                contacts.add(it)
                contactsAdapter.notifyItemInserted(contacts.size - 1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        recyclerView = findViewById(R.id.recyclerViewContacts)
        btnAddContact = findViewById(R.id.btnAddContact)

        contactsAdapter = ContactsAdapter(contacts) { contact ->
            val contactIndex = contacts.indexOfFirst { it.id == contact.id }
            if (contactIndex != -1) {
                contacts.removeAt(contactIndex)
                contactsAdapter.notifyItemRemoved(contactIndex)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = contactsAdapter

        btnAddContact.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            addContactLauncher.launch(intent)
        }
    }
}
