package com.hamdy.paytabsassignment.network

import com.hamdy.paytabsassignment.data.source.remote.BaseResponseModel


abstract class AppBaseResponse : BaseResponseModel() {

    override fun getError(): String? {
       return null
    }

    override fun isEmpty(): Boolean? {
        return null
    }

    override fun getSuccess(): Any? {
        return null
    }



}