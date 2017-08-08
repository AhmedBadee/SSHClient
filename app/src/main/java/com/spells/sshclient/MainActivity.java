package com.spells.sshclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import SSH.SSHApplication;

public class MainActivity extends AppCompatActivity {

    private SSHApplication sshApplication;

    private TextView terminal;

    private ProgressBar connection_bar;
    private LinearLayout connection_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        terminal          = (TextView) findViewById(R.id.terminal);
        connection_bar    = (ProgressBar) findViewById(R.id.connecting_bar);
        connection_layout = (LinearLayout) findViewById(R.id.connection_layout);

        new SSHConnectionTask().execute();
    }

    private class SSHConnectionTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            connection_layout.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {

            sshApplication = new SSHApplication(getApplicationContext());

            return sshApplication.readOutput("");
        }

        @Override
        protected void onPostExecute(String output) {
            connection_layout.setVisibility(View.INVISIBLE);
            terminal.setText(output);
        }
    }
}
