package com.example.volu.data

import java.util.regex.Pattern

object Constants{

    val DATABASE_NAME = "volu"

    val API_BASE_URL = "api.volu.com/v1"

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