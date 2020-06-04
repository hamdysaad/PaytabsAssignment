package com.hamdy.paytabsassignment.features.payment_checkout.tokenization

import android.content.Context
import com.hamdy.paytabsassignment.base.BasePreferenceStorage

class TokenizationPref( context: Context?) : BasePreferenceStorage(context) {


    private val TOKEN_PREF: String = "token_pref"
    private val CUSTOMER_EMAIL_PREF: String = "CUSTOMER_EMAIL_pref"
    private val CUSTOMER_PASSWORD_PREF: String = "CUSTOMER_PASSWORD_pref"

    fun saveTokenization(tokenizationModel: TokenizationModel) {
        putString(TOKEN_PREF , tokenizationModel.TOKEN )
        putString(CUSTOMER_EMAIL_PREF , tokenizationModel.CUSTOMER_EMAIL )
        putString(CUSTOMER_PASSWORD_PREF , tokenizationModel.CUSTOMER_PASSWORD )
    }


    fun getTokenization(): TokenizationModel {

        return TokenizationModel().apply {
            TOKEN = getString(TOKEN_PREF, null)
            CUSTOMER_EMAIL =  getString(CUSTOMER_EMAIL_PREF, null)
            CUSTOMER_PASSWORD = getString(CUSTOMER_PASSWORD_PREF, null)
        }
    }
}