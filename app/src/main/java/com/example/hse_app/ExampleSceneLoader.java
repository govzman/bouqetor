package com.example.hse_app;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;

import org.andresoviedo.android_3d_model_engine.model.Object3DData;
import org.andresoviedo.android_3d_model_engine.services.Object3DBuilder;
import org.andresoviedo.util.android.ContentUtils;
import org.andresoviedo.util.io.IOUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class loads a 3D scene as an example of what can be done with the app
 * 
 * @author andresoviedo
 *
 */
public class ExampleSceneLoader extends SceneLoader {

	public ExampleSceneLoader(ModelActivity modelActivity) {
		super(modelActivity);
	}

	// TODO: fix this warning
	@SuppressLint("StaticFieldLeak")
    public void init() {
		super.init();
		new AsyncTask<Void, Void, Void>() {

			List<Exception> errors = new ArrayList<>();


            @Override
            protected Void doInBackground(Void... params) {
                try {
                    // Set up ContentUtils so referenced materials and/or textures could be find
                    ContentUtils.setThreadActivity(parent);
                    ContentUtils.provideAssets(parent);

                    // test cube made of arrays
                    Object3DData obj10 = Object3DBuilder.buildCubeV1();
                    obj10.setColor(new float[] { 1f, 0f, 0f, 0.5f });
                    obj10.setPosition(new float[] { -2f, 2f, 0f });
                    addObject(obj10);

                    try {
                        // this has heterogeneous faces
                        Object3DData obj53 = Object3DBuilder.loadV5(parent, Uri.parse("assets://assets/models/ToyPlane.obj"));
                        //InputStream open = ContentUtils.getInputStream(Uri.parse("assets://assets/models/"+obj53.getTextureFile()));
                        //obj53.setTextureData(IOUtils.read(open));
                        obj53.centerAndScale(2.0f);
                        obj53.setPosition(new float[] { 0f, 0f, 0f });
                        obj53.setColor(new float[] { 1.0f, 1.0f, 1f, 1.0f });
                        // obj53.setDrawMode(GLES20.GL_TRIANGLE_FAN);
                        addObject(obj53);
                    } catch (Exception ex) {
                        errors.add(ex);
                    }

                    // test loading object without normals
                    /*try {
                        Object3DData obj = Object3DBuilder.loadV5(parent, Uri.parse("assets://assets/models/cube4.obj"));
                        obj.setPosition(new float[] { 0f, 2f, -2f });
                        obj.setColor(new float[] { 0.3f, 0.52f, 1f, 1.0f });
                        addObject(obj);
                    } catch (Exception ex) {
                        errors.add(ex);
                    }*/
                } catch (Exception ex) {
                    errors.add(ex);
                } finally{
                    ContentUtils.setThreadActivity(null);
                    ContentUtils.clearDocumentsProvided();
                }
                return null;
            }

		}.execute();

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
}
