package com.ihcl.order.model.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown =  true)
data class OrderLineItems(
    var cartId: String,
    val items : List<CartResponseItems>,
    val basePrice: Double,
    val tax : Double,
    val totalPrice : Double,
    val createdDate: String,
    val modifiedDate:  String

)

@Serializable
@JsonIgnoreProperties(ignoreUnknown =  true)
data class CartResponseItems(
    val category: String,
    val hotel: MutableList<HotelDetails>
)

@Serializable
@JsonIgnoreProperties(ignoreUnknown =  true)
data class HotelDetails(
    val hotelId: String,
    val hotelName: String,
    val hotelAddress: String,
    val pinCode: String,
    val state: String,
    val checkIn: String,
    val checkOut: String,
    val room: MutableList<RoomDetails>,
)
@Serializable
@JsonIgnoreProperties(ignoreUnknown =  true)
data class RoomDetails(
    val roomId: String,
    val roomType: String,
    val roomName: String,
    val cost: Double,
    var children: String,
    var adult: String,
    val rateCode: String,
    val currency: String,
    val rateDescription: String,
    val roomDescription: String,
)
