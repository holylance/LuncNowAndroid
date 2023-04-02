package com.cockerspaniel.network.mockdata

import com.cockerspaniel.network.internal.model.StakingResponse

internal object StakingResponseMock {

    fun generate(): StakingResponse {
        return StakingResponse(
            delegationTotal = "1.11"
        )
    }
}
