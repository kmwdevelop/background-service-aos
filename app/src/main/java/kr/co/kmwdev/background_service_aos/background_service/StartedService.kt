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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.kmwdev.background_service_aos.R

class StartedService : Service() {

    companion object {
        const val ACTION_TASK_COMPLETE = "com.example.mylibrary.ACTION_TASK_COMPLETE"
        const val MSG_TASK = 1
        const val CHANNEL_ID = "MyServiceChannel"
        const val NOTIFICATION_ID = 1
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("멋진")
            .setContentText("이등병")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@StartedService,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                // ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                // public fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                //                                        grantResults: IntArray)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                return@with
            }
            // notificationId is a unique int for each notification that you must define.
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        /**
         * START_STICKY : 서비스가 강제로 종료되었을 때 시스템이 자동으로 다시 시작
         * START_NOT_STICKY : 서비스가 강제로 종료되었을 때 시스템이 다시 시작하지 않음
         * START_REDELIVER_INTENT : 서비스가 강제로 종료되었을 때 시스템이 다시 시작하고 마지막 Intent를 다시 전달
         */
        performTask()
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

    private fun performTask() {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..10) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                Log.d("StartedService", "Waiting... $i")
            }
        }

    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "멋있는는"
            val descriptionText = "사나나이이"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}