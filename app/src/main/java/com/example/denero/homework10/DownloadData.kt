package com.example.denero.homework10

import android.os.Debug
import android.util.Log
import com.squareup.picasso.Picasso
import com.vk.sdk.api.*
import com.vk.sdk.api.methods.VKApiUsers
import com.vk.sdk.api.model.*
import org.json.JSONArray

/**
 * Created by Denero on 17.02.2018.
 */
public class DownloadData private constructor(){

    private object Holder{val INSTANCE = DownloadData()}
    var DStrings = ArrayList<String>()
    var IdAr = ArrayList<Int>()
    var temp = ArrayList<Mess>()
    companion object {
        val instance:DownloadData by lazy { Holder.INSTANCE }
    }

    var dialog = ArrayList<Dialog>()
     var i:String = ""

    fun takeDialogs(){
        dialog = ArrayList<Dialog>()
        DStrings = ArrayList<String>()
        IdAr = ArrayList<Int>()

        var request: VKRequest = VKApi.messages().getDialogs(VKParameters.from(VKApiConst.COUNT,50,VKApiConst.FIELDS,"photo_100"))
        request.executeWithListener(object: VKRequest.VKRequestListener(){
            override fun onComplete(response: VKResponse?) {

                var test = response!!.parsedModel as VKApiGetDialogResponse
                for(t in test.items){
                    if(!t.message.title.isEmpty()){
                       /* temp.first_name = t.message.title
                        temp.text = t.message.body
                        Log.d(temp.first_name,t.message.user_id.toString())
                        dialog.add(temp)*/
                    }
                    else{
                        if(i==""){
                            i = t.message.user_id.toString() + ","
                            DStrings.add(t.message.body)
                            IdAr.add(t.message.user_id)
                        }
                        else{
                            i = i+","+t.message.user_id
                            DStrings.add(t.message.body)
                            IdAr.add(t.message.user_id)
                        }

                    }
                }
                completData()
            }
        })
    }

    fun completData():ArrayList<Dialog>{
        var request:VKRequest = VKApi.users().get(VKParameters.from(VKApiConst.USER_IDS,i,VKApiConst.FIELDS,"photo_100"))
        request.executeWithListener(object :VKRequest.VKRequestListener(){
            override fun onComplete(response: VKResponse?) {
                var u = response!!.parsedModel as VKList<VKApiUser>
                var i=0
                for (t in u){
                    var temp = Dialog()
                    temp.text =  DStrings.get(i)
                    temp.idUser = IdAr.get(i)
                    temp.first_name = t.first_name
                    temp.last_name= t.last_name
                    temp.photo = t.photo_100
                    temp.single = true
                    Log.d(temp.first_name,t.id.toString())
                    dialog.add(temp)
                    i++
                }

            }
        })
        return dialog
    }

    fun getFullData():ArrayList<Dialog>{
        return dialog
    }

    fun getDownloadHistoryUserId(id:Int){
        var result = ArrayList<Mess>()
        var request:VKRequest = VKRequest("messages.getHistory",VKParameters.from(VKApiConst.COUNT,50,VKApiConst.USER_ID,id))
        request.executeWithListener(object :VKRequest.VKRequestListener(){
            override fun onComplete(response: VKResponse?) {
                var array:JSONArray = response!!.json.getJSONObject("response").getJSONArray("items")
                var vkApiM = ArrayList<VKApiMessage>()
                for(i in 0..array.length()-1){
                    var mes = VKApiMessage(array.getJSONObject(i))
                    vkApiM.add(mes)
                }
                for(item in vkApiM){
                    if(item.out){
                        Log.d("OUT",item.body)
                        result.add(Mess(item.body,false))
                    }
                    else{
                        Log.d("IN",item.body)
                        result.add(Mess(item.body,true))
                    }

                }
                temp = result
            }
        })
        Thread.sleep(1000)
    }

    fun getHistoryUserId():ArrayList<Mess>{
        return temp
    }


}