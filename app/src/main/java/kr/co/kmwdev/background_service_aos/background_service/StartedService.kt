package kr.co.kmwdev.background_service_aos.background_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartedService : Service() {
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

}