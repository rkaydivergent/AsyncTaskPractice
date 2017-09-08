package com.example.pc.asynctaskpractice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private	static	final	String	TEXT_STATE	=	"currentText";
    private TextView mTextView	=	null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView	=	(TextView)	findViewById(R.id.textView1);
        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }
    public	void	startTask	(View view)	{
        new	MyAsyncClass(mTextView).execute();
    }

    public class MyAsyncClass extends AsyncTask<String, String, String> {
        TextView mTextView;
        ProgressDialog progressDialog;

        public MyAsyncClass(TextView tv) {
            mTextView = tv;
        }

        @Override
        protected String doInBackground(String... params) {
            Random r = new Random();
            int n = r.nextInt(11);
            int s = n * 200;
            publishProgress("Napping...");
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Awake at last after sleeping for " + s + "	milliseconds!";
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Napping....");
        }

        @Override
        protected void onPostExecute(String result) {
            mTextView.setText(result);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(String... text) {
            mTextView.setText(text[0]);
        }
    }
}
