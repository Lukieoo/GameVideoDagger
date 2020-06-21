package com.anioncode.gamevideodagger.model.tmpmodel

import com.anioncode.gamevideodagger.model.tmpmodel.Address
import com.anioncode.gamevideodagger.model.tmpmodel.Company

data class tmpUsersItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)