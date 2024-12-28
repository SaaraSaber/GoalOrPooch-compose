package ir.developer.goalorpooch_compose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.developer.goalorpooch_compose.model.CardModel

@Database(
    entities = [CardModel::class],
    version = 2,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}