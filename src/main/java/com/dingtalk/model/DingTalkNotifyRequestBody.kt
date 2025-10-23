package com.dingtalk.model

/**
 * 工作通知
 */
class DingTalkNotifyRequestBody {
    /**
     *发送消息时使用的微应用的AgentID。
     *
     * 企业内部应用可在开发者后台的应用详情页面查看。
     *
     * 第三方企业应用可调用获取企业授权信息接口获取。
     */
    var agent_id: Long?=null

    /**
     * 接收者的userid列表，最大用户列表长度100。
     *
     */
    var userid_list:String?=null

    /**
     * 接收者的部门id列表，最大列表长度20。
     *
     * 接收者是部门ID时，包括子部门下的所有用户。
     */
    var dept_id_list:String?=null

    /**
     * 是否发送给企业全部用户。
     *
     * 说明
     * 当设置为false时必须指定userid_list或dept_id_list其中一个参数的值。
     */
    var to_all_user:Boolean?=null

    /**
     * 消息内容，最长不超过2048个字节，支持以下工作通知类型，msgtype 包括：
     *
     * text：文本消息
     *
     * image：图片消息
     *
     * voice：语音消息
     *
     * file：文件消息
     *
     * link：链接消息
     *
     * oa：OA消息
     *
     * 重要
     * OA消息支持通过status_bar参数设置消息的状态文案和颜色，消息发送后可调用更新工作通知状态栏接口更新消息状态和颜色。
     * markdown：Markdown消息
     *
     * action_card：卡片消息
     *
     * 说明
     * 企业内部应用，请参考工作通知类型。
     * 第三方企业应用，请参考工作通知类型。
     */
    var msg:MsgObj?=null

    class MsgObj{
        /**
         * text：文本消息
         *
         * image：图片消息
         *
         * voice：语音消息
         *
         * file：文件消息
         *
         * link：链接消息
         *
         * oa：OA消息
         *
         * 重要
         * OA消息支持通过status_bar参数设置消息的状态文案和颜色，消息发送后可调用更新工作通知状态栏接口更新消息状态和颜色。
         * markdown：Markdown消息
         *
         * action_card：卡片消息
         */
        var msgtype: String?=null

        /**
         * 是否开启id转译，默认false。仅第三方应用需要用到，企业内部应用可以忽略
         */
        var enable_id_trans:String?=null
        var text:Text?=null
        var image:Image?=null
        var link:Link?=null
        var file:File?=null
        var voice:Voice?=null
        var oa:Oa?=null
        var markdown:Markdown?=null
        var action_card:ActionCard?=null

        class Text{
            var content: String?=null
        }
        class Image{
            var media_id: String?=null
        }
        class Voice{
            /**
             * 音频时长
             */
            var duration: Long?=null
            var media_id: String?=null
        }
        class File{
            var media_id: String?=null
        }
        class Link{
            var picUrl:String?=null
            var messageUrl:String?=null
            var text:String?=null
            var title:String?=null
        }
        class Oa{
            /**
             * 消息体
             */
            var body:BodyObj?=null
            class BodyObj{
                /**
                 * 自定义作者名字
                 */
                var author:String?=null

                /**
                 * 自定义的附件数目。此数字仅供显示，钉钉不作验证
                 */
                var file_count:Long?=null

                /**
                 * 消息体中的图片，支持图片资源@mediaId
                 */
                var image:String?=null

                /**
                 * 消息体的内容，最多显示3行
                 */
                var content:String?=null

                /**
                 * 单行富文本信息
                 */
                var rich:Rich?=null
                class Rich{
                    /**
                     * 富文本单位
                     */
                    var unit:String?=null

                    /**
                     * 富文本数目
                     */
                    var num: Int?=null
                }

                /**
                 * 消息体的表单，最多显示6个，超过会被隐藏
                 */
                var form:List<Form>?=null
                class Form{
                    /**
                     *消息体的关键字对应的值
                     */
                    var value:String?=null

                    /**
                     * 消息体的关键字
                     */
                    var key:String?=null
                }

                /**
                 * 消息体的标题，建议50个字符以内
                 */
                var title:String?=null
            }

            /**
             * 消息头部内容
             */
            var head:Head?=null
            class Head{
                /**
                 * 消息头部的背景颜色。长度限制为8个英文字符，其中前2为表示透明度，后6位表示颜色值。不要添加0x
                 */
                var bgcolor:String?=null

                /**
                 * 消息的头部标题 (向普通会话发送时有效，向企业会话发送时会被替换为微应用的名字)，长度限制为最多10个字符
                 */
                var text:String?=null
            }

            /**
             * PC端点击链接跳转地址
             */
            var pc_message_url:String?=null

            /**
             * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接
             */
            var message_url:String?=null

            /**
             * 状态栏
             */
            var status_bar: StatusBar?=null
            class StatusBar{
                /**
                 * 状态栏文案
                 */
                var status_value:String?=null

                /**
                 * 状态栏背景色，默认为黑色
                 */
                var status_bg:String?=null
            }
        }

        class Markdown{
            /**
             * markdown格式的消息，建议500字符以内
             */
            var text:String?=null

            /**
             * 首屏会话透出的展示内容
             */
            var title:String?=null
        }
        class  ActionCard{
            /**
             * 使用独立跳转ActionCard样式时的按钮列表；必须与btn_orientation同时设置
             */
            var btn_json_list :BtnJsonList?=null
            class BtnJsonList{
                /**
                 * 使用独立跳转ActionCard样式时的按钮的标题，最长20个字符
                 */
                var title:String?=null

                /**
                 * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接，最长500个字符
                 */
                var action_url:String?=null
            }

            /**
             * 使用独立跳转ActionCard样式时的按钮排列方式，竖直排列(0)，横向排列(1)；必须与btn_json_list同时设置
             */
            var btn_orientation:String?=null

            /**
             * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接，最长500个字符
             */
            var single_url:String?=null

            /**
             * 使用整体跳转ActionCard样式时的标题，必须与single_url同时设置，最长20个字符
             */
            var single_title:String?=null

            /**
             * 消息内容，支持markdown，语法参考标准markdown语法。建议1000个字符以内
             */
            var markdown:String?=null

            /**
             * 透出到会话列表和通知的文案，最长64个字符
             */
            var title:String?=null
        }
    }
}
