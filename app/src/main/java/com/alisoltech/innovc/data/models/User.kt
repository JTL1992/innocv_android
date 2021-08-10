package com.alisoltech.innovc.data.models

data class User(
    val id: Int ,
    val name: String?,
    val birthdate: String?
) {
    val nameData: String
        get() = name ?: ""
    val birthdayData: String
        get() = birthdate ?: ""
}
