package com.sebastianmatyjaszczyk.mainfeature.di

import com.sebastianmatyjaszczyk.commonlib.stringDateFormatter.SourceStringParser
import com.sebastianmatyjaszczyk.commonlib.stringDateFormatter.StringDateFormatter
import com.sebastianmatyjaszczyk.commonlib.stringDateFormatter.TargetStringFormatter
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
    fun provideTargetStringFormatter(): TargetStringFormatter =
        TargetStringFormatter(
            DateTimeFormatter.ofPattern(ListFeatureDateFormats.TARGET_DATE_FORMAT)
        )

    @Singleton
    @Provides
    fun provideSourceStringParser(): SourceStringParser =
        SourceStringParser(
            DateTimeFormatter.ofPattern(ListFeatureDateFormats.SOURCE_DATE_FORMAT)
        )

    @Singleton
    @Provides
    fun provideStringDateFormatter(
        sourceStringParser: SourceStringParser,
        targetStringFormatter: TargetStringFormatter
    ): StringDateFormatter = StringDateFormatter(
        sourceStringParser,
        targetStringFormatter
    )

    @Singleton
    @Provides
    fun provideDetailUrlRepository(): DetailUrlRepository = DetailUrlRepository()
}
