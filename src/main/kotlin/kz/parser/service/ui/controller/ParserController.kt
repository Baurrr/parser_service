package kz.parser.service.ui.controller

import kz.parser.service.domain.ParserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/parser")
@CrossOrigin("*")
class ParserController(
    val service: ParserService
) {

    companion object {
        const val SECRET = "IrJYdWXHUJRhKeMUin3juoogii23nQ"
    }

    @PostMapping("/xml-to-json")
    fun xmlToJson(
        @RequestHeader("Secret") secret: String,
        @RequestBody xmlString: String
    ): ResponseEntity<Any> {
        return if (secret == SECRET) {
            service.xmlToJson(xmlString)
        } else {
            ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not allowed")
        }
    }

    @PostMapping("/json-to-xml")
    fun jsonToXml(
        @RequestHeader("Secret") secret: String,
        @RequestBody jsonString: String
    ): ResponseEntity<Any> {
        return if (secret == SECRET) {
            service.jsonToXmlInTag(jsonString)
        } else {
            ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not allowed")
        }
    }
}