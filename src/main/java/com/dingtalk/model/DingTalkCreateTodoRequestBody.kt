package com.dingtalk.model

/**
 * 创建待办任务
 */
class DingTalkCreateTodoRequestBody {
    /**
     * 是否必填：false
     * 业务系统侧的唯一标识ID，即业务ID。
     * 说明
     * 创建个人待办时，该字段无需传入。
     * 创建第三方待办时，业务系统侧的唯一标识任务ID作为sourceId，保证一个待办任务对应一个sourceId。
     */
    var sourceId: String? = null
    /**
     * 是否必填：true
     * 待办标题，最大长度1024字节。
     */
    var subject: String? = null

    /**
     * 是否必填：false
     * 创建者的unionId。
     * 企业内部应用，调用查询用户详情接口获取unionid参数值。
     * 第三方企业应用，调用查询用户详情接口获取unionid参数值。
     */
    var creatorId: String? = null

    /**
     * 是否必填：false
     * 待办备注描述，最大长度4096。
     *
     * 说明
     * 创建第三方待办时，该字段无需传入，不会正常展示。
     */
    var description: String? = null

    /**
     * 是否必填：false
     * 截止时间，Unix时间戳，单位毫秒
     */
    var dueTime:Long? = null

    /**
     * 是否必填：false
     * 执行者的unionId，建议不超过100人。
     * 企业内部应用，调用查询用户详情接口获取unionid参数值。
     * 第三方企业应用，调用查询用户详情接口获取unionid参数值。
     */
    var executorIds:List<String>?=null

    /**
     * 是否必填：false
     * 参与者的unionId，建议不超过100人。
     * 企业内部应用，调用查询用户详情接口获取unionid参数值。
     * 第三方企业应用，调用查询用户详情接口获取unionid参数值。
     */
    var participantIds:List<String>?=null

    /**
     * 是否必填：false
     * 详情页url跳转地址。
     * 说明
     * 创建个人待办时，该字段无需传入。
     * 创建第三方待办时，需传入自身应用详情页链接。
     */
    var detailUrl:DetailUrl?=null

    /**
     * 是否必填：false
     * 办卡片内容区表单自定义字段列表。
     */
    var contentFieldList:List<ContentField>?=null

    /**
     *是否必填：false
     * 生成的待办是否仅展示在执行者的待办列表中。
     *
     */
    var isOnlyShowExecutor:Boolean?=null

    /**
     * 是否必填：false
     * 优先级，取值：
     * 10：较低
     * 20：普通
     * 30：较高
     * 40：紧急
     */
    var priority:Int?=null

    /**
     * 是否必填：false
     * 待办通知配置。
     */
    var notifyConfigs:NotifyConfig?=null

    /**
     * 是否必填：false
     * 二级分类。
     */
    var bizCategoryId:String?=null

    /**
     * 是否必填：false
     * 自定义按钮配置
     */
    var actionList:List<ActionConfig>?=null

    /**
     * 是否必填：false
     * 待办的业务类型，目前支持两种：
     * TODO：待办业务类型
     * READ：待阅业务类型。
     * 说明
     * 不传该入参时，默认创建的是待办业务类型。
     */
    var todoType:String?=null
    /**
     * 是否必填：false
     * 待办任务的提醒时间，Unix时间戳，单位毫秒。要求必须大于当前时间，推荐设置为早于待办截止时间的5～10分钟。
     */
    var reminderTimeStamp:Long?=null

    /**
     * 是否必填：false
     * 待办截止前的通知提醒设置
     */
    var remindNotifyConfigs:RemindNotifyConfig?=null

    /**
     * 是否必填：false
     * 三方待办的业务拓展数据
     */
    var thirdExtension: MutableMap<Any, Any?>?=null
    class NotifyConfig {
        /**
         * 是否必填：false
         * 是否发送钉钉弹框通知：
         *
         * 1：发送待办弹窗通知
         */
        var dingNotify:String?=null

        /**
         * 是否必填：false
         * 是否发送系统APN通知：
         * true：发送
         * false：不发送
         * 说明
         * 当未设置时取dingNotify的设置值。
         */
        var sendTodoApn:String?=null

        /**
         * 是否必填：false
         * 是否发送待办助手通知：
         * true：发送，默认值
         * false：不发送
         */
        var sendAssistantChat:String?=null

    }

    class DetailUrl {
        /**
         * 是否必填：false
         * APP端详情页url跳转地址，该字段长度限制为1024个字节。
         * 创建个人待办时，该字段无需传入。
         * 创建第三方待办时，需传入自身应用详情页链接。
         * 说明
         * 如果创建第三方待办时配置了DING通知能力，appUrl需要支持以dingtalk协议打开。
         */
        var appUrl: String? = null

        /**
         * 是否必填：false
         * PC端详情页url跳转地址，该字段长度限制为1024个字节。
         * 说明
         * 创建个人待办时，该字段无需传入。
         * 创建第三方待办时，需传入自身应用详情页链接。
         */
        var pcUrl: String? = null
    }
    class ContentField {
        /**
         * 是否必填：false
         * 字段唯一标识，最大长度1024字节
         */
        var fieldKey:String?=null

        /**
         * 是否必填：false
         * 字段值，最大长度1024字节
         */
        var fieldValue:String?=null
    }
    class ActionConfig{
        /**
         * 是否必填：false
         * 按钮的名称。
         */
        var title:String?=null
        /**
         * 是否必填：false
         * 按钮类型：
         * 1：直接调用业务服务
         * 2：直接跳转
         */
        var actionType: Int?=null

        /**
         * 是否必填：false
         * 按钮的回调参数
         */
        var param:Param?=null

        /**
         * 是否必填：false
         * 跳转链接或者回调请求的地址。
         */
        var url :String?=null

        /**
         * 是否必填：false
         * 按钮唯一标识，当有两个按钮时可以通过这个字段来区分。
         */
        var actionKey:String?=null

        /**
         * 是否必填：false
         * pc端的跳转链接，可以不填，为空时会拿url参数里面的值。
         */
        var pcUrl:String?=null


        class Param{
            /**
             * 是否必填：false
             * 回调三方服务时请求的body。
             */
            var body:String?=null

            /**
             * 是否必填：false
             * 回调三方服务时请求的header
             */
            var header: MutableMap<Any,Any?>?=null
        }
    }
    class RemindNotifyConfig{
        /**
         * 是否必填：false
         * 是否发送钉钉弹框通知：
         * 1：发送
         * 0：不发送
         */
        var dingNotify:String?=null

        /**
         * 是否必填：false
         * 是否发送系统APN通知：
         * true：发送
         * false：不发送。
         * 说明
         * 当未设置时取dingNotify的设置值。
         */
        var sendTodoApn:String?=null
    }
}
