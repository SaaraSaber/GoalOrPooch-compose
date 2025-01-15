package ir.developer.goalorpooch_compose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ir.developer.goalorpooch_compose.model.SettingModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDao {

    @Query("SELECT * FROM item_setting")
    fun getItemSetting(): Flow<SettingModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItemSetting(settingModel: SettingModel)

    @Update
    suspend fun updateItemSetting(settingModel: SettingModel)
}