package kr.co.kmwdev.background_service_aos.background_service

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.kmwdev.background_service_aos.R
import kr.co.kmwdev.background_service_aos.background_service.worker.MyWorker

class StartedService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        /**
         * START_STICKY : 서비스가 강제로 종료되었을 때 시스템이 자동으로 다시 시작
         * START_NOT_STICKY : 서비스가 강제로 종료되었을 때 시스템이 다시 시작하지 않음
         * START_REDELIVER_INTENT : 서비스가 강제로 종료되었을 때 시스템이 다시 시작하고 마지막 Intent를 다시 전달
         */

        WorkManager.getInstance(this).enqueue(OneTimeWorkRequest.from(MyWorker::class.java))

        return START_STICKY


    }

    override fun onBind(intent: Intent?): IBinder? {
        // 바인딩 서비스가 아닌 경우 null을 반환 한다.
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        // 서비스가 종료될 때 정리 작업 수행
    }
}