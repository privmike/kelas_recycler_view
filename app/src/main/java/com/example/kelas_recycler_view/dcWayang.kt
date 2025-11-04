package com.example.kelas_recycler_view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class dcWayang(
    var foto : String,
    var nama : String,
    var karakter : String,
    var deskripsi : String

) : Parcelable
