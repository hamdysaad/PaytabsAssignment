package com.hamdy.paytabsassignment

import android.app.Application
import com.hamdy.paytabsassignment.koin.appModule
import com.hamdy.paytabsassignment.koin.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.hamdy.paytabsassignment.koin.viewModelModule


class PayTabsAssignmentApp : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            modules( listOf( viewModelModule , appModule , repoModule ))
            androidContext(applicationContext)
        }


    }


    companion object {
        var instance : PayTabsAssignmentApp? = null

    }
}