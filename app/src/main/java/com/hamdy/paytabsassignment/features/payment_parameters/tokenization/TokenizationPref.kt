package com.hamdy.paytabsassignment.features.payment_parameters.tokenization

import android.content.Context
import com.hamdy.paytabsassignment.base.BasePreferenceStorage
import com.hamdy.paytabsassignment.features.payment_parameters.tokenization.TokenizationModel

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

        return TokenizationModel(
            getString(TOKEN_PREF, null),
            getString(CUSTOMER_EMAIL_PREF, null),
            getString(CUSTOMER_PASSWORD_PREF, null)
        )
    }
}