package com.example.hiyandao101.horserace;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    TextView tv2;
    TextView tv3;
    Button btn;
    int num = 1;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        btn = (Button)findViewById(R.id.btn);
        tv.setVisibility(View.GONE);
    }

    public void startRace(View v){
        new MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 1, 100, 1);
        new MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 1, 100, 2);
        new MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 1, 100, 3);
    }

    class MyTask extends AsyncTask<Integer, String, Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv.setVisibility(View.VISIBLE);
            btn.setEnabled(false);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if(values[1].equals("1")){
                tv.setText(values[0]);}
            else if(values[1].equals("2")){
                tv2.setText(values[0]);}
            else if(values[1].equals("3")){
                tv3.setText(values[0]);}
        }


        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if(result == 1){
                tv.setText(" Horse " + result + " finished " + num);
                num++;
            }
            else if(result == 2){
                tv2.setText(" Horse " + result + " finished " + num);
                num++;
            }
            else if(result == 3){
                tv3.setText(" Horse " + result + " finished " + num);
                num++;
            }
            btn.setEnabled(true);
        }

        @Override
        protected Integer doInBackground(Integer... args) {
            if(1 == 1){
                int start = args[0];
                int end = args[1];
                int horseNum = args[2];

                for(int i=start;i<=end;i++){
                    try {
                        Thread.sleep(r.nextInt(400) + 100);
                        publishProgress("Horse " + horseNum + ": " + i + "/" + end ,horseNum + "");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return horseNum;
            }
            return 0;
        }
    }
}