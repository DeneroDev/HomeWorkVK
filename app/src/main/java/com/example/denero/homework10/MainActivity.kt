package com.example.denero.homework10

import android.annotation.SuppressLint
import android.app.Fragment
import android.app.FragmentManager
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

import com.vk.sdk.*
import com.vk.sdk.api.VKApi
import com.vk.sdk.api.VKError
import com.vk.sdk.api.VKRequest
import com.vk.sdk.api.VKResponse
import com.vk.sdk.api.methods.VKApiMessages
import com.vk.sdk.api.model.VKApiGetDialogResponse
import com.vk.sdk.api.model.VKApiGetMessagesResponse
import com.vk.sdk.api.model.VKApiMessage
import com.vk.sdk.api.model.VKApiPhoto
import kotlinx.android.synthetic.main.activity_main.*
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity(),AdapterDialogs.onOpenHistoryCallback {
    override fun onOpenFragment(id: Int,photo:String,name:String) {
        DownloadData.instance.getDownloadHistoryUserId(id)
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frameMess,ListMessageFragment.newInstance(photo,name,id))
                    .addToBackStack("one")
                    .commit()
        }
        else
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame,ListMessageFragment.newInstance(photo,name,id))
                .addToBackStack("one")
                .commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var toolbar = toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frameDial,DialogMessageFragment.newInstance())
                    .commit()
        }
        else{
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frame,DialogMessageFragment.newInstance())
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.order){
            100 ->{
                if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frameDial,DialogMessageFragment.newInstance())
                            .commit()
                }
                else{
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame,DialogMessageFragment.newInstance())
                            .commit()
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }



    override fun onDestroy() {

        super.onDestroy()
    }
}
