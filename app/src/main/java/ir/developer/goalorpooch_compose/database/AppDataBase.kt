package ir.developer.goalorpooch_compose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.developer.goalorpooch_compose.model.CardModel
import ir.developer.goalorpooch_compose.model.SettingModel

@Database(
    entities = [CardModel::class, SettingModel::class],
    version = 6,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun settingDao(): SettingDao
}