package com.example.nouste.utils

fun <T> List<T>.getSubList(endIndex: Int): List<T> {
    return if (this.count() > endIndex) {
        this.subList(0, endIndex)
    } else {
        this
    }
}