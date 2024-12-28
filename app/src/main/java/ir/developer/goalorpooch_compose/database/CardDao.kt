package ir.developer.goalorpooch_compose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ir.developer.goalorpooch_compose.model.CardModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM cards")
    fun getCards(): Flow<List<CardModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCard(cardModel: CardModel)

    @Update
    suspend fun updateCard(cardModel: CardModel)
}