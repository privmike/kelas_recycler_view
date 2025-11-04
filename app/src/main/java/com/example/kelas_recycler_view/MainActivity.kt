package com.example.kelas_recycler_view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var nama : MutableList<String>
    private lateinit var karakter : MutableList<String>
    private lateinit var deskripsi : MutableList<String>
    private lateinit var gambar : MutableList<String>
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
        nama = resources.getStringArray(R.array.namaWayang).toMutableList()
        deskripsi = resources.getStringArray(R.array.deskripsiWayang).toMutableList()
        karakter = resources.getStringArray(R.array.karakterUtamaWayang).toMutableList()
        gambar= resources.getStringArray(R.array.gambarWayang).toMutableList()
    }

    fun TambahData(){
        arWayang.clear()
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
//                Toast.makeText(this@MainActivity, data.nama, Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, detWayang::class.java)
                intent.putExtra("kirimData", data)
                startActivity(intent)
            }

            override fun delData(pos: Int) {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Hapus Data")
                    .setMessage("Apakah benar data" + nama[pos]+" akan dihapus ?")
                    .setPositiveButton(
                        "Hapus",
                        DialogInterface.OnClickListener{ dialog, which ->
                            gambar.removeAt(pos)
                            nama.removeAt(pos)
                            deskripsi.removeAt(pos)
                            karakter.removeAt(pos)
                            TambahData()
                            TampilkanData()

                        }
                    )
                    .setNegativeButton(
                        "Batal",
                        DialogInterface.OnClickListener{dialog, which ->
                            Toast.makeText(this@MainActivity,"Data abtal dihapus", Toast.LENGTH_LONG).show()
                        }
                    ).show()
            }
        })

    }
}