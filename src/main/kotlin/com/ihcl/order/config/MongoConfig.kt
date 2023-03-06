package com.ihcl.order.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.connection.ConnectionPoolSettings
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

object MongoConfig {
    private val log: Logger = LoggerFactory.getLogger(javaClass)
    private var client: CoroutineClient
    private var database: CoroutineDatabase

    init {
        log.info("Mongo Config Loaded")
        client = KMongo.createClient(
            MongoClientSettings.builder()
                .applyConnectionString(ConnectionString("mongodb://ihcldevadmin:LGxXSn7uLasteksMxB9yjtk1w4mI2WnQBA7yrfdVHBsKSiZeoRi2zZmaj3G9Vs4bVBLssIAy5n0RACDbWJRd1g%3D%3D@ihcldevadmin.mongo.cosmos.azure.com:10255/?ssl=true&replicaSet=globaldb&retrywrites=false&maxIdleTimeMS=120000&appName=@ihcldevadmin"))
                .applyToConnectionPoolSettings {
                    ConnectionPoolSettings.builder().maxConnectionIdleTime(120000, TimeUnit.MILLISECONDS)
                        .minSize(5).maxSize(40)
                }
                 .applicationName("order-service")
                 .build()).coroutine
        database = client.getDatabase("IHCLDataBase")

        log.info("Connected to MongoDB $database")
    }

    fun getDatabase(): CoroutineDatabase {
        log.info("creating DB $database")
        return database
    }
}



