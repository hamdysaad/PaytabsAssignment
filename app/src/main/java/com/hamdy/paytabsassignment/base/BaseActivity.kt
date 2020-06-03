package com.hamdy.paytabsassignment.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mte.infrastructurebase.utils.KeyboardUtils

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {

    protected var dialogAlert: IDialogAlert?      = null
    protected var dialogLoading: IDialogLoading?      = null


    lateinit var binding: T

    @get:LayoutRes
    protected abstract val layoutRes: Int

    @get:StringRes
    protected open val yesString: Int? = null
    protected open val noString:  Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        performDataBinding()
        initUI(savedInstanceState)
    }

    abstract fun initUI(savedInstanceState: Bundle?)

    open fun init() {
        dialogLoading   = DefaultDialogLoading(this)
        dialogAlert     = DefaultDialogAlert(this , yesString?.let { getString(it) } ?: "Yes", noString?.let { getString(it) } ?: "No")
    }


    protected open fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.executePendingBindings()
    }


    /**
     * Show dialog loading
     */
    open fun showDialogLoading() {
        runOnUiThread {
            dialogLoading?.showLoading()
        }
    }


    /**
     * Hide dialog loading
     */
    open fun hideDialogLoading() {
        runOnUiThread {
            dialogLoading?.hideLoading()
        }
    }

    /**
     * show message dialog
     */
    fun showInfoMsgDialog(message: String?) {
        if(message == null) return

        runOnUiThread { dialogAlert?.showInfoMsg(message) }
    }

    /**
     * show message dialog
     */
    fun showSuccessMsgDialog(message: String?) {
        if(message == null) return
        runOnUiThread { dialogAlert?.showSuccessMsg(message) }
    }
    fun showSuccessMsgDialog(message: String? ,onYesClick:()->Unit) {
        if(message == null) return
        runOnUiThread { dialogAlert?.showSuccessMsg(message,onYesClick = onYesClick) }
    }

    /**
     * show Error message dialog
     */
    fun showErrorMsgDialog(message: String?) {
        if(message == null) return
        runOnUiThread { dialogAlert?.showErrorMsg(message)

        }
    }

    /**
     * show Warning message dialog
     */
    fun showWarningMsgDialog(message: String?) {
        if(message == null) return
        runOnUiThread { dialogAlert?.showWarningMsg(message) }
    }

    /**
     * Show confirm message dialog loading
     */
    fun showConfirmMessagDialog(message: String?, yesAction: () -> Unit) {
        if(message == null) return
        runOnUiThread {
            dialogAlert?.showConfirmationMsg(message, object : ConfirmHandler {
                override fun onConfirmed() {
                    yesAction.invoke()
                }
            })
        }
    }

    fun showConfirmMessagDialog(message: String?, yesAction: () -> Unit ,onCancelClick:()->Unit) {
        if(message == null) return
        runOnUiThread {
            dialogAlert?.showConfirmationMsg(message, object : ConfirmHandler {
                override fun onConfirmed() {
                    yesAction.invoke()
                }
            },onDismissHandel = onCancelClick)
        }
    }


    fun hideKeyboard() {
        KeyboardUtils.hideKeyboard(this)
    }

    fun hideKeyboardOutSide(view: View) {
        KeyboardUtils.hideKeyBoardWhenClickOutSide(view, this)
    }

    fun hideKeyboardOutSideText(view: View) {
        KeyboardUtils.hideKeyBoardWhenClickOutSideText(view, this)
    }


    fun addFragment(fragment: Fragment, id: Int, addToBackStack: Boolean) {
        addFragment(supportFragmentManager , fragment , id , addToBackStack)
    }


    //Add Fragment by fragmentManager
    fun addFragment(fragmentManager : FragmentManager,
                    fragment : Fragment,
                    id: Int,
                    addToBackStack: Boolean) {

        val transaction = fragmentManager.beginTransaction()

        transaction.add(id, fragment, fragment.javaClass.simpleName)

        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.simpleName)

        transaction.commit()

    }

    fun replaceFragment(fragment: Fragment, id: Int, addToBackStack: Boolean) {
        replaceFragment(supportFragmentManager , fragment , id , addToBackStack)
    }

    fun replaceFragment(fragmentManager : FragmentManager,
                        fragment: Fragment,
                        id: Int,
                        addToBackStack: Boolean) {

        val transaction = fragmentManager.beginTransaction()


        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.canonicalName)

        transaction.replace(id, fragment, fragment.javaClass.canonicalName)
        transaction.commit()
    }

}