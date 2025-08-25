package com.dingtalk.util

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter: TypeAdapter<LocalDateTime>() {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    override fun write(p0: JsonWriter, p1: LocalDateTime?) {
        if(p1==null){
            p0.nullValue()
        }else{
            p0.value(p1.format(formatter))
        }
    }

    override fun read(p0: JsonReader): LocalDateTime? {
        val value = p0.nextString()
        if(value.isNullOrBlank()){
            return null
        }
        return LocalDateTime.parse(value, formatter)
    }
}
