package com.cockerspaniel.luncnow.util.listadapter

import androidx.recyclerview.widget.RecyclerView

/**
 * Base class for list item data shown in [ListItemAdapter].
 */
interface ListItemModel {

    /**
     * Returns unique id for the item. This id will be used in [ListItemAdapter] for change
     * diff calculations and stable item position resolving.
     */
    fun id(): Long = RecyclerView.NO_ID

    /**
     * Returns type of the item. The method implementation must use the provided
     * [ListItemTypeFactory] for resolving the type to ensure a match when requested
     * by [ListItemAdapter].
     */
    fun type(typeFactory: ListItemTypeFactory): Int
}
