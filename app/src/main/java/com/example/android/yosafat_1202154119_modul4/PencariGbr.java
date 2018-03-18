package com.example.android.yosafat_1202154119_modul4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.URL;

public class PencariGbr extends AppCompatActivity {
    EditText mMasukkanURLGbr;
    Button mCariGbr;
    ImageView mTampilGbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gbr);

        mMasukkanURLGbr = (EditText) findViewById(R.id.et_masukkanurl);
        mCariGbr = (Button) findViewById(R.id.btn_carigbr);
        mTampilGbr = (ImageView) findViewById(R.id.imageview_gambar);

        //final String imgURL = "https://media.golfdigest.com/photos/56cdddcc35ca4a572a7444bd/master/pass/Tiger-Woods-2016.jpg";

        //ketika button diklik maka akan menjalankan perintah pada onClick
        mCariGbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inputan diambil ke dalam bentuk string dan dieksekusi
                final String imgURL = mMasukkanURLGbr.getText().toString();
                new DownloadImageTask(mTampilGbr).execute(imgURL);
            }
        });
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageTask(ImageView mTampilGbr) {
            this.imageView = mTampilGbr;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String URLOfImage = urls[0]; //nilai pada URLOfImage
            Bitmap logo = null;
            //membuat koneksi dengan URL gambar dan mengembalikan InputStream
            try {
                InputStream IS = new URL(URLOfImage).openStream();
                logo = BitmapFactory.decodeStream(IS); //format gambar di-decode
            } catch (Exception e) {
                e.printStackTrace();
            }
            return logo;
        }

        protected void onPostExecute(Bitmap result) {
            //gambar ditampilkan ke dalam imageview
            imageView.setImageBitmap(result);
        }
    }
}
