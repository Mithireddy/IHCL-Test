package com.ihcl.order.model.exception

import io.ktor.http.*

class HttpResponseException(val data: Any, val statusCode: HttpStatusCode) : Exception()