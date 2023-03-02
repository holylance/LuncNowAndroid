package com.cockerspaniel.luncnow.screen.burn.model

import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory
import java.math.BigDecimal

data class LuncBurnItem(
    val id: Long,
    val name: String,
    val amountNum: BigDecimal,
    val amount: String,
    val time: String,
    val ranking: Int = 0
) : ListItemModel {

    override fun id(): Long = id

    override fun type(typeFactory: ListItemTypeFactory) = typeFactory.type(this)
}
