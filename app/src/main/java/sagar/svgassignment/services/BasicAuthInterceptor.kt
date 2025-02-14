package sagar.svgassignment.services

import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("X-Requested-With", "XMLHttpRequest")
        return chain.proceed(builder.build())
    }
}