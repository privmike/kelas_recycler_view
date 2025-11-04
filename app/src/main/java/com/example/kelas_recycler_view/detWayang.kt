package com.example.kelas_recycler_view

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class detWayang : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_det_wayang)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detFotoWayang = findViewById<ImageView>(R.id.detFotoWayang)
        val detNamaWayang = findViewById<TextView>(R.id.detNamaWayang)
        val detDetailWayang = findViewById<TextView>(R.id.detDetailWayang)

        val dataIntent = intent.getParcelableExtra<dcWayang>("kirimData", dcWayang::class.java)
        if (dataIntent!=null){
            Picasso.get().load(dataIntent.foto).into(detFotoWayang)

            detNamaWayang.setText(dataIntent.nama)
            detDetailWayang.setText(dataIntent.deskripsi)
        }
    }
}