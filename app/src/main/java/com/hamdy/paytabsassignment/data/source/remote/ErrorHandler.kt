package com.hamdy.paytabsassignment.data.source.remote

interface ErrorHandler {
    fun getErrorFromBody(errorBody: String?): String?


    fun getHttpExceptionError(error: Throwable): String?{
        return  "An error occurred"
    }
}