package xyz.stylianosgakis.shape.android.feature.settings

import androidx.lifecycle.ViewModel
import com.deliveryhero.whetstone.viewmodel.ContributesViewModel
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@ContributesViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
  init {
    println("Stelios init SettingsViewModel:${System.currentTimeMillis().milliseconds.toIsoString()}")
  }
}
