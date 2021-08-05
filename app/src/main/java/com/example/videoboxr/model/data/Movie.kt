package com.example.videoboxr.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val description: String,
    val dataCreate: String,
    val rating: Int
): Parcelable
