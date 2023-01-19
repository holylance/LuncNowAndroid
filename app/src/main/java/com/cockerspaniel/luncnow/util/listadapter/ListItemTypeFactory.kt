package com.cockerspaniel.luncnow.util.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * Type factory for resolving RecyclerView item types for [ListItemAdapter].
 */
interface ListItemTypeFactory {

    fun type(item: ListItemModel): Int

    fun createViewHolder(parent: ViewGroup, type: Int): BindingViewHolder<*, *>

    fun <T : ViewBinding> createBinding(
        creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T,
        parent: ViewGroup
    ): T {
        return creator(LayoutInflater.from(parent.context), parent, false)
    }
}
