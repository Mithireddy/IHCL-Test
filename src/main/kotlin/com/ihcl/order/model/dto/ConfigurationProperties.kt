package com.ihcl.order.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ConfigurationProperties(
    val database_url : String,
    val database_name : String
)
