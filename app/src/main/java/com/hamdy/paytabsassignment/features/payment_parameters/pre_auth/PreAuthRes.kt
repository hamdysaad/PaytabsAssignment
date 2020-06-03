package com.hamdy.paytabsassignment.features.payment_parameters.pre_auth

import com.hamdy.paytabsassignment.data.source.remote.BaseResponseModel
import com.hamdy.paytabsassignment.network.AppBaseResponse

class PreAuthRes : AppBaseResponse() {


    val result : String ?= null
    val response_code : String ?= null
}