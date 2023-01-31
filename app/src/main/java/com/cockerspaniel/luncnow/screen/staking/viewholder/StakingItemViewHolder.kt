package com.cockerspaniel.luncnow.screen.staking.viewholder

import com.cockerspaniel.luncnow.databinding.StakingItemBinding
import com.cockerspaniel.luncnow.screen.staking.model.StakingItem
import com.cockerspaniel.luncnow.util.listadapter.BindingViewHolder
import com.cockerspaniel.luncnow.util.listadapter.ListItemAction

class StakingItemViewHolder(
    private val binding: StakingItemBinding
) : BindingViewHolder<StakingItem, ListItemAction>(binding.root) {

    override fun bind(item: StakingItem) {
        with(binding) {

            ranking.text = item.ranking.toString()
            country.text = item.country
            name.text = item.name
            amount.text = item.amount
        }
    }
}
