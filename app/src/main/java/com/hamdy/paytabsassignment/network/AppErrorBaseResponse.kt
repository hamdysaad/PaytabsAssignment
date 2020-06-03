package com.hamdy.paytabsassignment.network


class AppErrorBaseResponse : AppBaseResponse() {

    override fun getError(): String? {
        return null
    }

    override fun getSuccess(): Any? {return false}
}
