package com.example.android.yosafat_1202154119_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListNamaMhs extends AppCompatActivity {
    Button mBtnMulaiAsyncTask;
    ProgressBar mProgressBar;
    ListView mListView;
    String[] mListMhs = {
            "Suwardi Kiesmo",
            "Mardjono Siswanto",
            "Wisnu Tjaroko",
            "Bagong Eko",
            "Siti Wardjono",
            "Endah Moelwardi",
            "Hazim Djojo",
            "Hendro Siswojo",
            "Mamiek Samioen",
            "Wijoso Soelardi",
    };
    private ItemListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama_mhs);

        mBtnMulaiAsyncTask = (Button) findViewById(R.id.btn_mulaiasynctask);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mListView = (ListView) findViewById(R.id.listviewmhs);

        //untuk menetapkan nilai dari nama array ke dalam array adapter
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //jika button diklik maka menjalankan onClick
        mBtnMulaiAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //proses adapter dengan asynctask
                itemListView = new ItemListView();
                itemListView.execute();
            }
        });
    }

    private class ItemListView extends AsyncTask<Void, String, Void> {
        ArrayAdapter<String> mAdapter;
        int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListNamaMhs.this);

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //membuat progress style berbentuk spinner
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");

            //jika button Mulai AsyncTask ditekan maka akan menampilkan progressbar dan listview
            mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Cancel Process",
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    itemListView.cancel(true); //cancel bernilai true
                    mProgressBar.setVisibility(View.VISIBLE);
                    //progressbar hilang ketika dialog setelah dijalankan
                    dialogInterface.dismiss();
                    mProgressBar.setVisibility(View.GONE);
                }
            });
            //progress bar ditampilkan
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String data : mListMhs) {
                //publish progress untuk mengirimkan object ke method onProgressUpdate
                publishProgress(data);
                try {
                    Thread.sleep(500); //code digunakan untuk men-delay proses selama 5 detik
                } catch (Exception e){
                    e.printStackTrace();
                } if(isCancelled()) {
                    //bernilai true ketika melakukan cancel
                    itemListView.cancel(true);
                    //mListView.setVisibility(View.GONE);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //memberikan nilai pada adapter
            mAdapter.add(values[0]);

            //int status adalah variabel berisi angka persen dari proses asynctask
            Integer status = (int) ((counter / (float)mListMhs.length * 100));
            mProgressBar.setProgress(status);

            //hanya dijalankan saat loading horizontal
            mProgressDialog.setProgress(status);

            //tidak dijalankan ketika menggunakan loading horizontal
            mProgressDialog.setMessage(String.valueOf(status+" %"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //circle progressbar diatur dengan dihapuskan dari tampilan
            mProgressBar.setVisibility(View.GONE);

            //progress dialog dihentikan dari tampilan dan mListView ditampilkan
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }
    }
}
