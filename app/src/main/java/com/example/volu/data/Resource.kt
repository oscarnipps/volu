package com.example.volu.data

class Resource<T>(val data: T?, val status: Status, val message: String?) {

    companion object{

        fun <T> loading(data: T?): Resource<T> {
            return Resource<T>(data, Status.LOADING, null)
        }

        fun <T> error(message: String?): Resource<T> {
            return Resource<T>(null, Status.ERROR, message)
        }

        fun <T> success(data: T?, message: String?): Resource<T> {
            return Resource<T>(data, Status.SUCCESS,message)
        }

    }

    enum class Status {
        LOADING, ERROR, SUCCESS
    }

}