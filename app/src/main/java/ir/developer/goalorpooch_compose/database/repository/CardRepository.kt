package ir.developer.goalorpooch_compose.database.repository

import dagger.hilt.android.scopes.ViewModelScoped
import ir.developer.goalorpooch_compose.database.CardDao
import ir.developer.goalorpooch_compose.model.CardModelTeamOne
import ir.developer.goalorpooch_compose.model.CardModelTeamTwo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CardRepository @Inject constructor(private val cardDao: CardDao) {

    val getCardsTeamOne: Flow<List<CardModelTeamOne>> = cardDao.getCardsTeamOne()

    val getCardsTeamTwo: Flow<List<CardModelTeamTwo>> = cardDao.getCardsTeamTwo()

    fun getCardsSelectTeamOne(isSelect: Boolean) = cardDao.getCardSelectedTeemOne(isSelect)

    suspend fun addCardTeamOne(cardModelTeamOne: CardModelTeamOne) =
        cardDao.addCardTeamOne(cardModelTeamOne)

    suspend fun addCardTeamTwo(cardModelTeamTwo: CardModelTeamTwo) =
        cardDao.addCardTeamTwo(cardModelTeamTwo)

    suspend fun updateCardTeamOne(cardModelTeamOne: CardModelTeamOne) =
        cardDao.updateCardTeamOne(cardModelTeamOne)

    suspend fun updateCardTeamTwo(cardModelTeamTwo: CardModelTeamTwo) =
        cardDao.updateCardTeamTwo(cardModelTeamTwo)
}