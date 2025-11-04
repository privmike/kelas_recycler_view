package com.example.kelas_recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class adapterRecView (private val listWayang : ArrayList<dcWayang>) : RecyclerView.Adapter<adapterRecView.ListViewHolder> (){
private lateinit var view : View
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: dcWayang)
        fun delData(pos: Int)

    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemViewType(position: Int): Int {
        return if (position%2==0){
            2
        }else{
            1
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        if (viewType==2){

         view= LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent,false)
        }else{

             view= LayoutInflater.from(parent.context).inflate(R.layout.item_recycler2, parent,false)
        }
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        var wayang = listWayang[position]
        holder.namaWayang.setText(wayang.nama)
        holder.karakterWayang.setText(wayang.karakter)
        holder.deskripsiWayang.setText(wayang.deskripsi)
        Log.d("Test", wayang.foto)
        Picasso.get().load(wayang.foto).resize(100,100).into(holder.gambarWayang)

        holder.gambarWayang.setOnClickListener {
//            Toast.makeText(holder.itemView.context, wayang.nama, Toast.LENGTH_SHORT).show()
            onItemClickCallback.onItemClicked(listWayang[holder.adapterPosition])
        }
        holder.btnHapus.setOnClickListener {
            onItemClickCallback.delData(position)
        }
    }

    override fun getItemCount(): Int {
        return listWayang.size
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val namaWayang = view.findViewById<TextView>(R.id.namaWayang)
        val karakterWayang =  view.findViewById<TextView>(R.id.karakterWayang)
        val deskripsiWayang =  view.findViewById<TextView>(R.id.deskripsiWayang)
        val gambarWayang =  view.findViewById<ImageView>(R.id.gambarWayang)

        val btnHapus = view.findViewById<Button>(R.id.btnHapus)

    }
}