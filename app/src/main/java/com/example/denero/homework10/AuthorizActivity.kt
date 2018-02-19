package com.example.denero.homework10

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Denero on 18.02.2018.
 */
class AuthorizActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VKSdk.login(this, VKScope.MESSAGES, VKScope.FRIENDS)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(!VKSdk.onActivityResult(requestCode,resultCode,data,
                object : VKCallback<VKAccessToken> {
                    override fun onError(error: VKError?) {
                        Snackbar.make(root_view,"Failed", Snackbar.LENGTH_SHORT).show()
                    }

                    override fun onResult(res: VKAccessToken?) {
                        Toast.makeText(applicationContext,"SUCCESS",Toast.LENGTH_LONG).show()
                        DownloadData.instance.takeDialogs()
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                    }

                }))
            super.onActivityResult(requestCode, resultCode, data)
    }
}