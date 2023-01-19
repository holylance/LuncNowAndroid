package com.cockerspaniel.luncnow.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * ViewBinding for Fragments. Stores the binding in View's tag, which simplifies lifecycle
 * handling: when Fragment destroys its View, the binding is removed too.
 */
inline fun <reified T : ViewBinding> Fragment.viewBinding(
    crossinline bind: (View) -> T
): ReadOnlyProperty<Fragment, T> = object : ReadOnlyProperty<Fragment, T> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = (requireView().getTag(property.name.hashCode()) as? T)
        if (binding != null) return binding

        return bind(requireView()).also {
            requireView().setTag(property.name.hashCode(), it)
        }
    }
}
