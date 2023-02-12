package com.cockerspaniel.luncnow.screen.burn.model

import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory
import java.math.BigDecimal
import java.time.ZonedDateTime

data class LuncBurnItem(
    val name: String,
    val amountNum: BigDecimal,
    val amount: String,
    val time: ZonedDateTime,
    val ranking: Int = 0
) : ListItemModel {

    override fun type(typeFactory: ListItemTypeFactory) = typeFactory.type(this)
}
