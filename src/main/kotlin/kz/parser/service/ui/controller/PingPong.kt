package kz.parser.service.ui.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ping")
@CrossOrigin("*")
class PingPong {

    @GetMapping("")
    fun pong(): ResponseEntity<Any> {
        return ResponseEntity.ok("pong")
    }

}