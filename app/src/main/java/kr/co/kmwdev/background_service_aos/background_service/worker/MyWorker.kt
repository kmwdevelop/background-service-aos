package kr.co.kmwdev.background_service_aos.background_service.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        performTask()
        return Result.success()
    }

    private fun performTask() {
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