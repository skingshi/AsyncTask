package com.sking.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Button btn;
    private ProgressBar ps;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button03);
        ps = (ProgressBar)findViewById(R.id.progressBar02);
        ps.setMax(100);
        ps.setProgress(0);
        tv = (TextView) findViewById(R.id.textView01);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Integer,Integer,String>(){
                    @Override
                    protected void onPreExecute() {
                        tv.setText("开始执行异步线程");
                                          }

                    @Override
                    protected void onPostExecute(String s) {
                        tv.setText("异步操作执行结束" + s);
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        int vlaue = values[0];
                        ps.setProgress(vlaue);
                    }

                    @Override
                    protected String doInBackground(Integer... params) {
                        int i = 0;
                        for (i = 10; i <= 100; i+=10) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            publishProgress(i);
                        }
                        return i + params[0].intValue() + "";
                    }
                }.execute(1000);
            }
        });
    }

}
