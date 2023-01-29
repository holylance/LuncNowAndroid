package com.cockerspaniel.luncnow.screen.burn.adapter

import android.view.ViewGroup
import com.cockerspaniel.luncnow.R
import com.cockerspaniel.luncnow.databinding.LuncBurnItemBinding
import com.cockerspaniel.luncnow.databinding.LuncDescriptionItemBinding
import com.cockerspaniel.luncnow.screen.burn.model.LuncBurnItem
import com.cockerspaniel.luncnow.screen.burn.model.LuncDescriptionItem
import com.cockerspaniel.luncnow.screen.burn.viewholder.LuncBurnItemViewHolder
import com.cockerspaniel.luncnow.screen.burn.viewholder.LuncDescriptionItemViewHolder
import com.cockerspaniel.luncnow.util.listadapter.BindingViewHolder
import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory

class LuncBurnTypeFactory : ListItemTypeFactory {

    override fun type(item: ListItemModel): Int {
        return when (item) {
            is LuncBurnItem -> R.layout.lunc_burn_item
            is LuncDescriptionItem -> R.layout.lunc_description_item
            else -> error("Invalid type")
        }
    }

    override fun createViewHolder(parent: ViewGroup, type: Int): BindingViewHolder<*, *> {
        return when (type) {
            R.layout.lunc_burn_item -> LuncBurnItemViewHolder(
                createBinding(LuncBurnItemBinding::inflate, parent)
            )
            R.layout.lunc_description_item -> LuncDescriptionItemViewHolder(
                createBinding(LuncDescriptionItemBinding::inflate, parent)
            )
            else -> error("Invalid layout")
        }
    }
}
