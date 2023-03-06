package com.ihcl.order.service.v1

import com.ihcl.order.client.client
import com.ihcl.order.model.dto.request.ConfirmOrder
import com.ihcl.order.model.dto.request.CreateBooking
import com.ihcl.order.model.dto.request.GiftCardRequest
import com.ihcl.order.model.dto.response.*
import com.ihcl.order.model.schema.Order
import com.ihcl.order.model.schema.OrderType
import com.ihcl.order.repository.OrderRepository
import com.ihcl.order.utils.CUSTOMERHASH
import com.ihcl.order.utils.DataMapperUtils
import com.microsoft.applicationinsights.core.dependencies.http.HttpStatus
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.koin.java.KoinJavaComponent
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class OrderService {
    private val orderRepository by KoinJavaComponent.inject<OrderRepository>(OrderRepository::class.java)
    val log: Logger = LoggerFactory.getLogger(javaClass)

    suspend fun createOrder(customerHash: String, headers: String): Any {
        try {
            log.info("calling get cart api")
            val cartResponse = client.get("http://20.219.150.172:8081/v1/cart-service/cart") {
                contentType(ContentType.Application.Any)
                headers {
                    append(CUSTOMERHASH, customerHash)
                }
            }
            val response = cartResponse.body<OrderLineItems>()
            val orderType = OrderType.HOTEL_BOOKING
            val order = DataMapperUtils.mapCreateOrder(customerHash, request = null,orderType,response, headers)
            log.info("get cart details for order line items ${cartResponse.body<OrderLineItems>()}")
            orderRepository.saveOrder(order)
            return DataMapperUtils.mapCartToCreateOrder(customerHash,response, headers)
        }catch (e:Exception){
            log.info("Exception occured while calling api ${e.message}")
        }
        return Any()
    }

    suspend fun deleteOrder(orderId: String): String{
        log.info("enter into delete order service")
         val order = orderRepository.findOrderByOrderId(orderId)
        log.info("getting order by id $order")
           orderRepository.deleteOrderById(order!!)
        return "SUCCESS"
    }

    suspend fun getOrder(orderId: String):Order?{
        log.info("enter into get order service")
        return orderRepository.findOrderByOrderId(orderId)
    }
    suspend fun createOrderForGiftCard(customerHash: String, request: GiftCardRequest, headers: String ): GiftCardResponse {
        log.info("create order for gift card")
        val orderType = OrderType.GIFT_CARD_PURCHASE
        val order = DataMapperUtils.mapCreateOrder(customerHash, request,orderType, response = null, headers)
        orderRepository.saveOrder(order)
        return  DataMapperUtils.mapGiftCardToCreateOrder(customerHash, request,headers)
    }

    suspend fun createBooking(createBooking: Any): BookingResponse{
        try {
            val response = client.post("http://20.219.150.172:8085/hudini/createBooking"){
                log.info("create booking API ${createBooking}")
                headers{
                    append(HttpHeaders.ContentType,"application/json")
                }
                setBody(createBooking)
            }
            return response.body()
        }catch (e:Exception){
            log.info("Exception occured while calling api ${e.message}")
            throw  Exception()
        }
    }

    suspend fun confirmOrder(confirmOrder: Any): Any {
            try {
                val response = client.post("http://20.219.150.172:8083/orderStatus") {
                    contentType(ContentType.Application.Json)
                    setBody(confirmOrder)
                }
                val responseValue = response.status
                val bookingId = orderStatus(responseValue)
                return bookingId
            }catch (e:Exception){
                log.info("Exception occured while calling api ${e.message}")
                throw  Exception()
            }
        }
    suspend fun createBookingQC(createBooking: Any): BookingResponse{
        try {
            val response = client.post("http://20.219.150.172:8084/gc/orderGC"){
                log.info("create booking API ${createBooking}")
                headers{
                    append(HttpHeaders.ContentType,"application/json")
                }
                setBody(createBooking)
            }
            return response.body()
        }catch (e:Exception){
            log.info("Exception occured while calling api ${e.message}")
            throw  Exception()
        }
    }

    suspend fun orderStatus(response: HttpStatusCode): Any{
        var bookingId = Any()
        if(response == HttpStatusCode.OK){

            bookingId = createBookingQC(DataMapperUtils.mapOrderStatus())
        }else{
            log.info("order status pending")
        }
        return bookingId
    }

}

