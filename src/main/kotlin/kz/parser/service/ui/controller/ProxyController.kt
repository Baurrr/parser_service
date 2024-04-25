package kz.parser.service.ui.controller

import kz.parser.service.domain.ProxyService
import kz.parser.service.ui.dto.RequestExample
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/request")
@CrossOrigin("*")
class ProxyController(
    val proxyService: ProxyService
) {

    @PostMapping
    fun post(
        @RequestHeader("Secret") secret: String,
        @RequestBody requestExample: RequestExample
    ): ResponseEntity<Any> {
        return if (ParserController.SECRET == secret) {
            proxyService.makeRequest(requestExample, HttpMethod.POST)
        } else {
            ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not allowed")
        }
    }

    @PutMapping
    fun put(
        @RequestHeader("Secret") secret: String,
        @RequestBody requestExample: RequestExample
    ): ResponseEntity<Any> {
        return if (ParserController.SECRET == secret) {
            proxyService.makeRequest(requestExample, HttpMethod.PUT)
        } else {
            ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not allowed")
        }
    }

    @DeleteMapping
    fun delete(
        @RequestHeader("Secret") secret: String,
        @RequestBody requestExample: RequestExample
    ): ResponseEntity<Any> {
        return if (ParserController.SECRET == secret) {
            proxyService.makeRequest(requestExample, HttpMethod.DELETE)
        } else {
            ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not allowed")
        }
    }

    @GetMapping
    fun get(
        @RequestHeader("Secret") secret: String,
        @RequestBody requestExample: RequestExample
    ): ResponseEntity<Any> {
        return if (ParserController.SECRET == secret) {
            proxyService.makeRequest(requestExample, HttpMethod.GET)
        } else {
            ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not allowed")
        }
    }

}