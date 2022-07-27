package com.example.hse_app

import android.annotation.SuppressLint
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import org.andresoviedo.util.android.ContentUtils
import org.andresoviedo.android_3d_model_engine.model.Object3DData
import org.andresoviedo.android_3d_model_engine.services.Object3DBuilder
import org.andresoviedo.util.android.assets.Handler
import java.lang.Exception
import java.lang.StringBuilder
import java.net.URL
import java.util.ArrayList
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.GsonBuilder
import java.lang.Math.asin

/**
 * This class loads a 3D scene as an example of what can be done with the app
 *
 * @author andresoviedo
 */
class ExampleSceneLoader(modelActivity: ModelActivity?) : SceneLoader(modelActivity) {
    // TODO: fix this warning
    @SuppressLint("StaticFieldLeak")
    override fun init() {
        super.init()
        URL.setURLStreamHandlerFactory { protocol ->
            if ("assets" == protocol) {
                Handler()
            } else null
        }
        object : AsyncTask<Void?, Void?, Void?>() {
            //ProgressDialog dialog = new ProgressDialog(parent);
            var errors: MutableList<Exception> = ArrayList()
            lateinit var name : String
            val width = parent.getResources().getDisplayMetrics().widthPixels / 2 - 150
            val height = parent.getResources().getDisplayMetrics().heightPixels / 2 - 150
            /*
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.setCancelable(false);
                dialog.setMessage("Loading bouquet...");
                dialog.show();
            }
*/
            protected override fun doInBackground(vararg params: Void?): Void? {
                try {
                    // Set up ContentUtils so referenced materials and/or textures could be find
                    ContentUtils.setThreadActivity(parent)
                    ContentUtils.provideAssets(parent)
                    try {
                        val objects: List<Object3DData> = ArrayList()
                        var obj53: Object3DData
                        //name = intent.getStringExtra("name") ?: ""
                        name = "for mom"
                        var count = 0
                        if (name != "") {
                            var current_bouq: Bouquets = MyRead(name)

                            for (i in current_bouq.current_flowers) {
                                Log.i("!!!!" + count.toString(), (i.x - width).toString() + " " + (i.y - height).toString())
                                Log.i("!!!!" + count.toString(), (kotlin.math.asin((i.x - width) / 800) * 57.296).toString() + " " + (kotlin.math.asin((i.y - height) / 800) * 57.296).toString())
                                count++
                                obj53 = Object3DBuilder.loadV5(
                                    parent,
                                    Uri.parse("assets://assets/models/tuple.obj")
                                )
                                obj53.centerAndScale(8.0f)
                                obj53.position = floatArrayOf(0.006f * (i.x - width), -5f, 0.006f * (i.y - height))
//                                obj53.rotation = floatArrayOf(kotlin.math.asin((i.x - width) / 5), 0f, kotlin.math.asin((i.y - width) / 5))
                                obj53.rotation = floatArrayOf((kotlin.math.asin((i.y - height) / 800) * 57.296).toFloat(), (kotlin.math.asin((i.x - width) / 800) * 57.296).toFloat(), 0f)
                                obj53.color = floatArrayOf(0.8f, 0.2f, 0.2f, 1.0f)
                                // obj53.setDrawMode(GLES20.GL_TRIANGLE_FAN);
                                addObject(obj53)
                            }
                        }

                        // this has heterogeneous faces

                    } catch (ex: Exception) {
                        errors.add(ex)
                    }
                } catch (ex: Exception) {
                    errors.add(ex)
                } finally {
                    ContentUtils.setThreadActivity(null)
                    ContentUtils.clearDocumentsProvided()
                }
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                //                if (dialog.isShowing()) {
//                    dialog.dismiss();
//                }
                if (!errors.isEmpty()) {
                    val msg = StringBuilder("There was a problem loading the data")
                    for (error in errors) {
                        Log.e("Example", error.message, error)
                        msg.append(
                            """
    
    ${error.message}
    """.trimIndent()
                        )
                    }
                    Toast.makeText(parent.applicationContext, msg, Toast.LENGTH_LONG).show()
                }
            }
        }.execute()

        // test loading collada object
        /*try {
            // this has heterogeneous faces
            new ColladaLoaderTask(parent, Uri.parse("assets://assets/models/cowboy.dae"), this).execute();

        } catch (Exception ex) {
            Log.e("Example",ex.getMessage(),ex);
            //errors.add(ex);
        } finally {
            ContentUtils.setThreadActivity(null);
            ContentUtils.clearDocumentsProvided();
        }*/
    }
    fun MyRead(name : String) : Bouquets {
        val gson = GsonBuilder().create()
        val pref = parent.getSharedPreferences(name, Context.MODE_PRIVATE)
        val raw : String? = pref.getString("Bouquet", null)
        if (raw != null) {
            val bouq = gson.fromJson(raw, Bouquets::class.java)
            return bouq
        }
        return Bouquets("null")
    }
}