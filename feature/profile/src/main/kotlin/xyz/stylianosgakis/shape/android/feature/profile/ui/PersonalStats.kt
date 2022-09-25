package xyz.stylianosgakis.shape.android.feature.profile.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.persistentListOf
import xyz.stylianosgakis.shape.android.core.designsystem.icons.ShapeIcon
import xyz.stylianosgakis.shape.android.core.designsystem.icons.ShapeIcons

@Immutable
class PersonalStat(
  val icon: ShapeIcon,
  val title: String,
  val subtitle: String,
  val onClick: () -> Unit,
)

@Composable
internal fun PersonalStats(addAchievement: () -> Unit) {
  val items = remember {
    persistentListOf(
      PersonalStat(ShapeIcon(ShapeIcons.Achievement), "Achievements", "4 Unlocked") {},
      PersonalStat(ShapeIcon(ShapeIcons.Stats), "Achievements", "4 Unlocked") {},
      PersonalStat(ShapeIcon(ShapeIcons.Duel), "Achievements", "4 Unlocked") {},
      PersonalStat(ShapeIcon(ShapeIcons.MonthChallenge), "Achievements", "4 Unlocked") {
        addAchievement()
      },
    )
  }

  Column {
    for (item in items) {
      PersonalStat(item)
    }
  }
}

@Composable
fun PersonalStat(
  stat: PersonalStat,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .clickable { stat.onClick() }
      .padding(vertical = 16.dp),
  ) {
    Spacer(Modifier.width(16.dp))
    ShapeIcon(stat.icon, Modifier.size(32.dp))
    Spacer(Modifier.width(16.dp))
    Column(
      verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
      Text(text = stat.title)
      Text(text = stat.subtitle, fontWeight = FontWeight.Light, style = MaterialTheme.typography.bodyMedium)
    }
  }
}
