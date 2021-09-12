package com.example.volu.data

import java.util.regex.Pattern

object Constants{

    const val DATABASE_NAME = "volu"
    const val API_BASE_URL = "https://restcountries.eu/rest/v2/"
    const val ENDPOINT_REGISTER :  String = "/register"
    const val ENDPOINT_LOGIN :  String = "/login"

    @JvmField
    val EMAIL_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    ).toString()

}