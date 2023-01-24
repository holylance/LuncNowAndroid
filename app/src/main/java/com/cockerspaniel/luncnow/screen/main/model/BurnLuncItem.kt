package com.cockerspaniel.luncnow.screen.main.model

import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory
import java.math.BigDecimal

data class BurnLuncItem(
    val name: String,
    val amountNum: BigDecimal,
    val amount: String,
    val ranking: Int = 0
) : ListItemModel {
    override fun type(typeFactory: ListItemTypeFactory) = typeFactory.type(this)
}
