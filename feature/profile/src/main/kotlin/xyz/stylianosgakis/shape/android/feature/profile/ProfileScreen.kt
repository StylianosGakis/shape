package xyz.stylianosgakis.shape.android.feature.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import xyz.stylianosgakis.shape.android.core.designsystem.component.topappbar.transparentSmallTopAppBarColors
import xyz.stylianosgakis.shape.android.core.designsystem.icons.ShapeIcons
import xyz.stylianosgakis.shape.android.feature.profile.ui.PersonalStats
import xyz.stylianosgakis.shape.database.Achievement

@Composable
internal fun ProfileRoute(
  navigateToSettings: () -> Unit,
  viewModel: ProfileViewModel,
) {
  val uiState by viewModel.achievements.collectAsState()
  ProfileScreen(
    achievements = uiState,
    navigateToSettings = navigateToSettings,
    addAchievement = viewModel::addAchievement,
    deleteAchievement = viewModel::deleteAchievement,
  )
}

@Composable
private fun ProfileScreen(
  achievements: ImmutableList<Achievement>,
  navigateToSettings: () -> Unit,
  addAchievement: () -> Unit,
  deleteAchievement: (Long) -> Unit,
) {
  Column {
    ProfileTopAppBar(navigateToSettings)
    Column(
      Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
    ) {
      PersonalStats(addAchievement)
      achievements.reversed().forEach { achievement ->
        ListItem(
          headlineText = { Text(achievement.full_name) },
          overlineText = { Text(achievement.achievement_group) },
          supportingText = { Text(achievement.id.toString()) },
          modifier = Modifier.clickable {
            deleteAchievement(achievement.id)
          },
        )
      }
      Text(
        text = "Journey",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(horizontal = 16.dp),
      )
      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp),
      ) {
        repeat(15) { index ->
          JourneyItem(index)
        }
      }
    }
  }
}

@Composable
private fun ProfileTopAppBar(
  navigateToSettings: () -> Unit,
) {
  TopAppBar(
    title = { Text(stringResource(R.string.profile)) },
    actions = {
      IconButton(navigateToSettings) {
        Icon(ShapeIcons.SettingsGear, "Settings")
      }
    },
    colors = TopAppBarDefaults.transparentSmallTopAppBarColors(),
    scrollBehavior = pinnedScrollBehavior(),
  )
}

@Composable
private fun JourneyItem(index: Int) {
  Card(
    Modifier.fillMaxWidth(),
  ) {
    Column(
      verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
      modifier = Modifier.heightIn(80.dp).padding(16.dp),
    ) {
      Text("Achievement #$index")
      Text("Go crazy Mr#$index")
    }
  }
}
