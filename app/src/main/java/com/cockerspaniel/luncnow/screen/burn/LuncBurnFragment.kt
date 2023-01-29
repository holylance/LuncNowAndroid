package com.cockerspaniel.luncnow.screen.burn

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cockerspaniel.luncnow.R
import com.cockerspaniel.luncnow.databinding.FragmentLuncBurnBinding
import com.cockerspaniel.luncnow.screen.base.BaseFragment
import com.cockerspaniel.luncnow.screen.burn.adapter.LuncBurnTypeFactory
import com.cockerspaniel.luncnow.screen.burn.model.LuncBurnItem
import com.cockerspaniel.luncnow.util.listadapter.ListItemAction
import com.cockerspaniel.luncnow.util.listadapter.ListItemAdapter
import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.observe
import com.cockerspaniel.luncnow.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LuncBurnFragment : BaseFragment(R.layout.fragment_lunc_burn) {

    private val binding by viewBinding(FragmentLuncBurnBinding::bind)
    private val adapter = ListItemAdapter<ListItemModel, ListItemAction>(
        typeFactory = LuncBurnTypeFactory(),
        primaryAction = ::onItemClick
    )
    override val viewModel by viewModel<LuncBurnViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listItems.adapter = adapter
        viewModel.fetchTransactions()
    }

    override fun onDestroyView() {
        binding.listItems.adapter = null
        super.onDestroyView()
    }

    override fun observeViewState() {
        observe(viewModel.viewState) { state ->
            adapter.setItems(state)
        }

        observe(viewModel.errorEvent) {
            Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onItemClick(data: ListItemModel) {
        when(data) {
            is LuncBurnItem -> navigateToDetails(data.ranking)
            else -> Unit
        }
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
