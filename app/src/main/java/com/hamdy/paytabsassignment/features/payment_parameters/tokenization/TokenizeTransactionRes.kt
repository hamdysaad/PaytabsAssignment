package com.hamdy.paytabsassignment.features.payment_parameters.tokenization

import com.hamdy.paytabsassignment.data.source.remote.BaseResponseModel
import com.hamdy.paytabsassignment.network.AppBaseResponse

class TokenizeTransactionRes : AppBaseResponse() {


    /*
    *  {
    "transaction_id": 1720627,
    "result": "Approved",
    "response_code": "100"
}*/

    val transaction_id : String ?= null
    val result : String ?= null
    val response_code : String ?= null
}