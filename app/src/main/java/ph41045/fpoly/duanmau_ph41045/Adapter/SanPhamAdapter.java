package com.example.sampleandroidproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.model.SanPham;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SanPhamAdapter extends BaseAdapter {
    private final Context context;
    private final List<SanPham> danhSachSanPham;
    private OnSanPhamClickListener onSanPhamClickListener;
    NumberFormat currencyFormat;

    public interface OnSanPhamClickListener {
        void onAddToCartSanPham(SanPham sanPham, View iconGioHangItem);
        void onEditSanPham(SanPham sanPham);
        void onDeleteSanPham(SanPham sanPham);
    }

    public SanPhamAdapter(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.danhSachSanPham = sanPhamList;
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }
    public void setOnSanPhamClickListener(OnSanPhamClickListener onSanPhamClickListener) {
        this.onSanPhamClickListener = onSanPhamClickListener;
    }

    @Override
    public int getCount() {
        return danhSachSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_san_pham, parent, false);
            holder = new ViewHolder();
            holder.tvTenSanPham = convertView.findViewById(R.id.tvTenSanPham);
            holder.tvGiaSanPham = convertView.findViewById(R.id.tvGiaSanPham);
            holder.tvSoLuongTonKho = convertView.findViewById(R.id.tvSoLuongTonKho);
            holder.imgGioHang = convertView.findViewById(R.id.imgGioHang);
            holder.imgSuaSanPham = convertView.findViewById(R.id.imgSuaDanhMuc);
            holder.imgXoaSanPham = convertView.findViewById(R.id.imgXoaDanhMuc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SanPham sp = danhSachSanPham.get(position);
        holder.tvTenSanPham.setText(sp.getTenSanPham());
        holder.tvGiaSanPham.setText(currencyFormat.format(sp.getGiaSanPham()));
        holder.tvSoLuongTonKho.setText("Tồn kho: " + sp.getSoLuong());
        holder.imgGioHang.setOnClickListener(v -> onSanPhamClickListener.onAddToCartSanPham(sp, holder.imgGioHang));
        holder.imgSuaSanPham.setOnClickListener(v -> onSanPhamClickListener.onEditSanPham(sp));
        holder.imgXoaSanPham.setOnClickListener(v -> onSanPhamClickListener.onDeleteSanPham(sp));

        return convertView;
    }

    static class ViewHolder {
        ImageView imgProduct;
        TextView tvTenSanPham;
        TextView tvGiaSanPham;
        TextView tvSoLuongTonKho;
        ImageView imgGioHang;
        ImageView imgSuaSanPham;
        ImageView imgXoaSanPham;
    }
}
