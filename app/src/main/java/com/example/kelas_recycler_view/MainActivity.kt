package com.example.kelas_recycler_view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var nama : Array<String>
    private lateinit var karakter : Array<String>
    private lateinit var deskripsi : Array<String>
    private lateinit var gambar : Array<String>
    private lateinit var rvWayang : RecyclerView

    private var arWayang = arrayListOf<dcWayang>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        rvWayang = findViewById<RecyclerView>(R.id.rvWayang)
        SiapkanData()
        TambahData()
        TampilkanData()



    }

    fun SiapkanData(){
        nama = resources.getStringArray(R.array.namaWayang)
        deskripsi = resources.getStringArray(R.array.deskripsiWayang)
        karakter = resources.getStringArray(R.array.karakterUtamaWayang)
        gambar= resources.getStringArray(R.array.gambarWayang)
    }

    fun TambahData(){
        for (position in nama.indices){
            val data = dcWayang(
                gambar[position],
                nama[position],
                karakter[position],
                deskripsi[position]
            )
            arWayang.add(data)

        }
    }


    fun TampilkanData(){
        rvWayang.layoutManager = LinearLayoutManager(this)


        val adapterWayang = adapterRecView(arWayang)
        rvWayang.adapter = adapterWayang

        adapterWayang.setOnItemClickCallback(object : adapterRecView.OnItemClickCallback{
            override fun onItemClicked(data: dcWayang ){
                Toast.makeText(this@MainActivity, data.nama, Toast.LENGTH_SHORT).show()
            }
        })

    }
}