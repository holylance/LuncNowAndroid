package com.cockerspaniel.luncnow.screen.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.cockerspaniel.luncnow.util.getSlideAnimNavOptionsBuilder
import com.cockerspaniel.luncnow.util.safeNavigate

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    constructor() : this(0)

    open val viewModel: BaseViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        observeViewState()
    }

    private fun initToolbar() {
        getFragmentToolbar()?.apply {
            // If no title has been set, suppress setting app name as title
            if (title.isNullOrEmpty()) title = ""

            // Required for toolbar actions such as PDF preview
            (activity as AppCompatActivity).setSupportActionBar(this)

            // Handle toolbar back button
            setNavigationOnClickListener {
                onToolbarNavigateBack()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel?.fragmentResumed()
    }

    /**
     * Safe navigation for Fragments. Fragments should always use this instead of
     * directly calling the NavController extension method.
     */
    protected fun safeNavigate(
        navActionId: Int,
        bundle: Bundle? = null,
        extras: FragmentNavigator.Extras? = null,
        animationBuilder: NavOptions.Builder = getSlideAnimNavOptionsBuilder()
    ) {
        if (isAdded) {
            findNavController()
                .safeNavigate(navActionId, bundle, extras, animationBuilder)
        }
    }

    /**
     * Method fragments should use to set view state and observe any future changes.
     */
    open fun observeViewState() = Unit

    open fun getFragmentToolbar(): Toolbar? = null

    protected fun onToolbarNavigateBack() {
        if (!findNavController().popBackStack()) requireActivity().onBackPressed()
    }
}
