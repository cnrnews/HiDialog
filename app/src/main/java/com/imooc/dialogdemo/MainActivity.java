package com.imooc.dialogdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }
    private void showDialog(){
        AlertDialog dialog= new AlertDialog.Builder(this)
                .setContentView(R.layout.comment_dialog)
                .setCancelable(true)
                .fromBottom(false)
                .fullWidth()
                .show();
        dialog.setOnclickListener(R.id.btn_send, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"发送",Toast.LENGTH_SHORT).show();
            }
        });
    }
}