package com.app.volu.data

import java.util.regex.Pattern

object Constants{

    const val DATABASE_NAME = "volu"
    const val API_BASE_URL = "http://api-volu.herokuapp.com/"

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

    const val SHARED_PREFERENCE_KEY = "volu-preference"
    const val IS_LOGGED_IN = "is_logged_in"
    const val ACCESS_TOKEN = "access_token"
    const val HAS_LAUNCHED = "has_launched"

}