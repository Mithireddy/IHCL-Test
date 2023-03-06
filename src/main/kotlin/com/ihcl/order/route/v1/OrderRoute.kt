package com.ihcl.order.route.v1

import com.ihcl.order.model.dto.request.ConfirmOrder
import com.ihcl.order.model.dto.request.CreateBooking
import com.ihcl.order.model.dto.request.GiftCardRequest
import com.ihcl.order.model.dto.request.QCCreateBooking
import com.ihcl.order.service.v1.OrderService
import com.ihcl.order.utils.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent
import org.slf4j.Logger
import org.slf4j.LoggerFactory


fun Application.configureOrderRouting() {
    val orderService by KoinJavaComponent.inject<OrderService>(OrderService::class.java)
     val log: Logger = LoggerFactory.getLogger(javaClass)

    install(CORS){
        anyHost()
        allowHeader(HttpHeaders.ContentType)
        allowHeader("CUSTOMERHASH")
        exposeHeader("CUSTOMERHASH")
    }

    routing {
        route("orders/v1") {
            post("/create-order") {
               log.info("calling create order api")
                val customerHash = call.request.headers[CUSTOMERHASH]
                customerHash?.let {
                    val headers = OrderNumberGeneration.getUUID()
                    log.info("response header $headers")
                    val response  = orderService.createOrder(customerHash,headers)
                    log.info("creating order for cart $response ")
                    call.respond(response)
                }?: run {
                    call.fireHttpResponse(HttpStatusCode.UnprocessableEntity, CUSTOMERHASH_REQUIRED_ERR_MSG)
                }

          }
            delete("/delete-order") {
                log.info("enter into route")
                val orderId = call.request.headers[ORDERID]
                orderId?.let {
                    log.info("getting header $orderId")
                    val response = orderService.deleteOrder(orderId)
                    log.info("getting the response after deletion $response")
                    call.respond(response)
                }?:run {
                    call.fireHttpResponse(HttpStatusCode.UnprocessableEntity, ORDER_ID_REQUIRED_ERR_MSG)
                }

            }

            get("/order-details"){
                log.info("order details")
                val orderId = call.request.headers[ORDERID]
                orderId?.let {
                    val response = orderService.getOrder(orderId)
                    log.info("getting order details $response")
                    call.respond(response!!)
                }?:run {
                    call.fireHttpResponse(HttpStatusCode.UnprocessableEntity, ORDER_ID_REQUIRED_ERR_MSG)
                }
            }

            post("create-order/gift-card") {
                log.info("gift card purchase")
                val customerHash = call.request.headers[CUSTOMERHASH]
                customerHash?.let {
                    val headers = OrderNumberGeneration.getUUID()
                    val response = orderService.createOrderForGiftCard(customerHash, call.receive() as GiftCardRequest, headers)
                    call.respond(response)
                }?: run {
                    call.fireHttpResponse(HttpStatusCode.UnprocessableEntity,CUSTOMERHASH_REQUIRED_ERR_MSG )
                }
            }
            post("/create-booking") {
                log.info("create booking")
              val response = orderService.createBooking(call.receive() as CreateBooking)
                 log.info("response -------4${response}")
                call.respond(response)
            }

            post("/confirm-order") {
                log.info("Confirm order")
                //call.fireHttpResponse(HttpStatusCode.Accepted,orderService.confirmOrder(call.receive()))
                val response = orderService.confirmOrder(call.receive() as ConfirmOrder)
                call.respond(response)
            }
            post("/create-booking/gift-card") {
                log.info("create booking")
                val response = orderService.createBookingQC(call.receive() as QCCreateBooking)
                log.info("response -------4${response}")
                call.respond(response)
            }

        }
    }
}










