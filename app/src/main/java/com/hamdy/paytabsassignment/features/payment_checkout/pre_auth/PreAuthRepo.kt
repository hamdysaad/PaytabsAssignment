package com.hamdy.paytabsassignment.features.payment_checkout.pre_auth

import androidx.lifecycle.LiveData
import com.hamdy.paytabsassignment.data.source.remote.ApiResponse
import com.hamdy.paytabsassignment.data.source.remote.ContextProviders
import com.hamdy.paytabsassignment.data.source.remote.NetworkBoundResource
import com.hamdy.paytabsassignment.data.source.remote.Resource
import com.hamdy.paytabsassignment.network.ApiService

class PreAuthRepo(val apiService: ApiService, val contextProviders: ContextProviders) {



    fun releaseCapturePreauth(it: PreAuthModel?): LiveData<Resource<PreAuthRes?>> {

        val map = HashMap<String , Any?>()
        map["merchant_email"] = it?.MERCHANT_EMAIL
        map["secret_key"] = it?.SECRET_KEY
        map["transaction_id"] = it?.TRANSACTION_ID
        map["capture_amount"] = it?.CAPTURED_AMOUNT
        map["void_transaction"] = it?.VOID_TRANSACTION


       return object  : NetworkBoundResource<PreAuthRes? , PreAuthRes?>(contextProviders){
            override fun saveCallResult(res: PreAuthRes?) {
                itemsData = res
            }

            override fun getResult(): PreAuthRes? {
               return itemsData
            }

            override fun createCall(): LiveData<ApiResponse<PreAuthRes?>> {
               return apiService.releaseCapturePreauth(map)
            }

        }.asLiveData()
    }


}