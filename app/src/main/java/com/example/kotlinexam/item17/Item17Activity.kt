package com.example.kotlinexam.item17

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import kotlinx.android.synthetic.main.activity_item17.*
import kotlinx.android.synthetic.main.content_item17.*


class Item17Activity : AppCompatActivity() {
    private lateinit var beepManager: BeepManager
    private var lastText: String? = null

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            if (result.text == null || result.text == lastText) {
                // Prevent duplicate scans
                return
            }

            lastText = result.text
            zxing_barcode_scanner.setStatusText(result.text)

            beepManager.playBeepSoundAndVibrate()

            //Added preview of scanned barcode
            Glide.with(barcode_image).load(
                result.getBitmapWithResultPoints(Color.YELLOW)
            ).into(barcode_image)
        }

        override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.kotlinexam.R.layout.activity_item17)


        val formats = listOf(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39)

        zxing_barcode_scanner
            .barcodeView.decoderFactory = DefaultDecoderFactory(formats)

        beepManager = BeepManager(this)

        zxing_barcode_scanner.decodeContinuous(callback)
    }

    override fun onResume() {
        super.onResume()

        zxing_barcode_scanner.resume()
    }

    override fun onPause() {
        super.onPause()

        zxing_barcode_scanner.pause()
    }

    fun pause(view: View) {
        zxing_barcode_scanner.pause()
    }

    fun resume(view: View) {
        zxing_barcode_scanner.resume()
    }

    fun triggerScan(view: View) {
        zxing_barcode_scanner.decodeSingle(callback)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return zxing_barcode_scanner.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }

}
