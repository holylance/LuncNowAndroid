package com.cockerspaniel.luncnow.screen.main.adapter

import android.view.ViewGroup
import com.cockerspaniel.luncnow.R
import com.cockerspaniel.luncnow.databinding.BurnLuncItemBinding
import com.cockerspaniel.luncnow.screen.main.model.BurnLuncItem
import com.cockerspaniel.luncnow.screen.main.viewholder.BurnLuncItemViewHolder
import com.cockerspaniel.luncnow.util.listadapter.BindingViewHolder
import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.listadapter.ListItemTypeFactory

class BurnLuncTypeFactory : ListItemTypeFactory {

    override fun type(item: ListItemModel): Int {
        return when (item) {
            is BurnLuncItem -> R.layout.burn_lunc_item
            else -> error("Invalid type")
        }
    }

    override fun createViewHolder(parent: ViewGroup, type: Int): BindingViewHolder<*, *> {
        return when (type) {
            R.layout.burn_lunc_item -> BurnLuncItemViewHolder(
                createBinding(BurnLuncItemBinding::inflate, parent)
            )
            else -> error("Invalid layout")
        }
    }
}
