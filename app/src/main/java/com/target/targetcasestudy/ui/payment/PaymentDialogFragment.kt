package com.target.targetcasestudy.ui.payment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.validateCreditCard
import com.target.targetcasestudy.databinding.DialogPaymentBinding
import com.target.targetcasestudy.ui.viewmodel.CreditCardViewModel
import com.target.targetcasestudy.ui.viewmodel.DealViewModel

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment(private val viewModelFactory: ViewModelProvider.Factory) : DialogFragment() {

  private lateinit var paymentBinding: DialogPaymentBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    paymentBinding = DataBindingUtil.inflate(inflater,R.layout.dialog_payment, container, false )
    val viewModel = ViewModelProvider(this, viewModelFactory).get(CreditCardViewModel::class.java)
    paymentBinding.lifecycleOwner = this
    paymentBinding.creditCardViewModel = viewModel
    paymentBinding.cancel.setOnClickListener { dismiss() }
    paymentBinding.submit.setOnClickListener { dismiss() }
    viewModel.creditCardNumber.observe(this, Observer {
      paymentBinding.submit.isEnabled = validateCreditCard(it)
    })
    return paymentBinding.root
  }
}