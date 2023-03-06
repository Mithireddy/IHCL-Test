package com.ihcl.order.repository

import com.ihcl.order.config.MongoConfig
import com.ihcl.order.model.schema.Order
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class OrderRepository {
   private val orderCollection: CoroutineCollection<Order> = MongoConfig.getDatabase().getCollection()
    val log: Logger = LoggerFactory.getLogger(javaClass)

   suspend fun saveOrder(order: Order) {
      //order.modifiedTimestamp = Date()
      orderCollection.save(order)
   }

   suspend fun findOrderByOrderId(orderId: String): Order? {
       log.info("enter into repository $orderId")
       val order = orderCollection.findOneById(orderId)
       log.info("oder details $order")
      return order
   }

   suspend fun deleteOrderById(order: Order){
     val result = orderCollection.deleteOneById(order.orderId)
      log.info("deleted item $result")
   }



}
