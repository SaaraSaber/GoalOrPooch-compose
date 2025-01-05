package ir.developer.goalorpooch_compose.database.repository

import dagger.hilt.android.scopes.ViewModelScoped
import ir.developer.goalorpooch_compose.database.CardDao
import ir.developer.goalorpooch_compose.model.CardModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CardRepository @Inject constructor(private val cardDao: CardDao) {

    val getCards: Flow<List<CardModel>> = cardDao.getCards()

    suspend fun addCard(cardModel: CardModel) =
        cardDao.addCard(cardModel)

    suspend fun updateCard(cardModel: CardModel) =
        cardDao.updateCard(cardModel)
}