package com.dingtalk.util

import java.net.URLEncoder

/**
 * PC端
 *钉钉消息链接在默认浏览器打开
 */
fun String?.toOpenBrowser(): String? {
    if (this == null) return null
    return "dingtalk://dingtalkclient/page/link?url=${URLEncoder.encode(this, Charsets.UTF_8)}&pc_slide=false"
}

/**
 * PC端
 * 钉钉消息链接在PC则边栏打开
 */
fun String?.toOpenPcSlide(): String? {
    if (this == null) return null
    return "dingtalk://dingtalkclient/page/link?url=${URLEncoder.encode(this, Charsets.UTF_8)}&pc_slide=true"
}


/**
 * @param title 弹窗的标题
 *@param width 弹窗的宽度
 *@param height 弹窗的高度
 *
 */
fun String?.toOpenPopup(title: String, width: Int=1024, height: Int=768): String? {
    if (this == null) return null
    return "dingtalk://dingtalkclient/page/link?popup_wnd=true&url=${
        URLEncoder.encode(
            this,
            Charsets.UTF_8
        )
    }&title=${URLEncoder.encode(title, Charsets.UTF_8)}&width=${width}&height=${height}"
}

/**
 * @param corpid 免登微应用用户的所属企业。
 * @param container_type 使用哪种方式打开链接  work_platform：表示用工作台打开
 * @param  appIdOrAgentId 第三方企业应用应填写appId，企业自建应用填写0_agentId，由数字0、下划线和agentId拼接组成。其中appId，agentId可在应用详情中查看。
 * @param  redirect_type  此场景下输入jump。
 */
fun String?.toOpenApp(corpid: String, appIdOrAgentId: String): String? {
    if (this == null) return null
    return "dingtalk://dingtalkclient/action/openapp?corpid=${corpid}&container_type=work_platform&app_id=${appIdOrAgentId}&redirect_type=jump&redirect_url=${
        URLEncoder.encode(
            this,
            Charsets.UTF_8
        )
    }"
}

/**
 * 移动端打开 URL
 * 以全屏方式打开
 *
 */
fun String?.toOpenMobileFullScree(): String? {
    if (this == null) return null
    return "dingtalk://dingtalkclient/page/link?url=${URLEncoder.encode(this, Charsets.UTF_8)}"
}

/**
 * 移动端打开
 * 以半浮层方式打开
 * @param percent 半浮层占手机屏幕高度的百分比（数字）
 */
fun String?.toOpenSemiFloatingLayer(percent: Int=83): String? {
    if (this == null) return null
    return "dingtalk://dingtalkclient/action/im_open_hybrid_panel?panelHeight=percent${percent}&hybridType=online&pageUrl=${URLEncoder.encode(this, Charsets.UTF_8)}"
}


fun String?.toOpenAll(pcLink: String,mobileLink: String): String? {
    if (this == null) return null
    return "dingtalk://dingtalkclient/action/open_platform_link?pcLink=${pcLink}&mobileLink=${mobileLink}"
}
