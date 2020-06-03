package com.hamdy.paytabsassignment.base

import android.content.Context

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


open class BasePreferenceStorage(val context: Context?) {


    protected var pref: SharedPreferences?

    open val PREF = "pref"

    init {
       pref =  context?.getSharedPreferences(PREF, MODE_PRIVATE)
    }


    fun putString(key: String, value: String?) {
        val editor = pref?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun getString(key : String , default : String?) : String?{
        return pref?.getString(key , default)
    }

    fun putBoolean(key : String , value : Boolean) {
        val editor = pref?.edit()
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    fun getBoolean(key : String , default : Boolean = false) : Boolean{
        return pref?.getBoolean(key , default) == true
    }


}
