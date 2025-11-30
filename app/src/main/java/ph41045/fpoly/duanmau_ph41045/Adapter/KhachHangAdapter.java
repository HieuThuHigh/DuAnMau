package com.example.sampleandroidproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.model.DanhMuc;
import com.example.sampleandroidproject.model.KhachHang;

import java.util.List;

public class KhachHangAdapter extends BaseAdapter {
    private final Context context;
    private final List<KhachHang> danhSachKhachHang;
    private OnKhachHangClickListener onKhachHangClickListener;
    public interface OnKhachHangClickListener {
        void onEditKhachHang(KhachHang khachHang);
        void onDeleteKhachHang(KhachHang khachHang);
    }

    public KhachHangAdapter(Context context, List<KhachHang> danhSachKhachHang) {
        this.context = context;
        this.danhSachKhachHang = danhSachKhachHang;
    }

    public void setOnKhachHangClickListener(OnKhachHangClickListener onKhachHangClickListener) {
        this.onKhachHangClickListener = onKhachHangClickListener;
    }

    @Override
    public int getCount() {
        return danhSachKhachHang.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachKhachHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_khach_hang, parent, false);
            holder = new ViewHolder();
            holder.tvTenKhachHang = convertView.findViewById(R.id.tvTenKhachHang);
            holder.tvMaKhachHang = convertView.findViewById(R.id.tvMaKhachHang);
            holder.tvDiaChi = convertView.findViewById(R.id.tvDiaChi);
            holder.tvSoDienThoai = convertView.findViewById(R.id.tvSoDienThoai);
            holder.tvEmail = convertView.findViewById(R.id.tvEmail);
            holder.imgSuaKhachHang = convertView.findViewById(R.id.imgSuaKhachHang);
            holder.imgXoaKhachHang = convertView.findViewById(R.id.imgXoaKhachHang);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        KhachHang sp = danhSachKhachHang.get(position);
        holder.tvTenKhachHang.setText(sp.getTenKhachHang());
        holder.tvMaKhachHang.setText(sp.getMaKhachHang());
        holder.tvDiaChi.setText(sp.getDiaChi());
        holder.tvSoDienThoai.setText(sp.getSoDienThoai());
        holder.tvEmail.setText(sp.getEmail());
        holder.imgSuaKhachHang.setOnClickListener(v -> onKhachHangClickListener.onEditKhachHang(sp));
        holder.imgXoaKhachHang.setOnClickListener(v -> onKhachHangClickListener.onDeleteKhachHang(sp));

        return convertView;
    }
    static class ViewHolder {
        TextView tvTenKhachHang;
        TextView tvMaKhachHang;
        TextView tvDiaChi;
        TextView tvSoDienThoai;
        TextView tvEmail;
        ImageView imgSuaKhachHang;
        ImageView imgXoaKhachHang;
    }
}
