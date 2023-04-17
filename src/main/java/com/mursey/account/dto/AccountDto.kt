package com.mursey.account.dto

import com.mursey.account.model.Customer
import com.mursey.account.model.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountDto(
        val id: String?,
        val balance: BigDecimal? = BigDecimal.ZERO,
        val creationDate: LocalDateTime,
        val customer: AccountCustomerDto?,
        val transaction: Set<TransactionDto>?
)
