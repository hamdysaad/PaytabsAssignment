package com.hamdy.paytabsassignment.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseFragment < D : ViewDataBinding>:Fragment() {

    var isShown: Boolean = false
    protected lateinit var binding: D



    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        initUI(savedInstanceState)
        binding.executePendingBindings()
        return binding.root
    }
    protected abstract fun initUI(savedInstanceState: Bundle?)

    fun addFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.addFragment(fragment , id , addToBackStack)
    }

    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.addFragment(fragmentManager ,fragment , id , addToBackStack)
    }



    fun replaceFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.replaceFragment(fragment , id , addToBackStack)
    }

    fun replaceFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.replaceFragment(fragmentManager ,fragment , id , addToBackStack)
    }

    fun hideKeyboard() {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.hideKeyboard()
            }
        }
    }

    fun showDialogLoading() {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.showDialogLoading()
            }
        }
    }

    fun hideDialogLoading() {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.hideDialogLoading()
            }
        }
    }

    fun showErrorMsgDialog(message : String?) {

        if(message == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showErrorMsgDialog(message)
            }
        }
    }

    fun showSuccessMsgDialog(message : String?) {

        if(message == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showSuccessMsgDialog(message)
            }
        }
    }

    fun showSuccessMsgDialog(message : String?,onYesClick:()->Unit) {

        if(message == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showSuccessMsgDialog(message,onYesClick)
            }
        }
    }

    fun showInfoMsgDialog(message : String?) {

        if(message == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showInfoMsgDialog(message)
            }
        }
    }

    fun showWarningMsgDialog(message : String?) {

        if(message == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showWarningMsgDialog(message)
            }
        }
    }

    fun showConfirmMessagDialog(message : String , yesAction : () -> Unit) {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.showConfirmMessagDialog(message , yesAction)
            }
        }
    }

    fun showConfirmMessagDialog(message : String , yesAction : () -> Unit ,onCncelClick:()->Unit) {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.showConfirmMessagDialog(message , yesAction ,onCncelClick)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isShown = false
    }

    override fun onResume() {
        super.onResume()
        isShown = true
    }

}