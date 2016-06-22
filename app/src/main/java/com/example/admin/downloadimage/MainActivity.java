package com.example.admin.downloadimage;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {


    String imageURL="http://d2buyft38glmwk.cloudfront.net/media/images/canonical/mast-sherlock-s3-mini-episode-hires.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownImgTsk().execute();
    }

    private class DownImgTsk extends AsyncTask<Void,Void,Bitmap>{
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("wait");
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL url=new URL(imageURL);
                URLConnection urlConnection=url.openConnection();
                HttpURLConnection httpURLConnection=(HttpURLConnection)urlConnection;
                InputStream inputStream=httpURLConnection.getInputStream();
                Bitmap downloadimage= (Bitmap) BitmapFactory.decodeStream(inputStream);
                return downloadimage;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap image) {
            super.onPostExecute(image);

            ImageView imageView=(ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(image);
            progressDialog.cancel();

        }
    }


}
