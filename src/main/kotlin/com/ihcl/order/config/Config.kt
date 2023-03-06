package com.ihcl.order.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ihcl.order.model.dto.ConfigurationProperties
import io.ktor.server.application.*
import java.net.URL

object Configuration {
    lateinit var env: ConfigurationProperties

    fun initConfig(environment: ApplicationEnvironment){
        env = ConfigurationProperties(
            database_url = environment.config.property("ktor.database.connectionString").toString(),
            database_name = environment.config.property("ktor.database.databaseName").toString(),
        )
    }
}
