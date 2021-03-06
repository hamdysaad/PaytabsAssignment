package com.hamdy.paytabsassignment.base

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class DefaultDialogAlert(val context: Context, val yesBtnText: String, val noBtnText: String) :
    IDialogAlert {


    private var builder: AlertDialog.Builder? = null
    private var messageDialog: AlertDialog? = null
    var themeId: Int = -1

    var isCancelable: Boolean = false


    init {
        init()
    }


    private fun init() {

        builder = if (themeId != -1)
            AlertDialog.Builder(context, themeId) else AlertDialog.Builder(context)

        builder?.setCancelable(isCancelable)

    }


    override fun showInfoMsg(msg: String, title: String?) {


        if (messageDialog?.isShowing == true) return


        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText, DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }

    override fun showWarningMsg(msg: String, title: String?) {

        if (messageDialog?.isShowing == true) return


        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText, DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }

    override fun showErrorMsg(msg: String, title: String?) {

        if (messageDialog?.isShowing == true) return

        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText, DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }

    override fun showSuccessMsg(msg: String, title: String?) {

        if (messageDialog?.isShowing == true) return

        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText, DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }

    override fun showSuccessMsg(msg: String, title: String?, onYesClick: () -> Unit) {

        if (messageDialog?.isShowing == true) return

        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText, DialogInterface.OnClickListener { dialog, which ->
            onYesClick.invoke()
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }

    override fun showConfirmationMsg(msg: String, confirmHandler: ConfirmHandler, title: String?) {

        confirmationImpelimantaion(msg, confirmHandler, title) {
        }

    }

    fun confirmationImpelimantaion(msg: String, confirmHandler: ConfirmHandler, title: String? ,onDismissHandel: () -> Unit) {
        if (messageDialog?.isShowing == true) return

        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText, DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            confirmHandler.onConfirmed()
        })

        builder?.setNegativeButton(noBtnText, DialogInterface.OnClickListener { dialog, which ->
            onDismissHandel.invoke()
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }

    override fun showConfirmationMsg(
        msg: String,
        confirmHandler: ConfirmHandler,
        onDismissHandel: () -> Unit,
        title: String?
    ) {
        confirmationImpelimantaion(msg, confirmHandler, title) {
            onDismissHandel.invoke()
        }    }

}