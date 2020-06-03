package com.hamdy.paytabsassignment.koin

import com.hamdy.paytabsassignment.features.payment_parameters.MainVM
import com.hamdy.paytabsassignment.features.payment_parameters.pre_auth.PreAuthVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainVM(get() , get()) }
    viewModel { PreAuthVM(get()) }

}