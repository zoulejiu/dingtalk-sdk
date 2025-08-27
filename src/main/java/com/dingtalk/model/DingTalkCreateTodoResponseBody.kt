package com.dingtalk.model

class DingTalkCreateTodoResponseBody: DingTalkResponse() {
    /**
     * 待办ID。
     */
    var id: String? = null

    /**
     * 待办的标题。
     *
     */
    var subject: String? = null

    /**
     * 待办描述。
     *
     */
    var description: String? = null

    /**
     * 开始时间，Unix时间戳，单位毫秒。
     */
    var startTime: Long? = null

    /**
     * 截止时间，Unix时间戳，单位毫秒
     */
    var dueTime: Long? = null
    /**
     * 完成时间，Unix时间戳，单位毫秒
     */
    var finishTime: Long? = null
    /**
     * 完成状态。
     */
    var done: Boolean? = null
    /**
     * 执行者的unionId。。
     */
    var executorIds: List<String>? = null
    /**
     * 参与者的unionId。
     */
    var participantIds: List<String>? = null

    /**
     * 详情页url跳转地址。
     */
    var detailUrl: DetailUrl? = null

    /**
     * 业务来源
     */
    var source: String? = null

    /**
     * 业务系统侧的唯一标识ID，即业务ID
     */
    var sourceId: String? = null

    /**
     * 创建时间，Unix时间戳，单位毫秒。
     */
    var createdTime: Long? = null

    /**
     * 更新时间，Unix时间戳，单位毫秒
     */
    var modifiedTime: Long? = null

    /**
     * 创建者的unionId。
     *
     */
    var creatorId: String? = null

    /**
     * 更新者的unionId。
     *
     */
    var modifierId: String? = null

    /**
     * 接入应用标识
     */
    var bizTag: String? = null

    /**
     * 请求ID。
     *
     */
    var requestId: String? = null

    /**
     * 内容区表单字段配置
     */
    var contentFieldList:List<ContentField>? = null

    /**
     * 生成的待办是否仅展示在执行者的待办列表中。
     */
    var isOnlyShowExecutor:Boolean? = null

    /**
     * 优先级，取值：
     * 10：较低
     * 20：普通
     * 30：较高
     * 40：紧急
     */
    var priority:Int? = null

    /**
     * 待办通知配置。
     */
    var notifyConfigs: NotifyConfig? = null
    class NotifyConfig{
        /**
         * DING通知配置，目前仅支持取值为1，表示应用内DING。
         *
         */
        var dingNotify:String? = null
    }
    class ContentField{
        /**
         * 字段唯一标识。
         *
         */
        var fieldKey: String? = null

        /**
         * 字段值。
         */
        var fieldValue: String? = null
    }

    class DetailUrl {
        /**
         * PC端详情页url跳转地址。
         *
         */
        var pcUrl: String? = null

        /**
         * APP端详情页url跳转地址。
         *
         */
        var appUrl: String? = null

    }
}
