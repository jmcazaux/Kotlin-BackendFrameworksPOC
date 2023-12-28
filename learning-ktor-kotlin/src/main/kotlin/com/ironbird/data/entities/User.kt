package com.ironbird.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(val name: String, val age: Int) {
    override fun toString(): String {
        return "User(name='$name', age=$age)"
    }
}
