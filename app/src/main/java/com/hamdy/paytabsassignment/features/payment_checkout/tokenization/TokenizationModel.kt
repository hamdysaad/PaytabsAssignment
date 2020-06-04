package com.hamdy.paytabsassignment.features.payment_checkout.tokenization

class TokenizationModel {

    var TOKEN: String? = null
        set(value) {
            field = value?.trim()
        }
    var CUSTOMER_EMAIL: String? = null
        set(value) {
            field = value?.trim()
        }
    var CUSTOMER_PASSWORD: String? = null
        set(value) {
            field = value?.trim()
        }
    var FIRST_NAME: String? = null
        set(value) {
            field = value?.trim()
        }

    var LAST_NAME: String? = null
        set(value) {
            field = value?.trim()
        }
}