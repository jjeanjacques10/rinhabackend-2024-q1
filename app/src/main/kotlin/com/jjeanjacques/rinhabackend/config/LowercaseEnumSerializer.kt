package com.jjeanjacques.rinhabackend.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.jjeanjacques.rinhabackend.enums.TypeTransaction
import java.io.IOException
import java.util.*


class LowercaseEnumSerializer : JsonSerializer<TypeTransaction>() {
    @Throws(IOException::class)
    override fun serialize(value: TypeTransaction, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.name.lowercase(Locale.getDefault()))
    }
}
