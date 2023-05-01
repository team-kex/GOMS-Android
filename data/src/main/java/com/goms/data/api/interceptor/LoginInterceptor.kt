package com.goms.data.api.interceptor

import android.util.Log
import com.goms.data.datasource.token.ManageTokenDataSource
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class LoginInterceptor @Inject constructor(
    private val manageTokenDataSource: ManageTokenDataSource
): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        when(response.code) {
            200 -> Log.d("TAG", "intercept: success")
            400 -> Log.d("TAG", "intercept: 토큰을 요청하지 않음")
            401 -> {
                val currentRefreshToken = manageTokenDataSource.getRefreshToken()
                val newRequest = chain.request().newBuilder()
                    .addHeader("refreshToken", "Bearer $currentRefreshToken")
                    .build()
                val newResponse = chain.proceed(newRequest)

                if (newResponse.isSuccessful) {
                    val jsonParser = JsonParser()
                    val token = jsonParser.parse(response.body?.string()) as JsonObject
                    Log.d("TAG", "intercept token: $token")
                    manageTokenDataSource.setToken(
                        accessToken = token["accessToken"].toString(),
                        refreshToken = token["refreshToken"].toString(),
                        accessTokenExp = token["accessTokenExp"].toString(),
                        refreshTokenExp = token["refreshTokenExp"].toString()
                    )
                } else Log.d("TAG", "intercept newResponse fail")
            }
            500 -> Log.d("TAG", "intercept: server error")
            else -> Log.d("TAG", "intercept error: ${response.code}, ${response.body}")
        }

        return response
    }
}