package com.ihcl.order.model.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class GiftCardRequest(
    val deliveryMethods: DeliveryMethods,
    val giftCardDetails: GiftCardDetails,
    val promoCode: String,
    val receiverAddress: ReceiverAddress,
    val receiverDetails: ReceiverDetails,
    val senderDetails: SenderDetails
)
@Serializable
data class GiftCardDetails(
    val amount: Double,
    val quantity: Int,
    val sku: String,
    val type: String
)
@Serializable
data class ReceiverAddress(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val country: String,
    val pinCode: String,
    val state: String
)
@Serializable
data class ReceiverDetails(
    val email: String,
    val firstName: String,
    val lastName: String,
    val message: String,
    val phone: String,
    val rememberMe: Boolean,
    val scheduleOn: String
)
@Serializable
data class SenderDetails(
    val email: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val registerAsNeuPass: Boolean
)
@Serializable
data class DeliveryMethods(
    val phone: String,
    val sms: Boolean,
    val whatsApp: Boolean
)