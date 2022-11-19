package com.iunis.arrhythmiapp.domain.model

data class HeartData (
    val sistolica: Int? = null,
    val diastolica: Int? = null,
    val pulso: Int? = null,
    val nota: String = "",
    val fecha: String = "",
)