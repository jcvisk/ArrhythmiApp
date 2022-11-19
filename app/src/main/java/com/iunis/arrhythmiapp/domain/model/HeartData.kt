package com.iunis.arrhythmiapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class HeartData (
    val id: String = UUID.randomUUID().toString(),
    val systolic: Int? = null,
    val diastolic: Int? = null,
    val pulse: Int? = null,
    val note: String = "",
    val date: String = "",
):Parcelable