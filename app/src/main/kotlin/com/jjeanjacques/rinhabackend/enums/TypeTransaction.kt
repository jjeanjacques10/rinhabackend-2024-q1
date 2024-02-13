package com.jjeanjacques.rinhabackend.enums

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.jjeanjacques.rinhabackend.config.LowercaseEnumSerializer

@JsonSerialize(using = LowercaseEnumSerializer::class)
enum class TypeTransaction {
    C,
    D
}