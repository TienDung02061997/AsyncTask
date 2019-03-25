package com.example.asynctaskexample;

import android.os.AsyncTask;
import android.preference.PreferenceGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mProgressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.button_start).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_start:
                    new ProgreessAsyncTasK().execute();
            }
    }

    private class ProgreessAsyncTasK  extends AsyncTask<Void,Integer,String> {

        @Override
        protected String doInBackground(Void... voids) {
            for(int i =0; i <=100;i++){
                // gui du lieu cho ui
                publishProgress(i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Done";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // cap nhat giao dien ui cua ProgressBar tuc gia tri truyen vao la publishProgress
            //là đầu vào của onProgressUpdate co the dua cung luc vao nhieu gia tri
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //Khi tiến trình kết thúc thì ta trả về một String "DONE" cho doInBackground().
            // Đây cũng là đầu vào của onPostExecute(),
            // ta có thể đưa ra kết quả cuối cùng ở đây trên UI Thread.

            Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
        }
        //Tất cả các phương thức onPreExecute(), doInBackground() và onProgressUpdate() và onPostExecute()
        // tham số truyền vào không những là một giá trị mà bạn có thể truyền nhiều giá trí một lúc.

    }




}
