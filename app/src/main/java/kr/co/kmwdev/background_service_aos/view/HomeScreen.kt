package kr.co.kmwdev.background_service_aos.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.co.kmwdev.background_service_aos.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen() {

    val startServiceViewModel = viewModel<HomeScreenViewModel>()
    val context = LocalContext.current

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = {
                    startServiceViewModel.startService(context = context)
                }) {
                    Text(text = "Started Service !")

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}