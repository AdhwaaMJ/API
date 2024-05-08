package com.project.api.domain.Profile

import com.project.api.data.repository.ProfileRepository
import javax.inject.Inject

class GetSessionIdUseCase @Inject constructor(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(requestToken: String) =
        profileRepository.getSessionId(requestToken)
}