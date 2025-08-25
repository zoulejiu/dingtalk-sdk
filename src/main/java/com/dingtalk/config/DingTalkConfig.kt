package com.dingtalk.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "ding.talk")
class DingTalkConfig {
    var appKey: String? = null
    var appSecret: String? = null
    var oldHost: String? = "https://oapi.dingtalk.com"
    var host: String? = "https://api.dingtalk.com"
}
