package com.cockerspaniel.luncnow.screen.main.viewholder

import com.cockerspaniel.luncnow.databinding.BurnLuncItemBinding
import com.cockerspaniel.luncnow.screen.main.model.BurnLuncItem
import com.cockerspaniel.luncnow.util.listadapter.BindingViewHolder
import com.cockerspaniel.luncnow.util.listadapter.ListItemAction

class BurnLuncItemViewHolder(
    private val binding: BurnLuncItemBinding
) : BindingViewHolder<BurnLuncItem, ListItemAction>(binding.root) {

    override fun bind(item: BurnLuncItem, primaryAction: (BurnLuncItem) -> Unit) {
        with(binding) {
            root.setOnClickListener { primaryAction.invoke(item) }

            ranking.text = item.ranking.toString()
            name.text = item.name
            amount.text = item.amount
        }
    }
}
