package com.hamdy.paytabsassignment.features.payment_checkout

class PaymentParamModel {

    var MERCHANT_EMAIL : String? = null
        set(value) {
            field = value?.trim()
        }
    var SECRET_KEY : String? = null
        set(value) {
            field = value?.trim()
        }
    var LANGUAGE : String? = null
        set(value) {
            field = value?.trim()
        }
    var TRANSACTION_TITLE : String? = null
        set(value) {
            field = value?.trim()
        }
    var AMOUNT : String? = null
        set(value) {
            field = value?.trim()
        }
    var CURRENCY_CODE : String? = null
        set(value) {
            field = value?.trim()
        }
    var CUSTOMER_PHONE_NUMBER : String? = null
        set(value) {
            field = value?.trim()
        }
    var CUSTOMER_EMAIL : String? = null
        set(value) {
            field = value?.trim()
        }
    var ORDER_ID : String? = null
        set(value) {
            field = value?.trim()
        }
    var PRODUCT_NAME : String? = null
        set(value) {
            field = value?.trim()
        }

    //Address Billing
    var ADDRESS_BILLING : String? = null
        set(value) {
            field = value?.trim()
        }
    var CITY_BILLING : String? = null
        set(value) {
            field = value?.trim()
        }
    var STATE_BILLING : String? = null
        set(value) {
            field = value?.trim()
        }
    var COUNTRY_BILLING : String? = null
        set(value) {
            field = value?.trim()
        }
    var POSTAL_CODE_BILLING : String? = null
        set(value) {
            field = value?.trim()
        }

    //Address Shipping
    var ADDRESS_SHIPPING : String? = null
        set(value) {
            field = value?.trim()
        }
    var CITY_SHIPPING : String? = null
        set(value) {
            field = value?.trim()
        }
    var STATE_SHIPPING : String? = null
        set(value) {
            field = value?.trim()
        }
    var COUNTRY_SHIPPING : String? = null
        set(value) {
            field = value?.trim()
        }
    var POSTAL_CODE_SHIPPING : String? = null
        set(value) {
            field = value?.trim()
        }


    var IS_TOKENIZATION : Boolean = false
}