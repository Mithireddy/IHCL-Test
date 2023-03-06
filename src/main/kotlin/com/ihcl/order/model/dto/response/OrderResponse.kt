package com.ihcl.order.model.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.ihcl.order.model.dto.request.GiftCardRequest
import com.ihcl.order.model.schema.*
import com.microsoft.applicationinsights.core.dependencies.google.j2objc.annotations.AutoreleasePool
import kotlinx.serialization.Serializable
import org.intellij.lang.annotations.Identifier

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class OrderResponse(
    val orderId: String,
    val customerHash: String,
    val orderType: OrderType = OrderType.HOTEL_BOOKING,
    val customerEmail : String,
    val customerId: String,
    val customerMobile: Long,
    val channel: String,
    val currencyCode: String,
    val discountAmount: Double,
    val gradTotal: Double,
    val isRefundable: Boolean,
    val payableAmount: Double,
    val transactionId: String,
    val billingAddress: BillingAddressDetails,
    val offers: List<OffersDetails>,
    val orderLineItems: List<OrderLineItemDetails>,
    val paymentDetails: List<PaymentDetailInfo>,
    val paymentMethod: PaymentMethod = PaymentMethod.PAY_ONLINE,
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val orderStatus: OrderStatus = OrderStatus.PENDING,
    val refundAmount: Double,
    val taxAmount: Double
)
@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class RoomDetailsInfo(
    val confirmationId: String,
    val addOnDetails: List<AddOnDetailInfo>,
    val basePrice: Double,
    val checkIn: String,
    val checkOut: String,
    val discountAmount: Double,
    val discountCode: String,
    val isModified: Boolean,
    val isRefundedItem: Boolean,
    val modifiedWith: String,
    val payableAmount: Double,
    val price: Double,
    val rateDescription: String,
    val refundedAmount: String,
    val roomDescription: String,
    val roomId: String,
    val roomName: String,
    val roomType: String,
    val travellerDetails: List<TravellerDetailsInfo>
)

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class TravellerDetailsInfo(
    val dateOfBirth: String,
    val address: String,
    val city: String,
    val countryCode: String,
    val customerId: String,
    val customerType: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val gstNumber: String,
    val lastName: String,
    val membershipNumber: String,
    val mobile: String,
    val name: String,
    val secondaryContact: String,
    val state: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class BillingAddressDetails(
    val address1: String,
    val address2: String,
    val address3: String,
    val city: String,
    val country: String,
    val firstName: String,
    val lastName: String,
    val pinCode: String,
    val state: String,
    val phoneNumber: Long,
    val countyCodeISO: String
)
@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class OrderLineItemDetails(
    val hotel: HotelDetailsInfo
)

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class HotelDetailsInfo(
    val addOnDetails: List<AddOnDetailInfo>,
    val address: AddressDetails,
    val bookingNumber: String,
    val category: String,
    val hotelId: String,
    val invoiceNumber: String,
    val invoiceUrl: String,
    val name: String,
    val reservationId: String,
    val rooms: List<RoomDetailsInfo>,
    val status: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class AddressDetails(
    val city: String,
    val contactNumber: String,
    val directions: String,
    val landmark: String,
    val lat: String,
    val long: String,
    val mapLink: String,
    val pinCode: String,
    val state: String,
    val street: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class AddOnDetailInfo(
    val addOnCode: String,
    val addOnDesc: String,
    val addOnName: String,
    val addOnPrice: Double,
    val addOnType: String
)
@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class PaymentDetailInfo(
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
data class OffersDetails(
    val offerAmount: Double,
    val offerName: String,
    val offerType: String
)


