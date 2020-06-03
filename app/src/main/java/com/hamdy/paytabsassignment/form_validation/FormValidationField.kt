package com.hamdy.paytabsassignment.form_validation


class FormValidationField<T> (val value  : T, val  formControlResID : Int, val rules : List<IRule<T>> ? = null)