package com.example.clean_architecture_mvvm.ui_state

enum class Status { NONE, LOADING, SUCCESS, ERROR }

data class UiResult<out T>(
    val status: Status,
    val data: T?,
    val errorMsg: String?
) {

    companion object {

        fun <T> none(): UiResult<T> {
            return UiResult(Status.NONE, null, null)
        }

        fun <T> loading(): UiResult<T> {
            return UiResult(Status.LOADING, null, null)
        }

        fun <T> success(data: T?): UiResult<T> {
            return UiResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String? = "", data: T? = null): UiResult<T> {
            return UiResult(Status.ERROR, data, message)
        }
    }
}