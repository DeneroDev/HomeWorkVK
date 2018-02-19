package com.example.denero.homework10

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.message_in_item.view.*

/**
 * Created by Denero on 18.02.2018.
 */
class AdapterMessList(var data:ArrayList<Mess>,var photo:String,var name:String):RecyclerView.Adapter<AdapterMessList.MessListViewHolder>() {
    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MessListViewHolder
        = MessListViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.message_item,parent,false))

    override fun onBindViewHolder(holder: MessListViewHolder?, position: Int) {
            if(data.get(position).inner){
                Picasso.with(holder!!.v.context)
                        .load(photo)
                        .into(holder.imgVP)
                holder.tvText.setText(data.get(position).text)
                holder.tvName.setText(name)
            }
            else{
                holder!!.tvName.setText("Вы")
                holder.imgVP.setImageResource(R.mipmap.ic_launcher)
                holder.tvText.setText(data.get(position).text)
            }


    }

    class MessListViewHolder(var v: View):RecyclerView.ViewHolder(v) {
        var imgVP = v.imgv_user_photo
        var tvName: TextView = v.tv_namedialog
        var tvText:TextView = v.tv_textdialog
    }
}