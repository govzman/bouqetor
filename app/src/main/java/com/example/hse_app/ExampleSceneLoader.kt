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
import kotlin.Exception

/**
 * This class loads a 3D scene as an example of what can be done with the app
 *
 * @author andresoviedo
 */
class ExampleSceneLoader(modelActivity: ModelActivity?, val name_f: String = "") : SceneLoader(modelActivity) {
    // TODO: fix this warning
    @SuppressLint("StaticFieldLeak")
    override fun init() {
        super.init()
        try {
                URL.setURLStreamHandlerFactory { protocol ->
                if ("assets" == protocol) {
                    Handler()
                } else null
            }
        }
        catch (e : Error) {
            // пусто
        }
        object : AsyncTask<Void?, Void?, Void?>() {
            //ProgressDialog dialog = new ProgressDialog(parent);
            var errors: MutableList<Exception> = ArrayList()

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
                        var name : String = name_f
                        if (name != "") {
                            var current_bouq: Bouquets = MyRead(name)


                            for (i in current_bouq.current_flowers) {
//                                Log.i("!!!!" + count.toString(), (i.x - width).toString() + " " + (i.y - height).toString())
//                                Log.i("!!!!" + count.toString(), (kotlin.math.asin((i.x - width) / 800) * 57.296).toString() + " " + (kotlin.math.asin((i.y - height) / 800) * 57.296).toString())
                                val filename = when (i.name) {
                                    Flowers.chamomile -> "assets://assets/models/sunflower.obj"
                                    Flowers.rose -> "assets://assets/models/rose.obj"
                                    // Flowers.lily -> "assets://assets/models/lily.obj"
                                    else -> "assets://assets/models/tuple2.obj"
                                }
                                val size = when (i.name){
                                    Flowers.chamomile -> 3.0f
                                    Flowers.rose -> 5.0f
                                    //Flowers.lily -> 7f
                                    else -> 8.0f
                                }
                                val color = when (i.name){
                                    Flowers.chamomile -> floatArrayOf(1f, 1f, 1f, 1.0f)
                                    Flowers.rose -> floatArrayOf(1f, 0f, 0f, 1.0f)
                                    Flowers.carnation -> floatArrayOf(1f, 0.2f, 0.2f, 1.0f)
                                    Flowers.chrysanthemum -> floatArrayOf(0.86f, 0.59f, 0.75f, 1.0f)
                                    Flowers.peony -> floatArrayOf(0.94f, 0.74f, 0.83f, 1.0f)
                                    Flowers.iris -> floatArrayOf(0.52f, 0.3f, 0.6f, 1.0f)
                                    Flowers.lily -> floatArrayOf(1f, 0.5f, 0f, 1.0f)
                                    Flowers.hortensia -> floatArrayOf(0.53f, 0.61f, 0.8f, 1.0f)
                                    Flowers.sunflower -> floatArrayOf(0.9f, 0.9f, 0f, 1.0f)
                                    Flowers.gypsophila -> floatArrayOf(0.9f, 0.9f, 0.9f, 1.0f)
                                    Flowers.ruscus -> floatArrayOf(0.22f, 0.37f, 0.13f, 1.0f)
                                    Flowers.dianthus -> floatArrayOf(0.62f, 0.16f, 0.26f, 1.0f)
                                    Flowers.trachelium -> floatArrayOf(0.47f, 0.45f, 0.76f, 1.0f)
                                    else -> floatArrayOf(1f, 0.6f, 0.6f, 1.0f)
                                }
                                val h = when (i.name){
                                    Flowers.chamomile -> -1.2f
                                    Flowers.rose -> -1.8f
                                    else -> -3f
                                }
                                obj53 = Object3DBuilder.loadV5(
                                    parent,
                                    Uri.parse(filename)
                                )
                                obj53.centerAndScale(size)
                                obj53.position = floatArrayOf(0.006f * (i.x - width), h, 0.006f * (i.y - height))
                                //obj53.rotation = floatArrayOf(kotlin.math.asin((i.x - width) / 5), 0f, kotlin.math.asin((i.y - width) / 5))
                                // obj53.rotation = floatArrayOf((kotlin.math.asin((i.y - height) / 1200) * 57.296).toFloat(), 0f, 0f)
                                //obj53.rotation = floatArrayOf(0f, 0f, -(kotlin.math.asin((i.x - width) / 1200) * 57.296).toFloat())
                                obj53.rotation = floatArrayOf((kotlin.math.asin((i.y - height) / 900) * 57.296).toFloat(), 0f, -(kotlin.math.asin((i.x - width) / 900) * 57.296).toFloat())
                                obj53.color = color
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