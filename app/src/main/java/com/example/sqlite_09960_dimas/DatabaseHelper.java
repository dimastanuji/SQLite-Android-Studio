package com.example.sqlite_09960_dimas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="data_mhs.db";
    public static final String TABLE_NAME="table_mhs";
    public static final String COL_1="nim";
    public static final String COL_2="nama_mhs";
    public static final String COL_3="tgl";
    public static final String COL_4="alamat";
    public static final String COL_5="kota";
    public static final int DATABASE_VERTION=1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERTION);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table table_mhs(nim text null,nama_mhs text null,tgl text null,alamat text null,kota text null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //metode untuk tambah data
    public boolean insertData( String nim, String nama_mhs, String tgl, String alamat,String kota) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nim);
        contentValues.put(COL_2, nama_mhs);
        contentValues.put(COL_3, tgl);
        contentValues.put(COL_4, alamat);
        contentValues.put(COL_5, kota);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from table_mhs", null);
        return res;
    }
    //metode untuk merubah data
    public boolean updateData(String nim, String nama_mhs, String tgl, String alamat, String kota, String
            bu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nim);
        contentValues.put(COL_2, nama_mhs);
        contentValues.put(COL_3, tgl);
        contentValues.put(COL_4, alamat);
        contentValues.put(COL_5, kota);
        db.update(TABLE_NAME, contentValues, "nim = ?", new
                String[]{nim});
        return true;
    }
    //metode untuk menghapus data
    public int deleteData(String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "nim = ?", new String[]{nim});
    }
}