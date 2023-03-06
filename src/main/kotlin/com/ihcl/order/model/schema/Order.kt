package com.ihcl.order.model.schema

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.ihcl.order.model.dto.request.GiftCardRequest
import com.microsoft.applicationinsights.core.dependencies.google.j2objc.annotations.AutoreleasePool
import kotlinx.serialization.Serializable
import org.intellij.lang.annotations.Identifier

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class Order(
    val orderId: String,
    val customerHash: String,
    val customerEmail : String,
    val customerId: String,
    val customerMobile: Long,
    val channel: String,
    val currencyCode: String,
    val discountAmount: Double,
    val gradTotal: Double,
    val isRefundable: Boolean,
    val payableAmount: Double,
    val orderType: OrderType,
    val transactionId: String,
    val billingAddress: BillingAddress,
    val offers: List<Offers>,
    val orderLineItems: List<OrderLineItem>,
    val paymentDetails: List<PaymentDetail>,
    val paymentMethod: PaymentMethod = PaymentMethod.PAY_ONLINE,
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val orderStatus: OrderStatus = OrderStatus.PENDING,
    val refundAmount: Double,
    val taxAmount: Double
)
@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class OrderLineItem(
    val hotel: Hotel?,
    val giftCardDetails: GiftCard?,
)

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class Hotel(
    val addOnDetails: List<AddOnDetail>,
    val address: Address,
    val bookingNumber: String,
    val category: String,
    val hotelId: String?,
    val invoiceNumber: String,
    val invoiceUrl: String,
    val name: String,
    val reservationId: String,
    val rooms: List<Room>?,
    val status: String
)
@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class PaymentDetail(
    val paymentMethod: String,
    val paymentMethodType: String,
    val txnGateway: String,
    val txnId: String,
    val txnNetAmount: Double,
    val txnStatus: String,
    val txnUUID: String
)
@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class Offers(
    val offerAmount: Double,
    val offerName: String,
    val offerType: String
)

enum class PaymentStatus {
    FAILED, PENDING, SUCCESS, CANCELLED, REFUND_INITIATED, PARTIAL_REFUND_INITIATED, REFUND_SUCCESSFUL, REFUND_REJECTED, REFUND_FAILED,
    PARTIAL_REFUND_SUCCESSFUL, FULL_REFUND_SUCCESSFUL
}

enum class OrderStatus {
    AWAITING_CONFIRMATION, PENDING, CREATED, ALLOCATED, CANCEL_INITIATED, CANCELLATION_REJECTED, CANCELLED, FAILED, REFUND_INITIATED, REFUND_SUCCESSFUL, REFUND_REJECTED,
    PARTIAL_REFUND_INITIATED, FULL_REFUND_INITIATED, PARTIAL_REFUND_SUCCESSFUL, FULL_REFUND_SUCCESSFUL
}

enum class OrderType {
    HOTEL_BOOKING, GIFT_CARD_PURCHASE, RESTAURANTS, SPA
}
enum class PaymentMethod {
    PAY_ONLINE, PAY_AT_HOTEL
}


