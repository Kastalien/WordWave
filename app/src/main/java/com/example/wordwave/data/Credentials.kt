package com.example.wordwave.data

import android.content.Context
import android.os.Environment
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

object Credentials {
    private val apiService = RetrofitInstance.api
    private val favqsLogin="ivanop25@gmail.com"
    private val favqsPassword="b81ea8fda91"
    var favqsUsername=""
    var login:String=""
    var password:String=""
    var session=""

    private fun read(context:Context){
        val path = context.filesDir.absolutePath
        var file = File(path+"/credentials.txt")
        if (file.exists()) {
            var credentials = file.useLines { it.toList() }
            Log.d("wwww", "just read")
            if (credentials.count()==2) {
                login = credentials[0]
                password = credentials[1]
            }
        }
        Log.d("wwww", "read finish")
    }

    fun save(context:Context){
        Log.d("wwww","start write ")
        val path = context.filesDir.absolutePath
        var file = File(path+"/credentials.txt")
        if (!file.exists()) {
            Log.d("wwww","need to create file ${file.absolutePath}")
            file.createNewFile()
            Log.d("wwww","file created")
        }
        Log.d("wwww","created if necessary")
        file.printWriter().use { out ->
            {
                out.println(login)
                out.println(password)
            }
        }
    }

    fun check(context:Context, checkingLogin:String, checkingPassword:String):Boolean {
        Log.d("wwww","start read")
        read(context)
        if (login==""){
            Log.d("wwww","login is empty")
            login=checkingLogin
            password=checkingPassword
            save(context)
            return true
        }
        return (login==checkingLogin) && (password==checkingPassword)
    }

    fun authFavQs(){
        val userRequest=UserRequest(User(login=favqsLogin,password= favqsPassword))
        apiService.getSession(userRequest).enqueue(
            object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.d("quote","login ${response.body()?.login?:"NO LOGIN"}")
                Log.d("quote","session ${response.body()?.userToken?:"NO SESSION"}")
                session=response.body()?.userToken?:""
                favqsUsername=response.body()?.login?:""
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("quote","failure make session${t.message}")
            }
            })
    }
}
