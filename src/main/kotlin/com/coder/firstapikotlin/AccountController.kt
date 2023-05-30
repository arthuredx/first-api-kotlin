package com.coder.firstapikotlin

import io.micrometer.core.ipc.http.HttpSender.Response
import jakarta.persistence.Id
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController (private val repository: AccountRepository){

    @PostMapping
    fun create (@RequestBody account: Account): Account = repository.save(account)

    @GetMapping
    fun getAll(): List<Account>  = repository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Account> = repository.findById(id).map {
        ResponseEntity.ok(it)
    }.orElse(ResponseEntity.notFound().build())

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody account: Account): ResponseEntity<Account> = repository.findById(id).map {
        val accountUpdate = it.copy(
            name = account.name,
            document = account.document,
            phone = account.phone
        )
        ResponseEntity.ok(repository.save(accountUpdate))
    }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> = repository.findById(id).map {
        repository.deleteById(id)
        ResponseEntity.ok("Delete has completed")
    }.orElse(ResponseEntity.notFound().build())

}