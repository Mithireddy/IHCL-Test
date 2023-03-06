package com.ihcl.order.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID

object OrderNumberGeneration {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun getUUID(): String{
        return UUID.randomUUID().toString()
    }

}