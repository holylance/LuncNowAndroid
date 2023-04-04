package com.cockerspaniel.luncnow.screen.staking.viewholder

import com.bumptech.glide.Glide
import com.cockerspaniel.luncnow.databinding.StakingItemBinding
import com.cockerspaniel.luncnow.databinding.StakingTitleItemBinding
import com.cockerspaniel.luncnow.screen.base.BaseValues
import com.cockerspaniel.luncnow.screen.staking.model.StakingItem
import com.cockerspaniel.luncnow.screen.staking.model.StakingTitleItem
import com.cockerspaniel.luncnow.util.listadapter.BindingViewHolder
import com.cockerspaniel.luncnow.util.listadapter.ListItemAction

class StakingItemViewHolder(
    private val binding: StakingItemBinding
) : BindingViewHolder<StakingItem, ListItemAction>(binding.root) {

    override fun bind(item: StakingItem) {
        with(binding) {

            ranking.text = item.ranking.toString()
            name.text = item.name
            amount.text = item.amount

            Glide.with(root)
                .load(BaseValues.FLAG_URL + item.country + SVG)
                .into(country)
        }
    }

    companion object {
        private const val SVG = ".svg"
    }
}

class StakingTitleItemViewHolder(
    binding: StakingTitleItemBinding
) : BindingViewHolder<StakingTitleItem, ListItemAction>(binding.root) {

    override fun bind(item: StakingTitleItem) = Unit
}
