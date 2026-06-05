package com.elon.timebox.di

import android.content.Context
import androidx.room.Room
import com.elon.timebox.data.AppDatabase
import com.elon.timebox.data.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt DI Module
 * Hilt = Dagger 기반 안드로이드 의존성 주입 라이브러리
 * @Provides = 객체 생성 방법을 Hilt에 알려주는 어노테이션
 * @Singleton = 앱 전체에서 하나의 인스턴스만 사용
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "timebox_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBrainDumpDao(db: AppDatabase): BrainDumpDao = db.brainDumpDao()

    @Provides
    fun provideMitTaskDao(db: AppDatabase): MitTaskDao = db.mitTaskDao()

    @Provides
    fun provideTimeBlockDao(db: AppDatabase): TimeBlockDao = db.timeBlockDao()

    @Provides
    fun provideEveningReviewDao(db: AppDatabase): EveningReviewDao = db.eveningReviewDao()
}
