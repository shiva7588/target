package com.target.targetcasestudy.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.target.targetcasestudy.data.validateCreditCard
import com.target.targetcasestudy.ui.base.BaseViewModel
import javax.inject.Inject

class CreditCardViewModel @Inject constructor() : BaseViewModel() {

    val creditCardNumber = MutableLiveData<String>()
}

