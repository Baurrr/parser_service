package kz.parser.service.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.stereotype.Service

@Service
class ParserService {

    fun xmlToJson(xmlString: String): String {
        try {
            val xmlMapper = XmlMapper()
            val dataMap: Map<String, Any> = xmlMapper.readValue(xmlString, Map::class.java) as Map<String, Any>
            val objectMapper = ObjectMapper()
            val jsonString = objectMapper.writeValueAsString(dataMap)
            return jsonString
        } catch (e: Exception) {
            e.printStackTrace()
            return e.message.toString()
        }
    }

    fun jsonToXml(jsonString: String): String {
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
            return xmlString
        } catch (e: Exception) {
            return e.message.toString()
        }
    }

}