package com.example.denero.homework10

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.dialog_show_fragment.*

/**
 * Created by Denero on 17.02.2018.
 */
class DialogMessageFragment:Fragment() {

    companion object {
        fun newInstance():Fragment{
            val result = DialogMessageFragment()
            return result
        }
    }

    lateinit var recView:RecyclerView
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.dialog_show_fragment,container,false)
    }

    override fun onStart() {
        super.onStart()
        var adapter = AdapterDialogs(DownloadData.instance.getFullData())
        recView = rec_view
        recView.layoutManager = LinearLayoutManager(activity.applicationContext)
        recView.adapter = adapter
        recView.adapter.notifyDataSetChanged()
    }
}