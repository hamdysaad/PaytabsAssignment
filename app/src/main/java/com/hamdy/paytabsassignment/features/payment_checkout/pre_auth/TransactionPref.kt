package com.hamdy.paytabsassignment.features.payment_checkout.pre_auth

import android.content.Context
import com.hamdy.paytabsassignment.base.BasePreferenceStorage

class TransactionPref(context: Context?) : BasePreferenceStorage(context) {


    private val TRANSACTION_ID: String = "transaction_id_pref"

    fun saveTransactionId(transactionId: String?) {
        putString(TRANSACTION_ID , transactionId )
    }


    fun getTransactionId(): String? {
        return getString(TRANSACTION_ID, null)
    }

}