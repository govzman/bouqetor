package com.example.hse_app

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import org.andresoviedo.util.android.ContentUtils
import java.io.IOException

/**
 * This activity represents the container for our 3D viewer.
 *
 * @author andresoviedo
 */
class ModelActivity : AppCompatActivity() {
    /**
     * Type of model if file name has no extension (provided though content provider)
     */
    var paramType = 0
        private set

    /**
     * The file to load. Passed as input parameter
     */
    var paramUri: Uri? = null
        private set

    /**
     * Enter into Android Immersive mode so the renderer is full screen or not
     */
    private var immersiveMode = false

    /**
     * Background GL clear color. Default is light gray
     */
    val backgroundColor = floatArrayOf(0.47f, 0.67f, 0.9f, 1.0f)
    var gLView: ModelSurfaceView? = null

        private set
    var scene: SceneLoader? = null
        private set
    lateinit var name : String

    private var handler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scene = null
        handler = null
        // Try to get input parameters
        val b = intent.extras
        if (b != null) {
            if (b.getString("uri") != null) {
                paramUri = Uri.parse(b.getString("uri"))
            }
            paramType = if (b.getString("type") != null) b.getString("type")!!.toInt() else -1
            immersiveMode = "true".equals(b.getString("immersiveMode"), ignoreCase = true)
            try {
                val backgroundColors = b.getString("backgroundColor")!!
                    .split(" ".toRegex()).toTypedArray()
                backgroundColor[0] = backgroundColors[0].toFloat()
                backgroundColor[1] = backgroundColors[1].toFloat()
                backgroundColor[2] = backgroundColors[2].toFloat()
                backgroundColor[3] = backgroundColors[3].toFloat()
            } catch (ex: Exception) {
                // Assuming default background color
            }
        }
        Log.i("Renderer", "Params: uri '$paramUri'")
        handler = Handler(mainLooper)

        // Create our 3D sceneario
        name = intent.getStringExtra("name") ?: ""
        try {
            scene = ExampleSceneLoader(this, name)
            (scene as ExampleSceneLoader).init()
        }
        catch (e : Exception) {
            // пустота
        }
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        try {
            setContentView(R.layout.activity_viewer)
            val toolbar : Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                title = "3D Viewer"
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
            var gLView = ModelSurfaceView(this)
            var create_layout: ConstraintLayout = findViewById(R.id.create_layout)
            create_layout.addView(gLView)
            val kekk = findViewById<Button>(R.id.button2)
            kekk.visibility = View.INVISIBLE
            //create_layout.addView(gLView)
            //gLView.setId(1)
            //setContentView(create_layout)


        } catch (e: Exception) {
            Toast.makeText(
                this, """
     Error loading OpenGL view:
     ${e.message}
     """.trimIndent(), Toast.LENGTH_LONG
            ).show()
        }

        // Show the Up button in the action bar.
        setupActionBar()

        // TODO: Alert user when there is no multitouch support (2 fingers). He won't be able to rotate or zoom
        ContentUtils.printTouchCapabilities(packageManager)
        setupOnSystemVisibilityChangeListener()
    }


    /**
     * Set up the [android.app.ActionBar], if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private fun setupActionBar() {
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        // getActionBar().setDisplayHomeAsUpEnabled(true);
        // }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun setupOnSystemVisibilityChangeListener() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return
        }
        window.decorView.setOnSystemUiVisibilityChangeListener { visibility: Int ->
            // Note that system bars will only be "visible" if none of the
            // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                // The system bars are visible. Make any desired
                hideSystemUIDelayed()
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUIDelayed()
        }
    }

    private fun toggleImmersive() {
        immersiveMode = !immersiveMode
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return
        }
        if (immersiveMode) {
            hideSystemUI()
        } else {
            showSystemUI()
        }
        Toast.makeText(this, "Fullscreen " + immersiveMode, Toast.LENGTH_SHORT).show()
    }

    private fun hideSystemUIDelayed() {
        if (!immersiveMode) {
            return
        }
        handler!!.removeCallbacksAndMessages(null)
        //handler!!.postDelayed({ hideSystemUI() }, FULLSCREEN_DELAY.toLong())
    }

    private fun hideSystemUI() {
        if (!immersiveMode) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            hideSystemUIKitKat()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            hideSystemUIJellyBean()
        }
    }

    // This snippet hides the system bars.
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun hideSystemUIKitKat() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        val decorView = window.decorView
        decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or View.SYSTEM_UI_FLAG_IMMERSIVE)
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun hideSystemUIJellyBean() {
        val decorView = window.decorView
        decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LOW_PROFILE)
    }

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun showSystemUI() {
        handler!!.removeCallbacksAndMessages(null)
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            REQUEST_CODE_LOAD_TEXTURE -> {
                // The URI of the selected file
                val uri = data.data
                if (uri != null) {
                    Log.i("ModelActivity", "Loading texture '$uri'")
                    try {
                        ContentUtils.setThreadActivity(this)
                        scene!!.loadTexture(null, uri)
                    } catch (ex: IOException) {
                        Log.e("ModelActivity", "Error loading texture: " + ex.message, ex)
                        Toast.makeText(
                            this, "Error loading texture '$uri'. " + ex
                                .message, Toast.LENGTH_LONG
                        ).show()
                    } finally {
                        ContentUtils.setThreadActivity(null)
                    }
                }
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_LOAD_TEXTURE = 1000
        private const val FULLSCREEN_DELAY = 10000
    }
     */
    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val new_activity = Intent(this@ModelActivity, ActivityView::class.java)
                new_activity.putExtra("name", name)
                startActivity(new_activity)
            }
        }
        return true
    }
}