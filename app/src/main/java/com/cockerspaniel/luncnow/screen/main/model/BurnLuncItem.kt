package com.cockerspaniel.luncnow.screen.main.model

import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory

data class BurnLuncItem(
    val ranking: Int,
    val name: String,
    val amount: String
) : ListItemModel {
    override fun type(typeFactory: ListItemTypeFactory) = typeFactory.type(this)
}
