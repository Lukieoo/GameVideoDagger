package com.anioncode.gamevideodagger.model.dataState

data class DataWithStates<T>(
    val data: T? = null,
    val states: Throwable? = null
)