package com.cockerspaniel.luncnow.screen.main.model

import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory

data class BurnLuncItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val icon: String
) : ListItemModel {
    override fun type(typeFactory: ListItemTypeFactory) = typeFactory.type(this)
}
