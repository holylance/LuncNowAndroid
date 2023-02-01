package com.cockerspaniel.luncnow.screen.staking.model

import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory
import java.math.BigDecimal

data class StakingItem(
    val ranking: Int,
    val country: String,
    val name: String,
    val amountNum: BigDecimal,
    val amount: String
) : ListItemModel {

    override fun id(): Long = ranking.toLong()

    override fun type(typeFactory: ListItemTypeFactory) = typeFactory.type(this)
}
