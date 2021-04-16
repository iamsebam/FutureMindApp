package com.sebastianmatyjaszczyk.mainfeature.di

import com.sebastianmatyjaszczyk.commonlib.StringDateFormatter
import com.sebastianmatyjaszczyk.mainfeature.repository.DetailUrlRepository
import com.sebastianmatyjaszczyk.resourceslib.ListFeatureDateFormats
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ListFeatureModule {

    @Singleton
    @Provides
    fun provideStringDateFormatter(): StringDateFormatter =
        StringDateFormatter(
            DateTimeFormatter.ofPattern(ListFeatureDateFormats.SOURCE_DATE_FORMAT),
            DateTimeFormatter.ofPattern(ListFeatureDateFormats.TARGET_DATE_FORMAT)
        )

    @Singleton
    @Provides
    fun provideDetailUrlRepository(): DetailUrlRepository = DetailUrlRepository()
}
