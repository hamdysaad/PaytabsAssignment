package com.hamdy.paytabsassignment.form_validation

interface IRule<T> {

    fun validate(value: T?) : Any?
    fun getMsgStringRes(): Int?
    fun getMsgString(): String?
}