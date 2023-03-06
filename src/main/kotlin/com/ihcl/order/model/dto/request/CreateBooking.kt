package com.ihcl.order.model.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateBooking(
    val billingInfo: BillingInfo,
    val email: String,
    val hotelId: String,
    val isLoyaltyMember: Boolean,
    val payment: Payment,
    val personalisations: List<Personalisation>,
    val promoCode: String,
    val roomStay: RoomStay
)
@Serializable
data class BillingInfo(
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val phoneNo: String,
    val title: String
)
@Serializable
data class Payment(
    val cardCode: String,
    val cardHolderName: String,
    val cardNumber: String,
    val creditCardType: String,
    val expirationDate: String,
    val paymentType: String
)

@Serializable
data class Personalisation(
    val key: String,
    val value: String
)
@Serializable
data class RoomStay(
    val adultCount: Int,
    val childCount: Int,
    val endDate: String,
    val rateCode: String,
    val roomCode: String,
    val roomName: String,
    val startDate: String
)