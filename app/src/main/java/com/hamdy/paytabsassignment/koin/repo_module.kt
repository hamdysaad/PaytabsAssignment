package com.hamdy.paytabsassignment.koin

import com.hamdy.paytabsassignment.features.payment_checkout.pre_auth.PreAuthRepo
import com.hamdy.paytabsassignment.features.payment_checkout.tokenization.TokenizationRepo
import org.koin.dsl.module


val repoModule = module {


    single { TokenizationRepo(get(), get()) }
    single { PreAuthRepo(get(), get()) }
}