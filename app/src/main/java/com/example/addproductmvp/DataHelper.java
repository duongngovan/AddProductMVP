package com.example.addproductmvp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATA = "data_quanly";

    public DataHelper(@Nullable Context context) {
        super(context, DATA, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(SanPhamDAO.create_tb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL(" drop table if exists "+SanPhamDAO.TB_NAME);
       onCreate(db);
    }
}
