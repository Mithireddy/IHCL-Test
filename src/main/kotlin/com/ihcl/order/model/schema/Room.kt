package com.ihcl.order.model.schema

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class Room(
    val confirmationId: String,
    val addOnDetails: List<AddOnDetail>,
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
    val travellerDetails: List<TravellerDetail>
)