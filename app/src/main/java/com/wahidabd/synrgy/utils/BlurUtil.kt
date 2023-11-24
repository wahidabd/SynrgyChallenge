package com.wahidabd.synrgy.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.net.Uri
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.wahidabd.common.utils.Constants
import java.io.File
import java.io.FileOutputStream


/**
 * Created by wahid on 11/24/2023.
 * Github github.com/wahidabd.
 */


class BlurUtil(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams){
    override fun doWork(): Result {
        val inputImagePath = inputData.getString(Constants.INPUT_IMAGE)

        if (!inputImagePath.isNullOrEmpty()) {
            val blurredBitmap = blurImage(inputImagePath)
            val uri = saveBitmapToFile(blurredBitmap, inputImagePath)
            return Result.success(workDataOf(Constants.INPUT_IMAGE to uri.toString()))
        }

        return Result.failure()
    }

    private fun blurImage(inputImagePath: String): Bitmap {
        val bitmap = BitmapFactory.decodeFile(inputImagePath)
        val mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(mutableBitmap)
        val paint = Paint()
        canvas.drawBitmap(mutableBitmap, 0f, 0f, paint)
        return mutableBitmap
    }

    private fun saveBitmapToFile(bitmap: Bitmap, inputImagePath: String): Uri? {
        val file = File(inputImagePath)
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()

        return Uri.fromFile(file)
    }
}