package com.example.addproductmvp;

public class Product {

    private String id_Sp;
    private String loai_Sp;
    private String name_Sp;
    private double gia_Sp;
    private int so_luong_sp;
    private String chi_tiet;

    public Product() {
    }



    public String getId_Sp() {
        return id_Sp;
    }

    public void setId_Sp(String id_Sp) {
        this.id_Sp = id_Sp;
    }

    public String getLoai_Sp() {
        return loai_Sp;
    }

    public void setLoai_Sp(String loai_Sp) {
        this.loai_Sp = loai_Sp;
    }

    public String getName_Sp() {
        return name_Sp;
    }

    public void setName_Sp(String name_Sp) {
        this.name_Sp = name_Sp;
    }

    public double getGia_Sp() {
        return gia_Sp;
    }

    public void setGia_Sp(double gia_Sp) {
        this.gia_Sp = gia_Sp;
    }

    public int getSo_luong_sp() {
        return so_luong_sp;
    }

    public void setSo_luong_sp(int so_luong_sp) {
        this.so_luong_sp = so_luong_sp;
    }

    public String getChi_tiet() {
        return chi_tiet;
    }

    public void setChi_tiet(String chi_tiet) {
        this.chi_tiet = chi_tiet;
    }
}
