package com.coder.firstapikotlin.repository

import com.coder.firstapikotlin.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
}