package com.alisoltech.innovc.data.network


import android.util.Log
import com.alisoltech.innovc.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.*
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

class ApiClientManager {
    private val TAG = "ApiClientManager"
    private var mRetrofit: Retrofit? = null
    private val httpBuilder = OkHttpClient.Builder()
    private val interceptorBuilder = HttpCommonInterceptor.Builder()

    companion object {
        private const val DEFAULT_TIME_OUT = 30
        private const val DEFAULT_READ_TIME_OUT = 30
        private const val HOST = BuildConfig.SCHEME +BuildConfig.API
    }

    init {
        httpBuilder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
        httpBuilder.writeTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
        httpBuilder.readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
        createRetrofit()
    }

    private fun createRetrofit() {
        httpBuilder.addInterceptor(interceptorBuilder.build())
        if (BuildConfig.DEBUG) {
            httpBuilder.addInterceptor(LogInterceptor())
        }
        mRetrofit = Retrofit.Builder()
            .client(httpBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(HOST)
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return mRetrofit!!.create(service)
    }

    inner class LogInterceptor : Interceptor {

        @ExperimentalStdlibApi
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {

            val UTF8 = Charset.forName("UTF-8")
            val gson = GsonBuilder().disableHtmlEscaping().create()

            val request = chain.request()
            val startTime = System.currentTimeMillis()
            val response = chain.proceed(chain.request())
            val endTime = System.currentTimeMillis()
            val duration = endTime - startTime
            val mediaType = response.body()!!.contentType()
            val content = response.body()!!.string()
            Log.d(TAG, request.headers().toString())
            Log.d(TAG, "\n")
            Log.d(TAG, "----------Start----------------")
            Log.d(TAG, "| $request")
            val method = request.method()
            if ("POST" == method) {
                val sb = StringBuilder()
                if (request.body() is FormBody) {
                    val body = request.body() as FormBody
                    for (i in 0 until body.size()) {
                        sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",")
                    }
                    sb.delete(sb.length - 1, sb.length)
                    Log.d(TAG, "| RequestParams:{$sb}")
                } else if (request.body() is RequestBody) {
                    val body = request.body() as RequestBody

                    val buffer = Buffer()
                    body.writeTo(buffer)

                    var charset: Charset? = UTF8
                    val contentType = body.contentType()
                    contentType?.let {
                        charset = contentType.charset(UTF8)
                    }
                    val requestBody = buffer.readString(charset!!)
                    if (requestBody != "") {
                        for (i in requestBody.encodeToByteArray().indices) {

                            sb.append(requestBody.encodeToByteArray()[i])
                        }
                        sb.delete(sb.length - 1, sb.length)
                    }
                    Log.d(TAG, "| RequestParams:{$sb}")

                }
            }
            Log.d(TAG, "| Response:$content")
            Log.d(TAG, "----------End:" + duration + "毫秒----------")
            return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build()
        }
    }
}