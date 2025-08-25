package com.dingtalk.model


class HttpClientResponse<T> {
    var status = false
    var msg: String? = null
    var code: Int? = null
    var content: String? = null
    var body: T? = null
}
