package com.uiza.api.helps

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.joda.time.DateTime
import java.io.IOException
import java.util.*

class JsonDateSerializer : JsonSerializer<Date>() {
    @Throws(IOException::class)
    override fun serialize(value: Date, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(DateTime(value.time).toString(DATE_TIME_FMT))
    }

    companion object {
        private const val DATE_TIME_FMT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }
}