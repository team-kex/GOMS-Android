package com.goms.presentation.di.module

import com.goms.domain.repository.AuthRepository
import com.goms.domain.repository.LateRepository
import com.goms.domain.repository.OutingRepository
import com.goms.domain.repository.ProfileRepository
import com.goms.domain.usecase.auth.CheckLoginUseCase
import com.goms.domain.usecase.auth.SetTokenUseCase
import com.goms.domain.usecase.auth.SignInUseCase
import com.goms.domain.usecase.late.LateUseCase
import com.goms.domain.usecase.outing.OutingCountUseCase
import com.goms.domain.usecase.outing.OutingListUseCase
import com.goms.domain.usecase.outing.OutingUseCase
import com.goms.domain.usecase.profile.ProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSignInUseCase(authRepository: AuthRepository): SignInUseCase {
        return SignInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideCheckLoginUseCase(authRepository: AuthRepository): CheckLoginUseCase {
        return CheckLoginUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSaveTokenUseCase(authRepository: AuthRepository): SetTokenUseCase {
        return SetTokenUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideProfileUseCase(profileRepository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideOutingUseCase(outingRepository: OutingRepository): OutingUseCase {
        return OutingUseCase(outingRepository)
    }

    @Provides
    @Singleton
    fun provideOutingListUseCase(outingRepository: OutingRepository): OutingListUseCase {
        return OutingListUseCase(outingRepository)
    }

    @Provides
    @Singleton
    fun provideOutingCountUseCase(outingRepository: OutingRepository): OutingCountUseCase {
        return OutingCountUseCase(outingRepository)
    }

    @Provides
    @Singleton
    fun provideLateUseCase(lateRepository: LateRepository): LateUseCase {
        return LateUseCase(lateRepository)
    }
}