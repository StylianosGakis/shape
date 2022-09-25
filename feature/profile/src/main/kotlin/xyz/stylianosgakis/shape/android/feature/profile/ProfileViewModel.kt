package xyz.stylianosgakis.shape.android.feature.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deliveryhero.whetstone.viewmodel.ContributesViewModel
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import xyz.stylianosgakis.shape.database.Achievement
import xyz.stylianosgakis.shape.database.ShapeDatabase
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@ContributesViewModel
class ProfileViewModel @Inject constructor(
  sqlDriver: SqlDriver,
  @Suppress("UNUSED_PARAMETER") savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val shapeDatabase = ShapeDatabase(sqlDriver)
  val achievements: StateFlow<ImmutableList<Achievement>> = shapeDatabase.achievementQueries
    .selectAll().asFlow().mapToList()
    .map { it.toImmutableList() }
    .stateIn(
      viewModelScope,
      SharingStarted.WhileSubscribed(5.seconds),
      persistentListOf(),
    )

  fun addAchievement() {
    viewModelScope.launch {
      shapeDatabase.achievementQueries.addAchievement(
        List((4..10).random()) { (65..90).random().toChar().toString() }.joinToString(""),
      )
    }
  }

  fun deleteAchievement(id: Long) {
    viewModelScope.launch {
      shapeDatabase.achievementQueries.removeAchievement(id)
    }
  }
}
