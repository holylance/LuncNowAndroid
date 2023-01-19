package com.cockerspaniel.luncnow.util.listadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * View holder for binding ListItemModel data.
 */
abstract class BindingViewHolder<T : ListItemModel, A : ListItemAction>(view: View) :
    RecyclerView.ViewHolder(view) {

    /**
     * Data for the ViewHolder to show.
     */
    open fun bind(item: T) = Unit

    /**
     * Data for the ViewHolder to show and an primary action the ViewHolder
     * can trigger on click on the root element.
     */
    open fun bind(item: T, primaryAction: (T) -> Unit) {
        bind(item)
    }

    /**
     * Data for the ViewHolder to show, an primary action the ViewHolder
     * can trigger on click on the root element and another one for other interactive elements.
     */
    open fun bind(item: T, primaryAction: (T) -> Unit, secondaryAction: (T, A) -> Unit) {
        bind(item, primaryAction)
    }
}
