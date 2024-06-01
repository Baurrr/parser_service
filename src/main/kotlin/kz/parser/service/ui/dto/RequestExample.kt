package kz.parser.service.ui.dto

class RequestExample(
    val url: String,
    val headers: List<HeaderData> = arrayListOf(),
    val body: Any?
)