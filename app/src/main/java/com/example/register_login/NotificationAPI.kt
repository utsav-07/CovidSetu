package com.example.register_login


import com.example.register_login.Constants.Companion.CONTENT_TYPE
import com.example.register_login.Constants.Companion.Server_key
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {
    @Headers("Authorization: key=$Server_key","Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification:pushNotification

    ):Response<ResponseBody>
}