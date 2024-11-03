package com.example.lab1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Contact(
    var name: String,
    var phone: String,
    var email: String,
    val id: String = UUID.randomUUID().toString(),
) : Parcelable
