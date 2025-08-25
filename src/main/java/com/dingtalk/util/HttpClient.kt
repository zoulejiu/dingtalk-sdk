package com.dingtalk.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.dingtalk.model.HttpClientResponse
import lombok.extern.slf4j.Slf4j
import org.apache.hc.client5.http.classic.methods.HttpGet
import org.apache.hc.client5.http.classic.methods.HttpPatch
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase
import org.apache.hc.client5.http.config.ConnectionConfig
import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.cookie.BasicCookieStore
import org.apache.hc.client5.http.cookie.CookieStore
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier
import org.apache.hc.core5.http.ContentType
import org.apache.hc.core5.http.Header
import org.apache.hc.core5.http.HttpResponse
import org.apache.hc.core5.http.HttpStatus
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.apache.hc.core5.http.io.entity.StringEntity
import org.apache.hc.core5.http.message.BasicHeader
import org.apache.hc.core5.ssl.SSLContextBuilder
import org.apache.hc.core5.util.Timeout
import org.springframework.stereotype.Component
import java.io.UnsupportedEncodingException
import java.lang.reflect.Type
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.X509Certificate
import java.time.LocalDateTime

@Slf4j
@Component
class HttpClient @JvmOverloads constructor(
    private val defaultHeads: MutableMap<String, String>? = null
) {
    var heads: MutableMap<String, String?>? = null
    private var httpClient: CloseableHttpClient? = null
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .setDateFormat("yyyy-MM-dd HH:mm:ss").create()

    init {
        this.client
    }

    @get:Throws(NoSuchAlgorithmException::class, KeyStoreException::class, KeyManagementException::class)
    private val client: Unit
        get() {
            val defaultRequestConfig = RequestConfig.custom()
                .build()
            val cookieStore: CookieStore = BasicCookieStore()
            val httpClientBuilder = HttpClients.custom()
            if (defaultHeads != null) {
                val headerList: MutableList<Header> =
                    ArrayList()
                for (entry in defaultHeads.entries) {
                    headerList.add(BasicHeader(entry.key, entry.value))
                }
                if (!headerList.isEmpty()) {
                    httpClientBuilder.setDefaultHeaders(headerList)
                }
            }
            val sslContext = SSLContextBuilder.create().loadTrustMaterial(
                null
            ) { chain: Array<X509Certificate>, authType: String -> true }
            val sslConnectionSocketFactory =
                DefaultClientTlsStrategy(
                    sslContext.build(),
                    NoopHostnameVerifier.INSTANCE
                )
            val connectionConfig = ConnectionConfig.custom()
                .setSocketTimeout(Timeout.ofMilliseconds(3000)) // Socket Timeout
                .setConnectTimeout(Timeout.ofMilliseconds(3000)) // Connect Timeout
                .build()
            val connectionManager =
                PoolingHttpClientConnectionManagerBuilder.create()
                    .setDefaultConnectionConfig(connectionConfig)
                    .setTlsSocketStrategy(sslConnectionSocketFactory)
                    .build()
            this.httpClient = httpClientBuilder
                .setConnectionManager(connectionManager).setDefaultCookieStore(cookieStore)
                .setDefaultRequestConfig(defaultRequestConfig).build()
        }

    @Throws(Exception::class)
    fun <T> post(url: String?, obj: Any?, type: Type): HttpClientResponse<T> {
        return post(url, Gson().toJson(obj), type)
    }

    @Throws(Exception::class)
    fun <T> post(url: String?, json: String?, type: Type): HttpClientResponse<T> {
        checkParams(url)
        var response: CloseableHttpResponse? = null
        val httpMessage = HttpClientResponse<T>()
        httpMessage.status = false
        val post = HttpPost(url)
        if (json?.isNotBlank() == true) {
            val entity = StringEntity(json, ContentType.APPLICATION_JSON) //解決中文亂碼問題
            post.entity = entity
        }
        try {
            setHeads(post)
            response = httpClient!!.execute(post)
            val html = EntityUtils.toString(response.entity)
            error(response)
            httpMessage.status = true
            httpMessage.content = html
            httpMessage.code = response.code
            httpMessage.body = gson.fromJson<T?>(httpMessage.content, type)
        } finally {
            response?.close()
        }
        return httpMessage
    }


    @Throws(Exception::class)
    fun <T> post(url: String?, json: String?, responseType: Class<T>): HttpClientResponse<T> {
        checkParams(url)
        var response: CloseableHttpResponse? = null
        val httpMessage = HttpClientResponse<T>()
        httpMessage.status = false
        val post = HttpPost(url)
        if (json?.isNotBlank() == true) {
            val entity = StringEntity(json, ContentType.APPLICATION_JSON) //解決中文亂碼問題
            post.entity = entity
        }
        try {
            response = httpClient!!.execute(post)
            val html = EntityUtils.toString(response.entity)
            error(response)
            httpMessage.status = true
            httpMessage.content = html
            httpMessage.code = response.code
            httpMessage.body = gson.fromJson<T>(httpMessage.content, responseType)
        } finally {
            response?.close()
        }
        return httpMessage
    }


    @Throws(Exception::class)
    fun <T> patch(url: String?, json: String?, responseType: Class<T>): HttpClientResponse<T> {
        checkParams(url)
        var response: CloseableHttpResponse? = null
        val httpMessage = HttpClientResponse<T>()
        httpMessage.status = false
        val httpPatch = HttpPatch(url)
        if (json?.isNotBlank() == true) {
            val entity = StringEntity(json, ContentType.APPLICATION_JSON) //解決中文亂碼問題
            httpPatch.entity = entity
        }
        try {
            response = httpClient!!.execute(httpPatch)
            val html = EntityUtils.toString(response.entity)
            error(response)
            httpMessage.status = true
            httpMessage.content = html
            httpMessage.code = response.code
            httpMessage.body = gson.fromJson<T?>(httpMessage.content, responseType)
        } finally {
            response?.close()
        }
        return httpMessage
    }

    @Throws(Exception::class)
    fun <T> get(url: String?, params: MutableMap<String, String?>, type: Type): HttpClientResponse<T> {
        var url = url
        checkParams(url)
        var response: CloseableHttpResponse? = null
        val httpMessage = HttpClientResponse<T>()
        httpMessage.status = false
        val stringBuffer = coverMapToGetParams(params)
        if (stringBuffer.isNotEmpty()) {
            url = "$url?$stringBuffer"
        }
        val get = HttpGet(url)
        setHeads(get)
        try {
            response = httpClient!!.execute(get)
            val html = EntityUtils.toString(response.entity)
            error(response)
            httpMessage.status = true
            httpMessage.content = html
            httpMessage.code = response.code
            httpMessage.body = gson.fromJson<T?>(httpMessage.content, type)
        } finally {
            response?.close()
        }
        return httpMessage
    }

    @Throws(Exception::class)
    fun <T> get(url: String?, params: MutableMap<String, String?>, responseType: Class<T>): HttpClientResponse<T> {
        var url = url
        checkParams(url)

        var response: CloseableHttpResponse? = null
        val httpMessage = HttpClientResponse<T>()
        httpMessage.status = false
        val stringBuffer = coverMapToGetParams(params)
        if (!stringBuffer.isEmpty()) {
            url = "$url?$stringBuffer"
        }
        val get = HttpGet(url)
        setHeads(get)
        try {
            response = httpClient!!.execute(get)
            val html = EntityUtils.toString(response.entity)
            error(response)
            httpMessage.status = true
            httpMessage.content = html
            httpMessage.code = response.code
            httpMessage.body = gson.fromJson<T>(httpMessage.content, responseType)
        } finally {
            response?.close()
        }
        return httpMessage
    }

    @Throws(UnsupportedEncodingException::class)
    private fun coverMapToGetParams(params: MutableMap<String, String?>): StringBuffer {
        val stringBuffer = StringBuffer()
        // 編碼請求參數
        for (entry in params.entries) {
            stringBuffer.append(entry.key).append("=")
                .append(URLEncoder.encode(entry.value, StandardCharsets.UTF_8)).append("&")
        }
        if (!stringBuffer.isEmpty()) {
            stringBuffer.delete(stringBuffer.length - 1, stringBuffer.length)
        }
        return stringBuffer
    }

    @Throws(Exception::class)
    private fun checkParams(url: String?) {
        if (url?.isNotBlank() == false) {
            throw Exception("請求URL 不能為空")
        }
    }

    @Throws(Exception::class)
    private fun error(response: HttpResponse?) {
        if (response == null) {
            throw Exception("服務返 回失敗")
        }
        if (response.code == HttpStatus.SC_BAD_GATEWAY) {
            throw Exception("服務502,無法連接.")
        }
    }

    private fun setHeads(httpUriRequestBase: HttpUriRequestBase) {
        this.heads?.takeIf { it.isNotEmpty() }?.let { map ->
            val headers = map.map { BasicHeader(it.key, it.value) }.toTypedArray()
            httpUriRequestBase.setHeaders(*headers)
        }
    }
}
