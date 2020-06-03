package com.hamdy.paytabsassignment.data.source.remote

import com.hamdy.paytabsassignment.data.source.remote.ErrorHandler
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.HashMap


abstract class APIConfig {

    abstract fun getHost() : String
    abstract fun getBaseUrl() : String
    abstract fun <T> getApiService() :  Class<T>
    abstract fun getHeaders(): HashMap<String, String>?
    abstract fun getErrorHandler(): ErrorHandler?
    abstract fun addOKHttpConfig(client: OkHttpClient.Builder)
    abstract fun addRetrofitConfig(retrofitBuilder: Retrofit.Builder)

}