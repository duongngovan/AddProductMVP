package com.example.addproductmvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private EditText edIdSanPham;
    private EditText edNameSanPham;
    private EditText edGia_San_Pham;
    private EditText edSo_Luong;
    private EditText edChiTiet;
    private Spinner spinnerLoai;
    private List<String> list_Loai = new ArrayList();
    private Button btnLuu;
    private ArrayAdapter arrayAdapter;
    private DataHelper dataHelper;
    private SanPhamDAO sanPhamDAO;
    private  final Product product = new Product();
    private String id,name,gia,so_luong,chitiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        dataHelper = new DataHelper(this);
        sanPhamDAO = new SanPhamDAO(this);
        initView();
        SpLoai();
        btnLuu.setEnabled(false);
        inputLoad();
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedAdd();
            }
        });
    }

    private void SpLoai(){
        list_Loai.add("Điện thoại");
        list_Loai.add("Máy tính PC");
        list_Loai.add("Laptop");
        list_Loai.add("Phụ kiện");
         arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_Loai);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerLoai.setAdapter(arrayAdapter);
        spinnerLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                product.setLoai_Sp(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initView(){
        spinnerLoai = (Spinner)findViewById(R.id.loai_sanpham);
        edChiTiet = findViewById(R.id.edChitiet);
        edSo_Luong = findViewById(R.id.edsoLuong);
        edIdSanPham = findViewById(R.id.id_sanpham);
        edNameSanPham = findViewById(R.id.name_sanpham);
        edGia_San_Pham = findViewById(R.id.gia_sanpham);
        btnLuu = findViewById(R.id.btn_luu);
    }
    private boolean check(){

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Thêm sản phẩm thất bại !");
        if (id.trim().length() == 0 || name.trim().length() == 0 || chitiet.trim().length() == 0 || so_luong.trim().length() == 0 || gia.trim().length() == 0){
            builder.setMessage("Vui lòng nhập đầy đủ !");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
            return false;
        }
        if (sanPhamDAO.checkID(id) ){
            builder.setMessage("ID đã tồn tại !");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
            return false;
        }
        if ( id.trim().length() >= 15 || id.trim().length() < 3){
            builder.setMessage("ID phải từ 3 ký tự trở lên !");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
            return false;
        }
        if (name.trim().length() >= 25 || name.trim().length() <= 5){
            builder.setMessage("Tên phải từ 5 ký tự trở lên !");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
            return false;
        }
        try {
            int i = Integer.parseInt(so_luong);
            if (i <= 0){
                builder.setMessage("Số lượng phải lớn hơn không !");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                return false;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        try {
            int i = Integer.parseInt(gia);
            if (i <= 0){
                builder.setMessage("Số lượng phải lớn hơn không !");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                return false;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return true;
    }
    private void savedAdd(){


         id = edIdSanPham.getText().toString();
         name = edNameSanPham.getText().toString();
         gia = edGia_San_Pham.getText().toString() ;
         so_luong = edSo_Luong.getText().toString();
         chitiet = edChiTiet.getText().toString();



        product.setGia_Sp(Double.parseDouble(gia));
        product.setName_Sp(name);
        product.setId_Sp(id);
        product.setChi_tiet(chitiet);
        product.setSo_luong_sp(Integer.parseInt(so_luong));
        if (check()){
            sanPhamDAO.add(product);
            Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_SHORT).show();

        }


    }
    private void inputLoad(){
        edIdSanPham.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(edIdSanPham.getText())){
                    btnLuu.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edNameSanPham.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(edNameSanPham.getText())){
                    btnLuu.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edGia_San_Pham.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(edGia_San_Pham.getText())){
                    btnLuu.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edSo_Luong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(edSo_Luong.getText())){
                    btnLuu.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edChiTiet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(edChiTiet.getText())){
                    btnLuu.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

}
