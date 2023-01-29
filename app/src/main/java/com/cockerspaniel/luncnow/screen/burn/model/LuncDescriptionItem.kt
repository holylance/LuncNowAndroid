package com.cockerspaniel.luncnow.screen.burn.model

import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory

data class LuncDescriptionItem(
    val id: Long = 0L,
) : ListItemModel {
    override fun type(typeFactory: ListItemTypeFactory) = typeFactory.type(this)
}
