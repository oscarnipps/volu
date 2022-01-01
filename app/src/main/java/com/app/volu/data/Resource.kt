package com.app.volu.data

class Resource<T>(val data: T?, val status: Status, val message: String?) {

    companion object {

        fun <T> loading(): Resource<T> {
            return Resource(null, Status.LOADING, null)
        }

        fun <T> error(message: String?): Resource<T> {
            return Resource(null, Status.ERROR, message)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(data, Status.SUCCESS, null)
        }

    }

    enum class Status {
        LOADING, ERROR, SUCCESS
    }

}