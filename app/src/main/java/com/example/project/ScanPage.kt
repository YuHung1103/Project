package com.example.project

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector


private const val REQUEST_CAMERA: Int = 40

class ScanPage : AppCompatActivity() {

    private lateinit var FrontPageButton : ImageButton
    private lateinit var UserButton: ImageButton

    private lateinit var scanner: SurfaceView
    private lateinit var result: TextView
    private lateinit var cameraSource: CameraSource
    private lateinit var barcodeDetector: BarcodeDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_page)

        FrontPageButton = findViewById(R.id.front_page)
        UserButton = findViewById(R.id.user)

        FrontPageButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        UserButton.setOnClickListener{
            val intent = Intent(this, UserPage::class.java)
            startActivity(intent)
        }

        //移除ActionBar
        supportActionBar?.hide()

        //先獲取相機權限
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA
            )
        } else {
            //初始化掃描器
            initScanner()
            //偵測後回傳
            resultCallback()
        }
    }

    private fun resultCallback() {
        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {

            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                result = findViewById(R.id.result)
                //或取資料
                val barcode = detections.detectedItems
                if (barcode.size() > 0) {
                    runOnUiThread {
                        result.text = barcode.valueAt(0).displayValue
                    }
                }
            }
        })
    }

    private fun initScanner() {
        scanner = findViewById(R.id.scanner)
        scanner.apply {
            //創建Barcode偵測
            barcodeDetector = BarcodeDetector.Builder(this@ScanPage)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build()

            //獲取寬高後創建Camera
            viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    cameraSource = CameraSource.Builder(this@ScanPage, barcodeDetector)
                        .setAutoFocusEnabled(true)
                        .setRequestedPreviewSize(measuredWidth, measuredHeight)
                        .build()
                }
            })

            //camera綁定surfaceView
            holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceCreated(holder: SurfaceHolder) {
                    if (ActivityCompat.checkSelfPermission(
                            this@ScanPage,
                            Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return
                    }
                    cameraSource.start(holder)
                }

                override fun surfaceChanged(
                    holder: SurfaceHolder,
                    format: Int,
                    width: Int,
                    height: Int
                ) {

                }

                override fun surfaceDestroyed(holder: SurfaceHolder) {
                    cameraSource.stop()
                }
            })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (REQUEST_CAMERA == requestCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setContentView(R.layout.activity_main)
            //初始化掃描器
            initScanner()
            //偵測後回傳
            resultCallback()
        }
    }
}