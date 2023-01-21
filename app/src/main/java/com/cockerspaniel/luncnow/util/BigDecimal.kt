package com.cockerspaniel.luncnow.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun BigDecimal.formatNoSymbol(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault()) as DecimalFormat
    val symbol = formatter.decimalFormatSymbols
    symbol.currencySymbol = ""
    formatter.decimalFormatSymbols = symbol
    return formatter.format(this)
}
