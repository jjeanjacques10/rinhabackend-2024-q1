package main.kotlin.com.jjeanjacques.rinhabackend.exception

import java.lang.RuntimeException

class ClientNotFound(message: String?) : RuntimeException(message)