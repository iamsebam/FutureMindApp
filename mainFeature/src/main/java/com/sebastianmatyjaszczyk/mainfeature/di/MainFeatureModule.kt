package com.sebastianmatyjaszczyk.mainfeature.di

import android.content.Context
import com.sebastianmatyjaszczyk.commonlib.StringDateFormatter
import com.sebastianmatyjaszczyk.mainfeature.presentation.misc.ErrorMessagesProvider
import com.sebastianmatyjaszczyk.mainfeature.presentation.misc.MainFeatureDateFormats
import com.sebastianmatyjaszczyk.mainfeature.repository.DetailUrlRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainFeatureModule {

    @Singleton
    @Provides
    fun provideDetailUrlRepository(): DetailUrlRepository = DetailUrlRepository()
}

@Module
@InstallIn(ViewModelComponent::class)
object MainFeatureViewModelsModule {

    @Provides
    fun provideStringDateFormatter(): StringDateFormatter =
        StringDateFormatter(
            DateTimeFormatter.ofPattern(MainFeatureDateFormats.SOURCE_DATE_FORMAT),
            DateTimeFormatter.ofPattern(MainFeatureDateFormats.TARGET_DATE_FORMAT)
        )

    @Provides
    fun provideErrorMessagesProvider(
        @ApplicationContext context: Context
    ): ErrorMessagesProvider =
        ErrorMessagesProvider(context)
}
