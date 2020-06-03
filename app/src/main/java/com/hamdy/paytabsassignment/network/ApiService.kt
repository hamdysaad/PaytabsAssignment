package com.hamdy.paytabsassignment.network

import androidx.lifecycle.LiveData
import com.hamdy.paytabsassignment.data.source.remote.ApiResponse
import com.hamdy.paytabsassignment.features.payment_parameters.pre_auth.PreAuthRes
import com.hamdy.paytabsassignment.features.payment_parameters.tokenization.TokenizeTransactionRes
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.HashMap


interface ApiService {

    @POST("https://www.paytabs.com/apiv3/tokenized_transaction_prepare")
    fun tokenizedTransactionPrepare(@Body map: java.util.HashMap<String, Any?>): LiveData<ApiResponse<TokenizeTransactionRes?>>

    @POST("https://www.paytabs.com/apiv3/release_capture_preauth")
    fun releaseCapturePreauth(@Body map: HashMap<String, Any?>): LiveData<ApiResponse<PreAuthRes?>>

}