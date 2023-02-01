package com.cockerspaniel.luncnow.screen.staking.adapter

import android.view.ViewGroup
import com.cockerspaniel.luncnow.R
import com.cockerspaniel.luncnow.databinding.StakingItemBinding
import com.cockerspaniel.luncnow.screen.staking.model.StakingItem
import com.cockerspaniel.luncnow.screen.staking.viewholder.StakingItemViewHolder
import com.cockerspaniel.luncnow.util.listadapter.BindingViewHolder
import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory

class StakingTypeFactory : ListItemTypeFactory {

    override fun type(item: ListItemModel): Int {
        return when (item) {
            is StakingItem -> R.layout.staking_item
            else -> error("Invalid type")
        }
    }

    override fun createViewHolder(parent: ViewGroup, type: Int): BindingViewHolder<*, *> {
        return when (type) {
            R.layout.staking_item -> StakingItemViewHolder(
                createBinding(StakingItemBinding::inflate, parent)
            )
            else -> error("Invalid layout")
        }
    }
}
