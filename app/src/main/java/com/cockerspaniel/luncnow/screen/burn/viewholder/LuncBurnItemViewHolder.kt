package com.cockerspaniel.luncnow.screen.burn.viewholder

import com.cockerspaniel.luncnow.databinding.LuncBurnItemBinding
import com.cockerspaniel.luncnow.databinding.LuncDescriptionItemBinding
import com.cockerspaniel.luncnow.screen.burn.model.LuncBurnItem
import com.cockerspaniel.luncnow.screen.burn.model.LuncDescriptionItem
import com.cockerspaniel.luncnow.util.listadapter.BindingViewHolder
import com.cockerspaniel.luncnow.util.listadapter.ListItemAction
import com.cockerspaniel.network.base.util.toParsedString

class LuncBurnItemViewHolder(
    private val binding: LuncBurnItemBinding
) : BindingViewHolder<LuncBurnItem, ListItemAction>(binding.root) {

    override fun bind(item: LuncBurnItem, primaryAction: (LuncBurnItem) -> Unit) {
        with(binding) {
            root.setOnClickListener { primaryAction.invoke(item) }

            ranking.text = item.ranking.toString()
            name.text = item.name
            amount.text = item.amount
            time.text = item.time.toParsedString()
        }
    }
}

class LuncDescriptionItemViewHolder(
    private val binding: LuncDescriptionItemBinding
) : BindingViewHolder<LuncDescriptionItem, ListItemAction>(binding.root) {

    override fun bind(item: LuncDescriptionItem) { }
}