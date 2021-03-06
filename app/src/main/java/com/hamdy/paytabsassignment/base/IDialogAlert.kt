package com.hamdy.paytabsassignment.base


interface IDialogAlert {

    fun showInfoMsg(msg : String , title : String? = null)
    fun showWarningMsg(msg : String , title : String? = null)
    fun showErrorMsg(msg : String , title : String? = null)
    fun showSuccessMsg(msg : String , title : String? = null)
    fun showSuccessMsg(msg : String , title : String? = null,onYesClick:()->Unit)
    fun showConfirmationMsg(msg : String , confirmHandler : ConfirmHandler , title : String? = null)
    fun showConfirmationMsg(msg : String , confirmHandler : ConfirmHandler ,onDismissHandel:()->Unit , title : String? = null)

}