package ir.developer.goalorpooch_compose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ir.developer.goalorpooch_compose.model.CardModelTeamOne
import ir.developer.goalorpooch_compose.model.CardModelTeamTwo
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM cardTeamOne")
    fun getCardsTeamOne(): Flow<List<CardModelTeamOne>>

    @Query("SELECT * FROM cardTeamTwo")
    fun getCardsTeamTwo(): Flow<List<CardModelTeamTwo>>

    @Query("SELECT * FROM cardTeamOne WHERE isSelect=:isSelect")
    fun getCardSelectedTeemOne(isSelect :Boolean): Flow<List<CardModelTeamOne>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCardTeamOne(cardModelTeamOne: CardModelTeamOne)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCardTeamTwo(cardModelTeamTwo: CardModelTeamTwo)

    @Update
    suspend fun updateCardTeamOne(cardModelTeamOne: CardModelTeamOne)

    @Update
    suspend fun updateCardTeamTwo(cardModelTeamTwo: CardModelTeamTwo)
}