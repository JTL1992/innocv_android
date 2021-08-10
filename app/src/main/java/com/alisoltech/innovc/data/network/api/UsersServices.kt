package com.alisoltech.innovc.data.network.api

import com.alisoltech.innovc.data.models.User
import com.alisoltech.innovc.data.network.BaseResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface UsersServices {

    interface GetUsers {
        @GET("/api/User")
        fun getUsers(): Observable<List<User>>
    }

    interface PostUser {
        @Headers("Content-Type: application/json")
        @POST("/api/User")
        fun postUser(@Body user: User): Observable<ResponseBody>
    }

    interface PutUser {
        @Headers("Content-Type: application/json")
        @POST("/api/User")
        fun putUser(@Body user: User): Observable<ResponseBody>
    }

    interface DeleteUser {
        @DELETE("/api/User/{id}")
        fun deleteUser(@Path("id") id: Int) : Observable<ResponseBody>
    }

    interface GetUser{
        @GET("/api/User/{id}")
        fun getUser(@Path("id") id: Int) : Observable<User>
    }

}