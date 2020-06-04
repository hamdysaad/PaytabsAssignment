package com.hamdy.paytabsassignment.koin

import com.hamdy.paytabsassignment.features.payment_checkout.MainVM
import com.hamdy.paytabsassignment.features.payment_checkout.pre_auth.PreAuthVM
import com.hamdy.paytabsassignment.features.payment_checkout.tokenization.TokenizationVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { TokenizationVM(get() , get()) }
    viewModel { MainVM(get() , get()) }
    viewModel { PreAuthVM(get()) }
}