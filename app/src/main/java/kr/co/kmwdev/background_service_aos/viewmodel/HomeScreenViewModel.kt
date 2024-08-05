package kr.co.kmwdev.background_service_aos.viewmodel

import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import kr.co.kmwdev.background_service_aos.background_service.StartedService

class HomeScreenViewModel : ViewModel() {
    fun startService(context: Context) {
        val intent = Intent(context, StartedService::class.java).apply {

        }
        context.startService(intent)
    }
}