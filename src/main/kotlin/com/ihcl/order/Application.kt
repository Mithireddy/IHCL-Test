package com.ihcl.order


import com.ihcl.order.config.Configuration
import com.ihcl.order.config.MongoConfig
import com.ihcl.order.plugins.*
import com.ihcl.order.route.v1.configureOrderRouting
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)
fun Application.module() {
    MongoConfig.getDatabase()
    configureDependencyInjection()
    configureHTTP()
    configureSerialization()
    configureOrderRouting()
}




