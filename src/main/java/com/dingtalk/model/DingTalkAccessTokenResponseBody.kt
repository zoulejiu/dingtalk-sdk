package com.dingtalk.model

import com.google.gson.annotations.SerializedName

class DingTalkAccessTokenResponseBody: DingTalkResponse() {
    @SerializedName("access_token")
    var accessToken: String? = null
    @SerializedName("expires_in")
    var expiresIn: Long? = null
}
