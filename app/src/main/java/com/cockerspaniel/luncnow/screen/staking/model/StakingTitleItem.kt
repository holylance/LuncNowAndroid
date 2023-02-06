package com.cockerspaniel.luncnow.screen.staking.model

import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory

object StakingTitleItem : ListItemModel {

    override fun id(): Long = 1L

    override fun type(typeFactory: ListItemTypeFactory) = typeFactory.type(this)
}
