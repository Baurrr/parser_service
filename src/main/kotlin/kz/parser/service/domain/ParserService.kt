package kz.parser.service.domain

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ParserService {

    fun xmlToJson(xmlString: String): ResponseEntity<Any> {
        try {
            val xmlMapper = XmlMapper()
            val dataMap: Map<String, Any> = xmlMapper.readValue(xmlString, Map::class.java) as Map<String, Any>
            val objectMapper = ObjectMapper()
            val jsonString = objectMapper.writeValueAsString(dataMap)
            return ResponseEntity.ok(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    fun jsonToXmlInTag(jsonString: String): ResponseEntity<Any> {
        try {
            val json = JSONObject(jsonString)
            val xmlBuilder = StringBuilder()

            fun parseObject(json: JSONObject) {
                for (key in json.keys()) {
                    val value = json.get(key)
                    if (value is JSONObject) {
                        xmlBuilder.append("<$key")
                        for (innerKey in value.keys()) {
                            if (innerKey.startsWith("_")) {
                                xmlBuilder.append(" ${innerKey.substring(1)}=\"${value.get(innerKey)}\"")
                            }
                        }
                        xmlBuilder.append(">")
                        parseObject(value)
                        xmlBuilder.append("</$key>")
                    } else if (value is JSONArray) {
                        for (i in 0 until value.length()) {
                            val innerValue = value.get(i)
                            if (innerValue is JSONObject) {
                                xmlBuilder.append("<$key>")
                                parseObject(innerValue)
                                xmlBuilder.append("</$key>")
                            } else {
                                xmlBuilder.append("<$key>")
                                xmlBuilder.append("<value>$innerValue</value>")
                                xmlBuilder.append("</$key>")
                            }
                        }
                    } else {
                        if (!key.startsWith("_")) {
                            xmlBuilder.append("<$key>$value</$key>")
                        }
                    }
                }
            }

            parseObject(json)

            val xmlString = xmlBuilder.toString()
            return ResponseEntity.ok(xmlString)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

}