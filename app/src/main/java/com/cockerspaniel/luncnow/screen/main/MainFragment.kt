package com.cockerspaniel.luncnow.screen.main

import android.os.Bundle
import android.view.View
import com.cockerspaniel.luncnow.R
import com.cockerspaniel.luncnow.databinding.FragmentMainBinding
import com.cockerspaniel.luncnow.screen.base.BaseFragment
import com.cockerspaniel.luncnow.screen.main.adapter.BurnLuncTypeFactory
import com.cockerspaniel.luncnow.screen.main.model.BurnLuncItem
import com.cockerspaniel.luncnow.util.ViewState
import com.cockerspaniel.luncnow.util.listadapter.ListItemAction
import com.cockerspaniel.luncnow.util.listadapter.ListItemAdapter
import com.cockerspaniel.luncnow.util.observe
import com.cockerspaniel.luncnow.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val adapter = ListItemAdapter<BurnLuncItem, ListItemAction>(
        typeFactory = BurnLuncTypeFactory(),
        primaryAction = ::onItemClick
    )
    override val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listOrder.adapter = adapter
    }

    override fun onDestroyView() {
        binding.listOrder.adapter = null
        super.onDestroyView()
    }

    override fun observeViewState() {
        observe(viewModel.viewState) { state ->
            when (state) {
                is ViewState.Success -> adapter.setItems(state.value)
                else -> adapter.setItems(emptyList())
            }
        }
    }

    private fun onItemClick(data: BurnLuncItem) {
        navigateToDetails(data.ranking)
    }

    private fun navigateToDetails(id: Int) {
        //safeNavigate(
        //    R.id.to_fragment_order_details,
        //    bundleOf( RANKING to ranking )
        //)
    }

    companion object {
        const val RANKING = "ranking"
    }
}
