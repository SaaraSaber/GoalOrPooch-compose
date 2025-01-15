package ir.developer.goalorpooch_compose.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "data-base-game"
        ).build()

    @Singleton
    @Provides
    fun provideDaoCard(database: AppDataBase) = database.cardDao()

    @Singleton
    @Provides
    fun provideDaoSetting(database: AppDataBase) = database.settingDao()
}