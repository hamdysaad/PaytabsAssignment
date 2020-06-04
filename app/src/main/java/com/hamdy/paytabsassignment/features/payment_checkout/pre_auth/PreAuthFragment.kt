package com.hamdy.paytabsassignment.features.payment_checkout.pre_auth


import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import com.hamdy.paytabsassignment.R
import com.hamdy.paytabsassignment.base.BaseFragment
import com.hamdy.paytabsassignment.data.source.remote.Resource
import com.hamdy.paytabsassignment.data.source.remote.Status
import com.hamdy.paytabsassignment.databinding.FragmentPreAuthBinding
import com.hamdy.paytabsassignment.form_validation.FormValidationError
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class PreAuthFragment : BaseFragment<FragmentPreAuthBinding>() {

    val vm : PreAuthVM by viewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_pre_auth

    override fun initUI(savedInstanceState: Bundle?) {
        binding.model = vm.model
        binding.submitBtn.setOnClickListener { v: View? -> onSubmitClick() }

        vm.formValiResult.observe(this , Observer {
            if(it?.isSuccess == false)
                showValidationErrors(it.fields)
        })

        vm.releaseCapturePreauth.observe(viewLifecycleOwner , Observer {

            preAuthResponse(it)
        })
    }

    private fun preAuthResponse(it: Resource<PreAuthRes?>?) {

        hideDialogLoading()

        when(it?.status){

            Status.LOADING -> showDialogLoading()

            Status.ERROR -> showErrorMsgDialog(it.message ?: getString(R.string.anErrorOccured))

            Status.SUCCESS -> {
               showSuccessMsgDialog(it?.data?.result)
            }
        }
    }

    private fun showValidationErrors(fields: ArrayList<FormValidationError?>?) {

        showErrorMsgDialog(getString(R.string.please_enter_valid_data))

        fields?.get(0)?.formControlResID?.let {
            view?.findViewById<EditText>(it)?.requestFocus()
        }

        fields?.forEach{ formValidationError ->
            formValidationError?.messages?.get(0)?.getMsgStringRes()?.let { it1 -> getString(it1) }
                ?.let {
                    view?.findViewById<EditText>(formValidationError.formControlResID)?.error = it
                }
        }
    }

    private fun onSubmitClick() {
        vm.validateForm()
    }

    companion object {
        fun newInstance(): PreAuthFragment {
            return PreAuthFragment()
        }
    }
}