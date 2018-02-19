package com.example.denero.homework10

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError

/**
 * Created by Denero on 17.02.2018.
 */
class AuthFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(!VKSdk.onActivityResult(requestCode,resultCode,data,
                object : VKCallback<VKAccessToken>{
                    override fun onError(error: VKError?) {
                        Snackbar.make(view,"Failed",Snackbar.LENGTH_LONG).show()
                    }

                    override fun onResult(res: VKAccessToken?) {
                        Snackbar.make(view,"SUCESS",Snackbar.LENGTH_LONG).show()
                    }

                }))
        super.onActivityResult(requestCode, resultCode, data)
    }

}