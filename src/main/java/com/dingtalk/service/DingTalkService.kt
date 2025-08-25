package com.dingtalk.service

import com.dingtalk.model.DingTalkAccessTokenResponseBody
import com.dingtalk.model.DingTalkCreateTodoRequestBody
import com.dingtalk.model.DingTalkCreateTodoResponseBody
import com.dingtalk.model.DingTalkUserDetailResponseBody

interface DingTalkService {
    fun accessToken(): DingTalkAccessTokenResponseBody
    fun getUserDetail(userId: String): DingTalkUserDetailResponseBody?

    /**
     * 创建个人待办任务
     */
    fun createUserTodoTask(unionId: String, operatorId: String?, requestVo: DingTalkCreateTodoRequestBody): DingTalkCreateTodoResponseBody

}
