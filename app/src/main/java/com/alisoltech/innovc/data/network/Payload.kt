package com.alisoltech.innovc.data.network

import io.reactivex.functions.Function

class Payload<T> : Function<BaseResponse<T>, T> {

    override fun apply(tBaseResponse: BaseResponse<T>): T {
        return tBaseResponse.data
    }
}