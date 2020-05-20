package com.uiza.api.helps

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.joda.time.DateTime
import java.io.IOException
import java.util.*

class JsonDateDeserializer : JsonDeserializer<Date>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Date {
        return DateTime.parse(p.text).toDate()
    }
}