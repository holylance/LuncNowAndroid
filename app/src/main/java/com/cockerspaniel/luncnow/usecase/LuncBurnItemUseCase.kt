package com.cockerspaniel.luncnow.usecase

import com.cockerspaniel.luncnow.screen.base.BaseValues.NO_NAME
import com.cockerspaniel.luncnow.screen.burn.model.LuncBurnItem
import com.cockerspaniel.luncnow.util.storage.PreferenceStoreImpl
import com.cockerspaniel.network.base.util.toZonedDateTimeWithFormatter
import java.math.BigDecimal
import java.time.ZonedDateTime

class LuncBurnItemUseCase(private val preferenceStore: PreferenceStoreImpl) {

    fun getItemList(newList: List<LuncBurnItem>): List<LuncBurnItem> {
        val old = preferenceStore.retrieveObject(
            key = LIST_LUNC_ITEM,
            type = ListLuncBurnItem::class.java
        )
        val updatedList = old?.let {
            val result = old.list + newList
            val weekAgo = ZonedDateTime.now().minusWeeks(WEEK)
            result.filter { it.time.toZonedDateTimeWithFormatter() > weekAgo }
        } ?: run { newList }

        var ranking = 1
        val finalList = updatedList.asSequence().sortedByDescending { it.amountNum }
            .distinctBy { it.id }
            .distinctBy { it.time }
            .take(LIMIT_TOP)
            .map { it.copy(ranking = ranking++) }.toList()

        preferenceStore.persistObject(
            LIST_LUNC_ITEM,
            ListLuncBurnItem(
                finalList.filter {
                    it.name != NO_NAME || it.amountNum >= BigDecimal(LIMIT_AMOUNT)
                }
            )
        )

        return finalList
    }

    private data class ListLuncBurnItem(val list: List<LuncBurnItem> = listOf())

    companion object {
        private const val LIST_LUNC_ITEM = "list_lunc_item"
        private const val LIMIT_TOP = 100
        private const val WEEK = 1L
        private const val LIMIT_AMOUNT = 1000000
    }
}
