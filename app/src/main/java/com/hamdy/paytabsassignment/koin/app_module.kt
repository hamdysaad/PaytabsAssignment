package com.hamdy.paytabsassignment.koin

import com.hamdy.paytabsassignment.data.source.remote.ApiServiceFactory
import com.hamdy.paytabsassignment.data.source.remote.ContextProviders
import com.hamdy.paytabsassignment.features.payment_parameters.tokenization.TokenizationPref
import com.hamdy.paytabsassignment.network.ApiService
import com.hamdy.paytabsassignment.network.AppAPIConfig
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule: Module = module {


    single { ApiServiceFactory.getService<ApiService>( AppAPIConfig()) }//ApiService
    single { ContextProviders.getInstance() }//ContextProvidersd
    factory {
        TokenizationPref(
            get()
        )
    }//ApiService



}
