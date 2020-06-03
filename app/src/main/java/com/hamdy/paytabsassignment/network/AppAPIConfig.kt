package com.hamdy.paytabsassignment.network

import com.hamdy.paytabsassignment.data.source.remote.APIConfig
import com.hamdy.paytabsassignment.data.source.remote.ErrorHandler
import com.hamdy.paytabsassignment.network.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.HashMap

class AppAPIConfig() : APIConfig() {


    companion object{
        const val HOST = ApiConstants.HOST
        const val BASE_URL = ApiConstants.BASE_URL
    }

    override fun addOKHttpConfig(client: OkHttpClient.Builder) {}

    override fun addRetrofitConfig(retrofitBuilder: Retrofit.Builder) {}

    override fun <T> getApiService(): Class<T> {
        return ApiService::class.java as Class<T>
    }

    override fun getBaseUrl(): String {
        return BASE_URL
    }

    override fun getErrorHandler(): ErrorHandler? {
        return NetworkErrorHandler()
    }

    override fun getHeaders(): HashMap<String, String>? {
        return  HashMap<String, String>()
    }

    override fun getHost(): String {
        return BASE_URL
    }}