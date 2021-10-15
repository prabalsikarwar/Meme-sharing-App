package com.example.memesharingapp;

import static android.view.View.VISIBLE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.service.chooser.ChooserTarget;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {
    ImageView image;
    Button next ,share;

//    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView)findViewById(R.id.imageView);
        next=(Button) findViewById(R.id.button);
        share=(Button) findViewById(R.id.button2);





    }
public void loadmeme(){
//     progressBar.setVisibility(ProgressBar.VISIBLE);


        String url = "https://meme-api.herokuapp.com/gimme";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                String url = response.getString("url");
                            Glide.with(MainActivity.this).load(url).into(image);

//                            Glide.with(MainActivity.this).load(url).listener(new RequestListener<Drawable>() {
//                                @Override
//                                public boolean onLoadFailed(@Nullable GlideException e, Object model,
//                                                            Target<Drawable> target, boolean isFirstResource) {
//                                    progressBar.setVisibility(View.GONE);
//                                    return false;
//
//                                }
//
//                                @Override
//                                public boolean onResourceReady(Drawable resource, Object model,
//                                                               Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                    progressBar.setVisibility(View.GONE);
//                                    return false;
//                                }
//
//                            }).into(image);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }}

                    } ,new Response.ErrorListener() {


                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Toast.makeText(MainActivity.this,"Something Went Wrong!",Toast.LENGTH_LONG).show();


                        }
                    });

                    MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest );



                }

    public void next(View view) {
        loadmeme();

    }
    public void share(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
     intent.putExtra(Intent.EXTRA_TEXT,"Checkout this Meme I got from Reddit ");
        Intent chooserTarget = Intent.createChooser(intent,"share this meme using....");
        startActivity(chooserTarget);



    }

}