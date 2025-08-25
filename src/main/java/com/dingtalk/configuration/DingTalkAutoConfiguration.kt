package com.dingtalk.configuration

import com.dingtalk.config.DingTalkConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(DingTalkConfig::class)
@ComponentScan("com.dingtalk")
open class DingTalkAutoConfiguration {
}
