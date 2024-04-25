package kz.parser.service.ui.controller

import kz.parser.service.domain.ProxyService
import kz.parser.service.ui.dto.RequestExample
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/request")
class ProxyController(
    val proxyService: ProxyService
) {

    @PostMapping
    fun post(
        @RequestBody requestExample: RequestExample
    ): ResponseEntity<Any> {
        return proxyService.makeRequest(requestExample, HttpMethod.POST)
    }

    @PutMapping
    fun put(
        @RequestBody requestExample: RequestExample
    ): ResponseEntity<Any> {
        return proxyService.makeRequest(requestExample, HttpMethod.PUT)
    }

    @DeleteMapping
    fun delete(
        @RequestBody requestExample: RequestExample
    ): ResponseEntity<Any> {
        return proxyService.makeRequest(requestExample, HttpMethod.DELETE)
    }

    @GetMapping
    fun get(
        @RequestBody requestExample: RequestExample
    ): ResponseEntity<Any> {
        return proxyService.makeRequest(requestExample, HttpMethod.GET)
    }

}