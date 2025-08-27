package com.dingtalk.service.impl

import com.dingtalk.config.DingTalkConfig
import com.dingtalk.model.DingTalkAccessTokenResponseBody
import com.dingtalk.model.DingTalkCreateTodoRequestBody
import com.dingtalk.model.DingTalkCreateTodoResponseBody
import com.dingtalk.model.DingTalkUserDetailResponseBody
import com.dingtalk.service.DingTalkService
import com.dingtalk.util.HttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.net.URLEncoder
import kotlin.io.encoding.Base64

@Service
class DingTalkServiceImpl(
    val dingTalkConfig: DingTalkConfig,
    var httpClient: HttpClient
) : DingTalkService {
    val log: Logger = LoggerFactory.getLogger(DingTalkServiceImpl::class.java)
    val gson: Gson = Gson()
    val tokenMap = mutableMapOf<String, String?>()
    override fun accessToken(): DingTalkAccessTokenResponseBody {
        check()
        val type = object : TypeToken<DingTalkAccessTokenResponseBody>() {}.type
        val httpClientResponse =
            httpClient.get<DingTalkAccessTokenResponseBody?>(
                dingTalkConfig.oldHost + "/gettoken",
                mutableMapOf<String, String?>().apply {
                    put("appkey", dingTalkConfig.appKey)
                    put("appsecret", dingTalkConfig.appSecret)
                }, type
            )
        if (log.isDebugEnabled) {
            log.debug("ding talk get access token,code:${httpClientResponse.code},body:${gson.toJson(httpClientResponse.body)}")
        }
        if (httpClientResponse.code != 200) {
            throw RuntimeException("ding talk get access token failed")
        }
        val dingTalkAccessTokenResponseBody =
            httpClientResponse.body ?: throw RuntimeException("ding talk get access token failed")
        if (dingTalkAccessTokenResponseBody.accessToken.isNullOrBlank()) {
            throw RuntimeException(dingTalkAccessTokenResponseBody.errmsg)
        }

        tokenMap[dingTalkConfig.appKey!!] = dingTalkAccessTokenResponseBody.accessToken
        return dingTalkAccessTokenResponseBody
    }

    override fun getUserDetail(userId: String): DingTalkUserDetailResponseBody {
        if (tokenMap[dingTalkConfig.appKey] == null) {
            accessToken()
        }
        val type = object :TypeToken<DingTalkUserDetailResponseBody?>(){}.type
        val httpClientResponse=httpClient.post<DingTalkUserDetailResponseBody?>(dingTalkConfig.oldHost + "/topapi/v2/user/get?access_token=${tokenMap[dingTalkConfig.appKey]}",gson.toJson(mutableMapOf<String, String?>().apply {
            put("userid",userId)
            put("language","zh_CN")
        }), type)
        if(httpClientResponse.code!= 200) {
            throw RuntimeException("ding talk get user Detail failed")
        }
        if(httpClientResponse.body?.errcode!=0){
            throw RuntimeException(httpClientResponse.body?.errmsg)
        }
        return httpClientResponse.body!!
    }

    override fun createUserTodoTask(unionId: String, operatorId: String?,requestVo: DingTalkCreateTodoRequestBody): DingTalkCreateTodoResponseBody {
        if (tokenMap[dingTalkConfig.appKey] == null) {
            accessToken()
        }
        buildHeads()
        var tempOperatorId=operatorId
        if(tempOperatorId.isNullOrBlank()) {
            tempOperatorId=""
        }
        val type = object :TypeToken<DingTalkCreateTodoResponseBody?>(){}.type
        val httpClientResponse=httpClient.post<DingTalkCreateTodoResponseBody?>("${dingTalkConfig.host}/v1.0/todo/users/${unionId}/tasks?operatorId=${tempOperatorId}",gson.toJson(requestVo), type)
        if(httpClientResponse.code!= 200) {
            throw RuntimeException(httpClientResponse.body?.message)
        }

        return httpClientResponse.body!!
    }

    private fun check() {
        if (dingTalkConfig.appKey.isNullOrBlank()) {
            throw RuntimeException("DingTalk ding.talk.appKey can't be empty")
        }
        if (dingTalkConfig.appSecret.isNullOrBlank()) {
            throw RuntimeException("DingTalk ding.talk.appSecret can't be empty")
        }
    }
    private fun buildHeads(){
        tokenMap[dingTalkConfig.appKey]?.let {
            val heads =mutableMapOf<String, String?>()
            heads["x-acs-dingtalk-access-token"] = it
            httpClient.heads=heads
        }
    }
}





