package com.ihcl.order.model.dto.request

data class QCCreateBooking(
    val address: AddressDto,
    val billing: Billing,
    val deliveryMode: String,
    val payments: List<PaymentDto>,
    val products: List<Product>,
    val refno: String
)
data class AddressDto(
    val billToThis: Boolean,
    val city: String,
    val company: String,
    val country: String,
    val email: String,
    val firstname: String,
    val lastname: String,
    val line1: String,
    val line2: String,
    val postcode: String,
    val region: String,
    val telephone: String
)
data class Billing(
    val city: String,
    val company: String,
    val country: String,
    val email: String,
    val firstname: String,
    val lastname: String,
    val line1: String,
    val line2: String,
    val postcode: String,
    val region: String,
    val telephone: String
)
data class PaymentDto(
    val amount: Int,
    val code: String
)
data class Product(
    val currency: String,
    val price: Int,
    val qty: Int,
    val sku: String,
    val theme: String
)