package com.example.sqlite_09960_dimas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.sqlite_09960_dimas.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    EditText xnim;
    EditText xnama;
    EditText xtgl;
    EditText xalamat;
    EditText xkota;
    Button tblAdd;
    Button tblView;
    DatabaseHelper BantuDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BantuDb=new DatabaseHelper(this);
        xnim=(EditText)findViewById(R.id.xnim);
        xnama=(EditText)findViewById(R.id.xnama);
        xtgl=(EditText)findViewById(R.id.xtgl);
        xalamat=(EditText)findViewById(R.id.xalamat);
        xkota=(EditText)findViewById(R.id.xkota);
        tblAdd=(Button)findViewById(R.id.tblAdd);
        tblView=(Button)findViewById(R.id.tblView);
        viewAll();



        tblAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted =  BantuDb.insertData(xnim.getText().toString(),xnama.getText().toString(),xtgl.getText().toString(),xalamat.getText().toString(),xkota.getText().toString());
                if(isInserted == true)
                    Toast.makeText(MainActivity.this,"Data Terimpan",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();
            }
        });
        }

    public void viewAll() {
        tblView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res =BantuDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Noting Found");
                            return;                         }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Nim             :" + res.getString(0) + "\n");
                            buffer.append("Nama Mahasiswa  :" + res.getString(1) + "\n");
                            buffer.append("Tanggal Lahir  :" + res.getString(2) + "\n");
                            buffer.append("Alamat  :" + res.getString(3) + "\n");
                            buffer.append("Kota  :" + res.getString(4) + "\n");

    }
    // show all data
                        showMessage("Mahasiswa :", buffer.toString());
                    }
                });     }

        public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
    }
}