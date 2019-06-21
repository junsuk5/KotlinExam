package com.example.kotlinexam.item15

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_item15.*
import kotlin.math.min
import kotlin.math.sqrt

class Item15Activity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.kotlinexam.R.layout.activity_item15)

        val navController = findNavController(com.example.kotlinexam.R.id.nav_host_fragment)
        findViewById<BottomNavigationView>(com.example.kotlinexam.R.id.bottom_nav)
            .setupWithNavController(navController)

        viewModel.bitmap.observe(this, Observer {
            Glide.with(imageView).load(it).into(imageView)
        })
    }

    companion object {
        init {
            System.loadLibrary("NativeImageProcessor")
        }
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.example.kotlinexam.R.menu.filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.example.kotlinexam.R.id.action_open -> {
                capturePhoto()
                return true
            }
            com.example.kotlinexam.R.id.action_save -> {

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun capturePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE
            && resultCode == Activity.RESULT_OK
            && data != null) {
            val thumbnail: Bitmap = data.getParcelableExtra("data")
            // Do other work with full size photo saved in locationForPhotos

            viewModel.bitmap.value = mangwonkyung(thumbnail, 50)
        }
    }
    fun red(src: Bitmap, value: Int): Bitmap {
        // image size
        val width = src.width
        val height = src.height
        // create output bitmap
        val bmOut = Bitmap.createBitmap(width, height, src.config)
        // color information
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels
        for (x in 0 until width) {
            for (y in 0 until height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                A = Color.alpha(pixel)
                R = Color.red(pixel)
                R = min(255, R + value)
                G = Color.red(pixel)
                B = Color.red(pixel)

                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B))
            }
        }

        // return final image
        return bmOut
    }

    fun mangwonkyung(src: Bitmap, value: Int): Bitmap {
        // image size
        val width = src.width
        val height = src.height

        val radius = value

        val centerX = width / 2
        val centerY = height / 2

        // create output bitmap
        val bmOut = Bitmap.createBitmap(width, height, src.config)
        // color information
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels
        for (x in 0 until width) {
            for (y in 0 until height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                A = Color.alpha(pixel)
                R = Color.red(pixel)
                G = Color.red(pixel)
                B = Color.red(pixel)

                val distance = sqrt((centerX - x) * (centerX - x) + (centerY - y) * (centerY - y).toDouble())

                if (distance < radius) {
                    bmOut.setPixel(x, y, Color.argb(A, R, G, B))
                } else {
                    bmOut.setPixel(x, y, Color.BLACK)
                }

            }
        }

        // return final image
        return bmOut
    }

    fun createContrast(src: Bitmap, value: Double): Bitmap {
        // image size
        val width = src.width
        val height = src.height
        // create output bitmap
        val bmOut = Bitmap.createBitmap(width, height, src.config)
        // color information
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int
        // get contrast value
        val contrast = Math.pow((100 + value) / 100, 2.0)

        // scan through all pixels
        for (x in 0 until width) {
            for (y in 0 until height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                A = Color.alpha(pixel)
                // apply filter contrast for every channel R, G, B
                R = Color.red(pixel)
                R = (((R / 255.0 - 0.5) * contrast + 0.5) * 255.0).toInt()
                if (R < 0) {
                    R = 0
                } else if (R > 255) {
                    R = 255
                }

                G = Color.red(pixel)
                G = (((G / 255.0 - 0.5) * contrast + 0.5) * 255.0).toInt()
                if (G < 0) {
                    G = 0
                } else if (G > 255) {
                    G = 255
                }

                B = Color.red(pixel)
                B = (((B / 255.0 - 0.5) * contrast + 0.5) * 255.0).toInt()
                if (B < 0) {
                    B = 0
                } else if (B > 255) {
                    B = 255
                }

                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B))
            }
        }

        // return final image
        return bmOut
    }

}

@BindingAdapter("loadImage")
fun loadUrl(imageView: ImageView, item: FilterItem) {
    Glide.with(imageView)
        .load(item.bitmap)
        .into(imageView)
}