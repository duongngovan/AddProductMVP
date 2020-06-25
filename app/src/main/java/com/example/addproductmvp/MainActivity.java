package com.example.addproductmvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private RecyclerView recyclerView;
    private SanPham_Adapter sanPham_adapter;
    private DataHelper dataHelper;
    private SanPhamDAO sanPhamDAO;
    private List<Product> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tool_bar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataHelper = new DataHelper(this);
        sanPhamDAO = new SanPhamDAO(this);
        list = sanPhamDAO.getAll();
        sanPham_adapter = new SanPham_Adapter(list,getApplicationContext());
        intent = new Intent(this,Main3Activity.class);
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sanPham_adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addproduct){
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
