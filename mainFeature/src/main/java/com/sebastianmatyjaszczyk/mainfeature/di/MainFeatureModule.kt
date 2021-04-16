package com.sebastianmatyjaszczyk.mainfeature.di

import com.sebastianmatyjaszczyk.commonlib.StringDateFormatter
import com.sebastianmatyjaszczyk.mainfeature.presentation.misc.MainFeatureDateFormats
import com.sebastianmatyjaszczyk.mainfeature.repository.DetailUrlRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainFeatureModule {

    @Singleton
    @Provides
    fun provideStringDateFormatter(): StringDateFormatter =
        StringDateFormatter(
            DateTimeFormatter.ofPattern(MainFeatureDateFormats.SOURCE_DATE_FORMAT),
            DateTimeFormatter.ofPattern(MainFeatureDateFormats.TARGET_DATE_FORMAT)
        )

    @Singleton
    @Provides
    fun provideDetailUrlRepository(): DetailUrlRepository = DetailUrlRepository()
}
