package com.example.denero.homework10

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.message_item.view.*

/**
 * Created by Denero on 18.02.2018.
 */
class AdapterDialogs(var data:ArrayList<Dialog>):RecyclerView.Adapter<AdapterDialogs.DialogsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DialogsViewHolder =
            DialogsViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.message_item,parent,false))

    override fun onBindViewHolder(holder: DialogsViewHolder?, position: Int) {
        if(data.get(position).photo!="")
        Picasso.with(holder!!.v.context)
                .load(data.get(position).photo)
                .into(holder.imgPhoto)
        else
            holder!!.imgPhoto.setImageResource(R.mipmap.ic_launcher)

        holder!!.tvName.setText(data.get(position).first_name)
        if(data.get(position).text!="")
        holder!!.tvText.setText(data.get(position).text)
        else
            holder!!.tvText.setText("attached item")

        holder.v.setOnClickListener {
            if (holder.v.context is MainActivity) {
                (holder.v.context as MainActivity).onOpenFragment(data.get(position).idUser
                        ,data.get(position).photo,data.get(position).first_name)
            }
        }
    }

    override fun getItemCount(): Int = data.size


    class DialogsViewHolder(var v: View): RecyclerView.ViewHolder(v) {
        var imgPhoto:ImageView = v.imgv_user_photo
        var tvName:TextView = v.tv_namedialog
        var tvText:TextView = v.tv_textdialog
    }

    interface onOpenHistoryCallback{
        fun onOpenFragment(id:Int,photo:String,name:String)
    }
}