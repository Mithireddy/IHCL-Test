package com.ihcl.order.service

import com.ihcl.order.service.v1.OrderService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val serviceModule = module {
   singleOf(:: OrderService)
}