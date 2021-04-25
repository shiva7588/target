package com.target.targetcasestudy

import com.target.targetcasestudy.data.validateCreditCard
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Feel free to make modifications to these unit tests! Remember, you have full technical control
 * over the project, so you can use any libraries and testing strategies that see fit.
 */

@RunWith(Parameterized::class)
class CreditCardValidatorTest(private val cardNumber: String, private val isValid: Boolean) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: Valid card number({0})={1}")
        fun data(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf("4539976741512043", true),
                arrayOf("5597314998635208", true),
                arrayOf("3528831678881488", true),
                arrayOf("4539976741512013", false),
                arrayOf("5597314998635218", false),
                arrayOf("3528831678881418", false)
            ).toList()
        }
    }

    @Test
    fun `Testing card number`() {
        Assert.assertEquals(
            isValid,
            validateCreditCard(cardNumber)
        )
    }
}
