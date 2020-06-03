package com.hamdy.paytabsassignment.features.payment_parameters.tokenization

import androidx.lifecycle.LiveData
import com.hamdy.paytabsassignment.data.source.remote.ApiResponse
import com.hamdy.paytabsassignment.data.source.remote.ContextProviders
import com.hamdy.paytabsassignment.data.source.remote.NetworkBoundResource
import com.hamdy.paytabsassignment.data.source.remote.Resource
import com.hamdy.paytabsassignment.network.ApiService

class TokenizationRepo(val apiService: ApiService , val contextProviders: ContextProviders) {



    fun tokenizeTransaction(it: TokenizedTranactionRequest?): LiveData<Resource<TokenizeTransactionRes?>> {

        val map = HashMap<String , Any?>()
        map["merchant_email"] = it?.paymentParamModel?.MERCHANT_EMAIL
        map["secret_key"] = it?.paymentParamModel?.SECRET_KEY
        map["title"] = it?.paymentParamModel?.TRANSACTION_TITLE
        map["cc_first_name"] = it?.firstName
        map["cc_last_name"] = it?.lastName
        map["order_id"] = it?.paymentParamModel?.ORDER_ID
        map["product_name"] = it?.paymentParamModel?.PRODUCT_NAME
        map["customer_email"] = it?.paymentParamModel?.CUSTOMER_EMAIL
        map["phone_number"] = it?.paymentParamModel?.CUSTOMER_PHONE_NUMBER
        map["amount"] = it?.paymentParamModel?.AMOUNT
        map["currency"] = it?.paymentParamModel?.CURRENCY_CODE
        map["address_billing"] = it?.paymentParamModel?.ADDRESS_BILLING
        map["state_billing"] = it?.paymentParamModel?.STATE_BILLING
        map["city_billing"] = it?.paymentParamModel?.CITY_BILLING
        map["postal_code_billing"] = it?.paymentParamModel?.POSTAL_CODE_BILLING
        map["country_billing"] = it?.paymentParamModel?.COUNTRY_BILLING
        map["address_shipping"] = it?.paymentParamModel?.ADDRESS_SHIPPING
        map["city_shipping"] = it?.paymentParamModel?.CITY_SHIPPING
        map["state_shipping"] = it?.paymentParamModel?.STATE_SHIPPING
        map["postal_code_shipping"] = it?.paymentParamModel?.POSTAL_CODE_SHIPPING
        map["country_shipping"] = it?.paymentParamModel?.COUNTRY_SHIPPING
        map["pt_token"] = it?.TOKEN
        map["pt_customer_email"] = it?.CUSTOMER_EMAIL
        map["pt_customer_password"] = it?.CUSTOMER_PASSWORD


       return object  : NetworkBoundResource<TokenizeTransactionRes? , TokenizeTransactionRes?>(contextProviders){
            override fun saveCallResult(res: TokenizeTransactionRes?) {
                itemsData = res
            }

            override fun getResult(): TokenizeTransactionRes? {
               return itemsData
            }

            override fun createCall(): LiveData<ApiResponse<TokenizeTransactionRes?>> {
               return apiService.tokenizedTransactionPrepare(map)
            }

        }.asLiveData()
    }


}