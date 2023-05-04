package com.goms.domain.usecase.late

import com.goms.domain.data.profile.response.ProfileResponseData
import com.goms.domain.repository.LateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LateUseCase @Inject constructor(
    private val lateRepository: LateRepository
) {
    suspend operator fun invoke(): Flow<List<ProfileResponseData>> {
        return lateRepository.getLateRank()
    }
}