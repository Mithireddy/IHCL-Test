package com.ihcl.order.utils


import com.ihcl.order.model.dto.request.*
import com.ihcl.order.model.dto.response.*
import com.ihcl.order.model.schema.*
import com.ihcl.order.model.schema.OrderLineItem
import com.ihcl.order.model.schema.OrderStatus
import com.ihcl.order.model.schema.OrderType
import com.ihcl.order.model.schema.PaymentDetail
import com.ihcl.order.model.schema.PaymentMethod
import com.ihcl.order.model.schema.PaymentStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object DataMapperUtils {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun mapCreateOrder(
        customerHash: String,
        request: GiftCardRequest?,
        orderType: OrderType, response: OrderLineItems?, headers: String): Order{
        var total = 0.0
        val giftCardDetails = request?.let {
            total = (request.giftCardDetails.amount).times(request.giftCardDetails.quantity)
            log.info("total amount $total")
            GiftCard(
                deliveryMethods = DeliveryMethodsDto(
                    phone = request.deliveryMethods.phone,
                    sms = request.deliveryMethods.sms,
                    whatsApp = request.deliveryMethods.whatsApp
                ),
                giftCardDetails = GiftCardDetailsDto(
                    amount = request.giftCardDetails.amount,
                    sku = request.giftCardDetails.sku,
                    quantity = request.giftCardDetails.quantity,
                    type = request.giftCardDetails.type
                ),
                promoCode = request.promoCode,
                receiverAddress = ReceiverAddressDto(
                    addressLine1 = request.receiverAddress.addressLine1,
                    addressLine2 = request.receiverAddress.addressLine2,
                    city = request.receiverAddress.city,
                    state = request.receiverAddress.state,
                    country = request.receiverAddress.country,
                    pinCode = request.receiverAddress.pinCode,
                ),
                receiverDetails = ReceiverDetailsDto(
                    email = request.receiverDetails.email,
                    firstName = request.receiverDetails.firstName,
                    lastName = request.receiverDetails.lastName,
                    message = request.receiverDetails.message,
                    phone = request.receiverDetails.phone,
                    rememberMe = request.receiverDetails.rememberMe,
                    scheduleOn = request.receiverDetails.scheduleOn
                ),
                senderDetails = SenderDetailsDto(
                    email = request.senderDetails.email,
                    firstName = request.senderDetails.firstName,
                    lastName = request.senderDetails.lastName,
                    phone = request.senderDetails.phone,
                    registerAsNeuPass = request.senderDetails.registerAsNeuPass
                )
            )
        }
        val room = response?.let {orderLine ->
            orderLine.items[0].hotel[0].room.map { room ->
                Room(
                    confirmationId = "",
                    basePrice = orderLine.basePrice,
                    checkOut = orderLine.items[0].hotel[0].checkOut,
                    checkIn = orderLine.items[0].hotel[0].checkIn,
                    roomId = room.roomId,
                    roomName = room.roomName,
                    roomType = room.roomType,
                    rateDescription = room.rateDescription,
                    roomDescription = room.roomDescription,
                    addOnDetails = listOf(
                        AddOnDetail(
                            addOnCode = "",
                            addOnType = "",
                            addOnPrice = 0.0,
                            addOnName = "",
                            addOnDesc = ""
                        )
                    ),
                    discountAmount = 0.0,
                    discountCode = "",
                    isModified = true,
                    isRefundedItem = true,
                    modifiedWith = "",
                    payableAmount = 0.0,
                    price = room.cost,
                    refundedAmount = "",
                    travellerDetails = listOf(
                        TravellerDetail(
                            dateOfBirth = "",
                            address = "",
                            city = "",
                            countryCode = "",
                            customerId = "",
                            customerType = "",
                            email = "",
                            firstName = "",
                            gender = "",
                            gstNumber = "",
                            lastName = "",
                            membershipNumber = "",
                            mobile = "",
                            name = "",
                            secondaryContact = "",
                            state = ""
                        )
                    )
                )

            }
        }
        return Order(
            headers,
            customerHash,
            customerEmail = "",
            customerId = "",
            customerMobile = 45678910,
            orderType = orderType,
            billingAddress = BillingAddress(
                address1 = "",
                address2 = "",
                address3 = "",
                city = "",
                country = "",
                pinCode = "",
                firstName = "",
                lastName = "",
                phoneNumber = 898989898,
                countyCodeISO = "",
                state = ""
            ),
            channel = "",
            currencyCode = "",
            discountAmount = 0.0,
            taxAmount = 0.0,
            payableAmount = total,
            gradTotal = 0.0,
            isRefundable = true,
            offers = listOf(
                Offers(
                offerAmount = 0.0,
                offerName = "",
                offerType = ""
            )
            ),
            orderLineItems = listOf(
                OrderLineItem(
                    hotel = Hotel(
                        addOnDetails = listOf(
                            AddOnDetail(
                                addOnCode = "",
                                addOnDesc = "",
                                addOnName = "",
                                addOnPrice = 0.0,
                                addOnType = ""
                            )
                        ),
                        address = Address(
                            city = "",
                            contactNumber = "",
                            directions = "",
                            landmark = "",
                            lat = "",
                            long = "",
                            mapLink = "",
                            pinCode = "",
                            state = "",
                            street = ""
                        ),
                        bookingNumber = "",
                        category = "",
                        hotelId = response?.items?.get(0)?.hotel?.get(0)?.hotelId,
                        invoiceNumber = "",
                        invoiceUrl = "",
                        name = "",
                        reservationId = "",
                        rooms = room,
                        status = ""
                    ),
                    giftCardDetails = giftCardDetails
                    )
             ),
            orderStatus = OrderStatus.PENDING,
            paymentDetails = listOf(
                PaymentDetail(
                    paymentMethod = "",
                    paymentMethodType = "",
                    txnGateway = "",
                    txnId = "",
                    txnNetAmount = 0.0,
                    txnStatus = "",
                    txnUUID = ""
                )
            ),
            paymentMethod = PaymentMethod.PAY_ONLINE,
            paymentStatus = PaymentStatus.PENDING,
            refundAmount = 0.0,
            transactionId = ""
        )
    }

    fun mapGiftCardToCreateOrder(customerHash: String, request: GiftCardRequest, headers: String): GiftCardResponse {
        val total = (request.giftCardDetails.amount).times(request.giftCardDetails.quantity)
        log.info("total amount $total")
        val giftCardDetails = GiftCardDto(
            deliveryMethods = DeliveryMethodsInfo(
                phone = request.deliveryMethods.phone,
                sms = request.deliveryMethods.sms,
                whatsApp = request.deliveryMethods.whatsApp
            ),
            giftCardDetails = GiftCardInfo(
                amount = request.giftCardDetails.amount,
                sku = request.giftCardDetails.sku,
                quantity = request.giftCardDetails.quantity,
                type = request.giftCardDetails.type
            ),
            promoCode = request.promoCode,
            receiverAddress = ReceiverAddressDetails(
                addressLine1 = request.receiverAddress.addressLine1,
                addressLine2 = request.receiverAddress.addressLine2,
                city = request.receiverAddress.city,
                state = request.receiverAddress.state,
                country = request.receiverAddress.country,
                pinCode = request.receiverAddress.pinCode,
            ),
            receiverDetails = ReceiverInfo(
                email = request.receiverDetails.email,
                firstName = request.receiverDetails.firstName,
                lastName = request.receiverDetails.lastName,
                message = request.receiverDetails.message,
                phone = request.receiverDetails.phone,
                rememberMe = request.receiverDetails.rememberMe,
                scheduleOn = request.receiverDetails.scheduleOn
            ),
            senderDetails = SenderInfo(
                email = request.senderDetails.email,
                firstName = request.senderDetails.firstName,
                lastName = request.senderDetails.lastName,
                phone = request.senderDetails.phone,
                registerAsNeuPass = request.senderDetails.registerAsNeuPass
            )
        )
        return GiftCardResponse(
            headers,
            customerHash,
            customerEmail = "",
            customerMobile = 878777,
            channel = "",
            currencyCode = "",
            discountAmount = 0.0,
            payableAmount = total,
            gradTotal = 0.0,
            isRefundable = true,
            orderType = OrderType.GIFT_CARD_PURCHASE,
            transactionId = "",
            billingAddress = GiftCardBillingAddress(
                address1 = "",
                address2 = "",
                address3 = "",
                city = "",
                country = "",
                pinCode = "",
                firstName = "",
                lastName = "",
                phoneNumber = 898989898,
                countyCodeISO = "",
                state = ""
            ),
            offers = listOf(
                OffersForGiftCard(
                    offerAmount = 0.0,
                    offerName = "",
                    offerType = ""
                )
            ),
            orderItems = listOf(
                OrderItem(
                    shippingAddress = GiftCardShippingAddress(
                       address1 = "",
                        address2 = "",
                        address3 = "",
                        firstName = "",
                        lastName = "",
                        city = "",
                        country = "",
                        pinCode = "",
                        state = "",
                        phoneNumber = 989899,
                        countyCodeISO = ""
                    ),
                    invoiceNumber = "",
                    invoiceUrl = "",
                    name = "",
                    status = "",
                    giftCard =giftCardDetails
                )
            ),
           customerId = "",
            paymentInfo = listOf(
                PaymentInfo(
                    paymentMethod = "",
                    paymentMethodType = "",
                    txnGateway = "",
                    txnId = "",
                    txnNetAmount = 0.0,
                    txnStatus = "",
                    txnUUID = ""
                )
            ),
            paymentMethod = PaymentMethod.PAY_ONLINE,
            paymentStatus = PaymentStatus.PENDING,
            refundAmount = 0.0,
            taxAmount = 0.0

        )
    }

    fun mapCartToCreateOrder(customerHash: String, response : OrderLineItems, headers: String): OrderResponse {
        val room = response.items[0].hotel[0].room.map {
            RoomDetailsInfo(
                confirmationId = "",
                basePrice = response.basePrice,
                checkOut = response.items[0].hotel[0].checkOut,
                checkIn = response.items[0].hotel[0].checkIn,
                roomId = it.roomId,
                roomName = it.roomName,
                roomType = it.roomType,
                rateDescription = it.rateDescription,
                roomDescription = it.roomDescription,
                addOnDetails = listOf(
                    AddOnDetailInfo(
                        addOnCode = "",
                        addOnType = "",
                        addOnPrice = 0.0,
                        addOnName = "",
                        addOnDesc = ""
                    )
                ),
                discountAmount = 0.0,
                discountCode = "",
                isModified = true,
                isRefundedItem = true,
                modifiedWith = "",
                payableAmount = 0.0,
                price = it.cost,
                refundedAmount = "",
                travellerDetails = listOf(
                    TravellerDetailsInfo(
                        dateOfBirth = "",
                        address = "",
                        city = "",
                        countryCode = "",
                        customerId = "",
                        customerType = "",
                        email = "",
                        firstName = "",
                        gender = "",
                        gstNumber = "",
                        lastName = "",
                        membershipNumber = "",
                        mobile = "",
                        name = "",
                        secondaryContact = "",
                        state = ""
                    )
                )
            )

        }.toMutableList()

        return OrderResponse(
            headers,
            customerHash,
            customerEmail = "",
            orderType = OrderType.HOTEL_BOOKING,
            customerMobile = 89878777,
            channel = "",
            currencyCode = "",
            discountAmount = 0.0,
            payableAmount = 0.0,
            gradTotal = 0.0,
            isRefundable = true,
            billingAddress = BillingAddressDetails(
                address1 = "",
                address2 = "",
                address3 = "",
                city = "",
                country = "",
                pinCode = "",
                firstName = "",
                lastName = "",
                phoneNumber = 898989898,
                countyCodeISO = "",
                state = ""
            ),
            offers = listOf(
                OffersDetails(
                    offerAmount = 0.0,
                    offerName = "",
                    offerType = ""
                )
            ),
            orderLineItems = listOf(
                OrderLineItemDetails(
                    hotel = HotelDetailsInfo(
                        addOnDetails = listOf(
                            AddOnDetailInfo(
                                addOnCode = "",
                                addOnDesc = "",
                                addOnName = "",
                                addOnPrice = 0.0,
                                addOnType = ""
                            )
                        ),
                        address = AddressDetails(
                            city = "",
                            contactNumber = "",
                            directions = "",
                            landmark = "",
                            lat = "",
                            long = "",
                            mapLink = "",
                            pinCode = "",
                            state = "",
                            street = ""
                        ),
                        bookingNumber = "",
                        category = "",
                        hotelId = response.items[0].hotel[0].hotelId,
                        invoiceNumber = "",
                        invoiceUrl = "",
                        name = "",
                        reservationId = "",
                        rooms = room,
                        status = ""
                    )
                )
            ),
            customerId = "",
            paymentDetails = listOf(
                PaymentDetailInfo(
                    paymentMethod = "",
                    paymentMethodType = "",
                    txnGateway = "",
                    txnId = "",
                    txnNetAmount = 0.0,
                    txnStatus = "",
                    txnUUID = ""
                )
            ),
            paymentMethod = PaymentMethod.PAY_ONLINE,
            paymentStatus = PaymentStatus.PENDING,
            refundAmount = 0.0,
            taxAmount = 0.0,
            transactionId = ""
        )
    }

    fun mapOrderStatus(): CreateBooking{
        return CreateBooking(
          billingInfo = BillingInfo(
              email = "",
              firstName = "",
              gender = "",
              lastName = "",
              phoneNo = "",
              title = "",
          ),
            email = "",
            hotelId = "",
            isLoyaltyMember = true,
            payment = Payment(
                cardCode = "",
                cardHolderName = "",
                cardNumber = "",
                creditCardType = "",
                expirationDate = "",
                paymentType = ""
            ),
            personalisations = listOf( Personalisation(
                key = "",
                value = ""
            )),
            promoCode = "",
            roomStay = RoomStay(
                adultCount = 2,
                childCount = 1,
                endDate = "10/3/2023",
                startDate = "3/3/2023",
                rateCode = "",
                roomCode = "",
                roomName = ""
            )
        )
    }

}