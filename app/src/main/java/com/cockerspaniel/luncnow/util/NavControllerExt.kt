package com.cockerspaniel.luncnow.util

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import com.cockerspaniel.luncnow.R

/**
 * Navigation method that handles backstack clearing and default animations.
 * Note: do not call directly from Fragments. Use BaseFragment.safeNavigate instead.
 */
fun NavController.safeNavigate(
    navActionId: Int,
    bundle: Bundle? = null,
    extras: FragmentNavigator.Extras? = null,
    animationBuilder: NavOptions.Builder = getSlideAnimNavOptionsBuilder()
) {
    currentDestination
        ?.getAction(navActionId)
        ?.navOptions
        ?.let {
            animationBuilder.setPopUpTo(it.popUpTo, it.isPopUpToInclusive)
            animationBuilder.setLaunchSingleTop(it.shouldLaunchSingleTop())
        }
        .ifNull { error("No action found with id $navActionId") }

    navigate(navActionId, bundle, animationBuilder.build(), extras)
}

fun getSlideAnimNavOptionsBuilder(): NavOptions.Builder {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.faded_slide_in_right)
        .setExitAnim(R.anim.faded_slide_out_left)
        .setPopEnterAnim(R.anim.faded_slide_in_left)
        .setPopExitAnim(R.anim.faded_slide_out_right)
}
