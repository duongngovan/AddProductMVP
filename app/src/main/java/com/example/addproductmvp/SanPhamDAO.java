package com.example.addproductmvp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    public static final String TB_NAME = "sanpham";
    private static final String ID = "id_sanpham";
    private static final String NAME = "name_sanpham";
    private static final String SPINNER = "id_loai";
    private static final String GIABAN = "giaban_sp";
    private static final String SOLUONG = "soluong_sp";
    private static final String CHITIET = "chitiet_sp";

    private SQLiteDatabase db;
    private DataHelper dataHelper;

    private static final String TAG = "sanphamDAO";

    public SanPhamDAO(Context context) {
        dataHelper = new DataHelper(context);
        db = dataHelper.getWritableDatabase();
    }

    public static final String create_tb = " create table " + TB_NAME + " ( " + ID + " text primary key, " +
            NAME + " text not null, " + SPINNER + " text not null, " +
            GIABAN + " real not null, " +
             SOLUONG + " integer not null, " + CHITIET + " text not null )";

    public int add(Product sanPham) {
        ContentValues values = new ContentValues();
        values.put(ID, sanPham.getId_Sp());
        values.put(SPINNER, sanPham.getLoai_Sp());
        values.put(NAME, sanPham.getName_Sp());
        values.put(GIABAN, sanPham.getGia_Sp());
        values.put(SOLUONG, sanPham.getSo_luong_sp());
        values.put(CHITIET, sanPham.getChi_tiet());
        if (db.insert(TB_NAME, null, values) > 0) {
            return 1;
        }
        return -1;
    }

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String select = " select * from " + TB_NAME;
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                Product sanPham = new Product();
                sanPham.setId_Sp(cursor.getString(0));
                sanPham.setName_Sp(cursor.getString(1));
                sanPham.setLoai_Sp(cursor.getString(2));
                sanPham.setGia_Sp(cursor.getDouble(3));
                sanPham.setSo_luong_sp(cursor.getInt(4));
                sanPham.setChi_tiet(cursor.getString(5));
                list.add(sanPham);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public double tinhTong(String name, int soluong) {
        double tinhtong = 0;

        Cursor cursor = null;
        try {
            cursor = db.query(TB_NAME, null, NAME + " = ? ", new String[]{String.valueOf(name)}, null, null, null);
            cursor.moveToFirst();
            Product sanPham = new Product();
            sanPham.setGia_Sp(cursor.getDouble(3));
            Log.d("abcdef", String.valueOf(sanPham.getGia_Sp()));
            tinhtong = sanPham.getGia_Sp() * soluong;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tinhtong;
    }

    public boolean checkID(String id) {
        Cursor cursor = null;
        try {
            cursor = db.query(TB_NAME,null,ID + " = ? ",new String[]{String.valueOf(id)},null,null,null);
            cursor.moveToFirst();
            int i  = cursor.getCount();
            if (i <= 0) {
                return false;
            }
            return true;
        }catch (Exception e){

        }
        return false;
    }
    public int update(Product sanPham){
        ContentValues values = new ContentValues();
        values.put(NAME,sanPham.getName_Sp());
        values.put(GIABAN,sanPham.getGia_Sp());
        values.put(SOLUONG,sanPham.getSo_luong_sp());
        values.put(CHITIET,sanPham.getChi_tiet());
        return db.update(TB_NAME,values, ID + " = ? ",new String[]{String.valueOf(sanPham.getId_Sp())});
    }
    public int delete(Product sanPham){
        return db.delete(TB_NAME, ID + " = ? ", new String[]{String.valueOf(sanPham.getId_Sp())});

    }
}
