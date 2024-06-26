package kz.parser.service.domain

import com.google.gson.Gson
import kz.parser.service.ui.dto.RequestExample
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ProxyService(
    val parser: ParserService
) {

    fun makeRequest(request: RequestExample, httpMethod: HttpMethod): ResponseEntity<Any> {
        val xmlBody = request.body?.let { parser.jsonToXml(Gson().toJson(it).toString()) }
        val headers = HttpHeaders()
        request.headers.forEach { header ->
            headers.add(header.name, header.value)
        }
        headers.contentType = MediaType.APPLICATION_XML
        val httpEntity = HttpEntity<String>(xmlBody, headers)
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(request.url, httpMethod, httpEntity, String::class.java)
        val jsonResponse = parser.xmlToJson(response.body ?: "")
        return ResponseEntity.status(response.statusCode).contentType(MediaType.TEXT_PLAIN).body(jsonResponse)
    }

}