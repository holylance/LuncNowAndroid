package com.cockerspaniel.luncnow.screen.staking

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.cockerspaniel.luncnow.R
import com.cockerspaniel.luncnow.databinding.FragmentStakingBinding
import com.cockerspaniel.luncnow.screen.base.BaseFragment
import com.cockerspaniel.luncnow.screen.staking.adapter.StakingTypeFactory
import com.cockerspaniel.luncnow.util.listadapter.ListItemAction
import com.cockerspaniel.luncnow.util.listadapter.ListItemAdapter
import com.cockerspaniel.luncnow.util.listadapter.ListItemModel
import com.cockerspaniel.luncnow.util.observe
import com.cockerspaniel.luncnow.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StakingFragment : BaseFragment(R.layout.fragment_staking) {

    private val binding by viewBinding(FragmentStakingBinding::bind)
    private val adapter = ListItemAdapter<ListItemModel, ListItemAction>(
        typeFactory = StakingTypeFactory(),
        hasStableIds = true
    )
    override val viewModel by viewModel<StakingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loading.isVisible = true
        binding.listStaking.adapter = adapter
        viewModel.fetchStaking()
    }

    override fun onDestroyView() {
        binding.listStaking.adapter = null
        super.onDestroyView()
    }

    override fun observeViewState() {
        observe(viewModel.viewState) { state ->
            adapter.setItems(state)
            binding.loading.isVisible = false
        }

        observe(viewModel.errorEvent) {
            binding.loading.isVisible = false
            Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show()
        }
    }
}
