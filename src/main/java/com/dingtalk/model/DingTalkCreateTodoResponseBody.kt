package com.dingtalk.model

class DingTalkCreateTodoResponseBody: DingTalkResponse() {
    /**
     * 待办ID。
     */
    var taskId: String? = null

    /**
     * 创建时间，Unix时间戳，单位毫秒。
     */
    var createdTime: Long? = null
}
