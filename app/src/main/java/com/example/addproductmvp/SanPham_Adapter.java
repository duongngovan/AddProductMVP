package com.example.addproductmvp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SanPham_Adapter extends RecyclerView.Adapter<SanPham_Adapter.ViewHolder> {
    private List<Product> list;
    private SanPhamDAO sanPhamDAO;
    private Context context;
    private EditText edName,edGiaBan,edChitiet,edSoLuong;
    private Button btnBack,btnUpdate;

    public SanPham_Adapter(List<Product> list,Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        sanPhamDAO = new SanPhamDAO(context);
        View view = layoutInflater.inflate(R.layout.item_product,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = layoutInflater.inflate(R.layout.update_sanpham, parent, false);
                builder.setView(view1);
                edName = view1.findViewById(R.id.namesp_update);
                edGiaBan = view1.findViewById(R.id.giabansp_update);
                edChitiet = view1.findViewById(R.id.baohanhsp_update);
                edSoLuong = view1.findViewById(R.id.soluongsp_update);

                btnBack = view1.findViewById(R.id.backbtn);
                btnUpdate = view1.findViewById(R.id.save_btn);


                edName.setText(list.get(viewHolder.getAdapterPosition()).getName_Sp());
                edGiaBan.setText(list.get(viewHolder.getAdapterPosition()).getGia_Sp()+"");
                edChitiet.setText(list.get(viewHolder.getAdapterPosition()).getChi_tiet());
                edSoLuong.setText(list.get(viewHolder.getAdapterPosition()).getSo_luong_sp()+"");

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edName.length() != 0 && edGiaBan.length() != 0 && edChitiet.length() != 0 && edSoLuong.length() != 0) {
                            list.get(viewHolder.getAdapterPosition()).setName_Sp(edName.getText() + "");
                            list.get(viewHolder.getAdapterPosition()).setGia_Sp(Double.parseDouble(edGiaBan.getText()+""));
                            list.get(viewHolder.getAdapterPosition()).setChi_tiet(edChitiet.getText() + "");
                            list.get(viewHolder.getAdapterPosition()).setSo_luong_sp(Integer.parseInt(edSoLuong.getText().toString() +""));
                            sanPhamDAO.update(list.get(viewHolder.getAdapterPosition()));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context,"Cập nhật thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn có muốn xóa không");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sanPhamDAO.delete(list.get(viewHolder.getAdapterPosition()));
                        list.remove(list.get(viewHolder.getAdapterPosition()));
                        notifyItemRemoved(viewHolder.getAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,"Hủy bỏ thao tác thành công",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = list.get(position);
        holder.txtName_SP.setText("Tên :  "+product.getName_Sp());
        holder.txtGia_Ban_Sp.setText("Giá bán :  "+(double) product.getGia_Sp());
        holder.txtSo_Luong.setText("Số lượng :  "+product.getSo_luong_sp());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView txtName_SP;
        public TextView txtGia_Ban_Sp;
        public TextView txtSo_Luong;
        public ImageButton imageButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            txtSo_Luong = itemView.findViewById(R.id.soluong_sanpham);
            txtName_SP = itemView.findViewById(R.id.name_sanpham);
            txtGia_Ban_Sp = itemView.findViewById(R.id.giaban_sanpham);
            imageButton = itemView.findViewById(R.id.delete_sp);
        }
    }
}
