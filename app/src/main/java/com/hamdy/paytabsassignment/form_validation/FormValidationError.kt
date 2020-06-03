package com.hamdy.paytabsassignment.form_validation


class FormValidationError(val messages  : List<IRule<*>?>, val  formControlResID : Int)