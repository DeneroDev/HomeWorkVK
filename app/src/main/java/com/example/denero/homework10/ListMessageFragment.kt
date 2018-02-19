package com.example.denero.homework10


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vk.sdk.api.*
import com.vk.sdk.api.model.VKApiMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_mess_fragment.*
import org.json.JSONArray


/**
 * Created by Denero on 17.02.2018.
 */
class ListMessageFragment():Fragment() {
    lateinit var recView:RecyclerView
    companion object {
        fun newInstance(photo:String,name:String,id:Int): android.support.v4.app.Fragment {
            val bundle = Bundle()
            bundle.putString("photo",photo)
            bundle.putString("name",name)
            bundle.putInt("Id",id)
            val result = ListMessageFragment()
            result.arguments = bundle
            return result
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.dialog_mess_fragment,container,false)
    }

    override fun onStart() {
        super.onStart()
        var adapter = AdapterMessList(DownloadData.instance.getHistoryUserId(),arguments!!.getString("photo"),arguments.getString("name"))
        recView = rec_view
        recView.layoutManager = LinearLayoutManager(activity.applicationContext)
        recView.adapter = adapter
        recView.adapter.notifyDataSetChanged()

        btn_send.setOnClickListener {
            var request:VKRequest = VKRequest("messages.send", VKParameters.from(VKApiConst.USER_ID,arguments.getInt("Id")
            ,VKApiConst.MESSAGE,edt_message.text.toString()))
            request.executeWithListener(object :VKRequest.VKRequestListener(){
                override fun onComplete(response: VKResponse?) {
                   Toast.makeText(context.applicationContext,"Сообщение отправлено",Toast.LENGTH_LONG).show()
                    DownloadData.instance.takeDialogs()
                }
            })
        }
    }
}