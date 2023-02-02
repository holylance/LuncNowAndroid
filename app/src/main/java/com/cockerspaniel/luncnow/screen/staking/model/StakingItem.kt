package com.cockerspaniel.luncnow.screen.staking.model

import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory
import java.math.BigDecimal

data class StakingItem(
    val address: String,
    val name: String,
    val ranking: Int,
    val country: String,
    val countryFull: String,
    val amountNum: BigDecimal,
    val amount: String
) : ListItemModel {

    override fun id(): Long = address.hashCode().toLong()

    override fun type(typeFactory: ListItemTypeFactory) = typeFactory.type(this)
}
