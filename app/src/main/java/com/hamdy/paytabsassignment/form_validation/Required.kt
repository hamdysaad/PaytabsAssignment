package com.hamdy.paytabsassignment.form_validation

import androidx.annotation.StringRes


class Required(val message: String?= null , @StringRes val strRes: Int? = null) : IRule<String?> {

    override fun validate(value: String?): Any? {

        return if(!value.isNullOrEmpty() && !value.isNullOrBlank())
            null
        else
            message ?: strRes

    }



    override fun getMsgString(): String? {
        return message
    }

    override fun getMsgStringRes(): Int? {
        return strRes
    }

}