package com.ihcl.order.model.dto.response

import kotlinx.serialization.Serializable
@Serializable
data class OrderStatusResponse(
    val amount: Int,
    val amount_refunded: Int,
    val auth_type: String,
    val bank_error_code: String,
    val bank_error_message: String,
    val currency: String,
    val customer_email: String,
    val customer_id: String,
    val customer_phone: String,
    val date_created: String,
    val gateway_id: Int,
    val id: String,
    val mandate: Mandate,
    val merchant_id: String,
    val order_id: String,
    val payment_gateway_response: PaymentGatewayResponse,
    val payment_links: PaymentLinks,
    val payment_method: String,
    val payment_method_type: String,
    val product_id: String,
    val refunded: Boolean,
    val status: String,
    val status_id: Int,
    val txn_id: String,
    val txn_uuid: String,
    val udf1: String,
    val udf10: String,
    val udf2: String,
    val udf3: String,
    val udf4: String,
    val udf5: String,
    val udf6: String,
    val udf7: String,
    val udf8: String,
    val udf9: String
)
@Serializable
data class Mandate(
    val mandate_id: String,
    val mandate_status: String,
    val mandate_token: String
)
@Serializable
data class PaymentGatewayResponse(
    val auth_id_code: String,
    val created: String,
    val epg_txn_id: String,
    val resp_code: String,
    val resp_message: String,
    val rrn: String,
    val txn_id: String
)
@Serializable
data class PaymentLinks(
    val iframe: String,
    val mobile: String,
    val web: String
)