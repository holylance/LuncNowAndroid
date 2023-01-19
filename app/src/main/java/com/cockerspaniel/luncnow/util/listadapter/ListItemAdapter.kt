package com.cockerspaniel.luncnow.util.listadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Generic adapter class that uses [ListItemTypeFactory] for resolving links between
 * list model data and view holders.
 */
open class ListItemAdapter<T : ListItemModel, A : ListItemAction>(
    private val typeFactory: ListItemTypeFactory,
    private val primaryAction: ((T) -> Unit)? = null,
    private val secondaryAction: ((T, A) -> Unit)? = null,
    initialItems: List<T> = emptyList(),
    hasStableIds: Boolean = false
) : RecyclerView.Adapter<BindingViewHolder<T, A>>() {

    private val items: MutableList<T> = initialItems.toMutableList()

    init {
        setHasStableIds(hasStableIds)
    }

    open fun setItems(newItems: List<T>) {
        val diffResult = getDiff(items, newItems)

        items.clear()
        items.addAll(newItems)

        // If the adapter has stable ids, we can use those same ids for calculating list diffs.
        // The diff calculation and item stability resolving both depend on the items correctly
        // implementing the ID getter method of [ListItemModel].
        if (hasStableIds()) {
            diffResult.dispatchUpdatesTo(this)
        } else {
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun itemAt(index: Int): T {
        return items[index]
    }

    fun indexOf(matcher: (T) -> Boolean): Int? {
        return items.indexOfFirst { matcher(it) }
            .let { if (it >= 0) it else null }
    }

    override fun onBindViewHolder(holder: BindingViewHolder<T, A>, position: Int) {
        holder.bind(
            items[position],
            { item -> primaryAction?.invoke(item) },
            { item, action -> secondaryAction?.invoke(item, action) }
        )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<T, A> {
        return typeFactory.createViewHolder(parent, viewType) as BindingViewHolder<T, A>
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type(typeFactory)
    }

    override fun getItemId(position: Int): Long {
        return if (hasStableIds()) itemAt(position).id() else RecyclerView.NO_ID
    }

    /**
     * Method for calculating list diffs when updating. The calculation only works for items
     * that have implemented the unique id getter method of [ListItemModel].
     */
    private fun getDiff(
        oldItems: List<T>,
        newItems: List<T>
    ): DiffUtil.DiffResult {

        return DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize() = oldItems.size

            override fun getNewListSize() = newItems.size

            override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
                return oldItems[oldPosition].id() == newItems[newPosition].id()
            }

            override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
                return oldItems[oldPosition] == newItems[newPosition]
            }
        })
    }
}
