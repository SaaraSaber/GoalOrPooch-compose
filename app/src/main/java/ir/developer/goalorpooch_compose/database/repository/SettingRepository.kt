package ir.developer.goalorpooch_compose.database.repository

import dagger.hilt.android.scopes.ViewModelScoped
import ir.developer.goalorpooch_compose.database.SettingDao
import ir.developer.goalorpooch_compose.model.SettingModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class SettingRepository @Inject constructor(private val settingDao: SettingDao) {

    val getItemSetting: Flow<SettingModel> = settingDao.getItemSetting()

    suspend fun addItemSetting(settingModel: SettingModel) =
        settingDao.addItemSetting(settingModel)

    suspend fun updateItemSetting(settingModel: SettingModel) =
        settingDao.updateItemSetting(settingModel)
}