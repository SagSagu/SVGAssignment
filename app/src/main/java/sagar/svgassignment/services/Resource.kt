package sagar.svgassignment.services

data class Resource<out T>(val status: Status, val data: T?, val errors: Body?, val code: Int?) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(code: Int, data: T? = null, errors: Body? = null): Resource<T> {
            return Resource(Status.ERROR, data, errors, code)
        }
    }
}