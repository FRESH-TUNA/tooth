package com.freshtuna.openshop.endpoint.external.auth.request

import com.fasterxml.jackson.annotation.JsonProperty

data class LocalSignInRequest(
    @JsonProperty("id") val id: String,
    @JsonProperty("password") val password: String
)
