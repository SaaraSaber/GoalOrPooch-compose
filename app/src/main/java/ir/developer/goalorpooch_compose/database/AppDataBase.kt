package ir.developer.goalorpooch_compose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.developer.goalorpooch_compose.model.CardModelTeamOne
import ir.developer.goalorpooch_compose.model.CardModelTeamTwo

@Database(
    entities = [CardModelTeamOne::class, CardModelTeamTwo::class],
    version = 3,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}