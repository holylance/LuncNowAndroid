package com.cockerspaniel.luncnow.screen.main.viewholder

import androidx.core.net.toUri
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

            name.text = item.name
            description.text = item.description
            price.text = item.price
            icon.setImageURI(item.icon.toUri())
        }
    }
}
