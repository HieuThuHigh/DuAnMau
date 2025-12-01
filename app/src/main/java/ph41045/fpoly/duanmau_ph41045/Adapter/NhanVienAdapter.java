package ph41045.fpoly.duanmau_ph41045.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ph41045.fpoly.duanmau_ph41045.Model.NhanVien;
import ph41045.fpoly.duanmau_ph41045.R;


import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class NhanVienAdapter extends BaseAdapter {
    private final Context context;
    private final List<NhanVien> danhSachNhanVien;
    private OnNhanVienClickListener onNhanVienClickListener;
    NumberFormat currencyFormat;

    public interface OnNhanVienClickListener {
        void onEditNhanVien(NhanVien nhanVien);
        void onDeleteNhanVien(NhanVien nhanVien);
    }

    public NhanVienAdapter(Context context, List<NhanVien> danhSachNhanVien) {
        this.context = context;
        this.danhSachNhanVien = danhSachNhanVien;
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }

    public void setOnNhanVienClickListener(OnNhanVienClickListener onNhanVienClickListener) {
        this.onNhanVienClickListener = onNhanVienClickListener;
    }

    @Override
    public int getCount() {
        return danhSachNhanVien.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachNhanVien.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nhan_vien, parent, false);
            holder = new ViewHolder();
            holder.tvMaNhanVien = convertView.findViewById(R.id.tvMaNhanVien);
            holder.tvTenNhanVien = convertView.findViewById(R.id.tvTenNhanVien);
            holder.tvDiaChi = convertView.findViewById(R.id.tvDiaChi);
            holder.tvLuong = convertView.findViewById(R.id.tvLuong);
            holder.tvChucVu = convertView.findViewById(R.id.tvChucVu);
            holder.imgSuaNhanVien = convertView.findViewById(R.id.imgSuaNhanVien);
            holder.imgXoaNhanVien = convertView.findViewById(R.id.imgXoaNhanVien);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NhanVien nhanVien = danhSachNhanVien.get(position);
        holder.tvMaNhanVien.setText(nhanVien.getMaNhanVien());
        holder.tvTenNhanVien.setText(nhanVien.getTenNhanVien());
        holder.tvDiaChi.setText(nhanVien.getDiaChi());
        holder.tvLuong.setText(currencyFormat.format(nhanVien.getLuong()));
        holder.tvChucVu.setText(nhanVien.getChucVu() == 0 ? "Nhân viên" : "Quản lý");
        holder.imgSuaNhanVien.setOnClickListener(v -> onNhanVienClickListener.onEditNhanVien(nhanVien));
        holder.imgXoaNhanVien.setOnClickListener(v -> onNhanVienClickListener.onDeleteNhanVien(nhanVien));

        return convertView;
    }

    static class ViewHolder {
        ImageView imgProduct;
        TextView tvMaNhanVien;
        TextView tvTenNhanVien;
        TextView tvDiaChi;
        TextView tvLuong;
        TextView tvChucVu;
        ImageView imgSuaNhanVien;
        ImageView imgXoaNhanVien;
    }
}
