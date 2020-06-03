package com.hamdy.paytabsassignment.network

import com.google.gson.Gson
import com.hamdy.paytabsassignment.PayTabsAssignmentApp
import com.hamdy.paytabsassignment.R
import com.hamdy.paytabsassignment.data.source.remote.ErrorHandler
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
class NetworkErrorHandler : ErrorHandler {

    override fun getErrorFromBody(errorBody: String?): String? {
        return Gson().fromJson(errorBody  , AppErrorBaseResponse::class.java).getError()
    }

    override fun getHttpExceptionError(error: Throwable): String? {

        val context  = PayTabsAssignmentApp.instance?.applicationContext

       return when(error){

            is SocketTimeoutException -> context?.getString(R.string.SocketTimeoutException)
            is  UnknownHostException -> context?.getString(R.string.UnknownHostException)
            is  IOException -> context?.getString(R.string.noInternetConnection)

           else -> context?.getString(R.string.anErrorOccured)
       }

    }

}