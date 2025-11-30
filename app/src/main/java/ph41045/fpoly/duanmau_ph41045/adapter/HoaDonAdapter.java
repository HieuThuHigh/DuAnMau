package ph41045.fpoly.duanmau_ph41045.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.dto.HoaDonDto;

import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    private final Context context;
    private final List<HoaDonDto> danhSachHoaDon;
    private OnHoaDonClickListener onHoaDonClickListener;

    public interface OnHoaDonClickListener {
        void onDeleteHoaDon(HoaDonDto hoaDon);
        void onOpenHDCT(HoaDonDto hoaDon);
    }

    public HoaDonAdapter(Context context, List<HoaDonDto> sanPhamList) {
        this.context = context;
        this.danhSachHoaDon = sanPhamList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hoa_don, parent, false);
            holder = new ViewHolder();
            holder.tvMaHoaDon = convertView.findViewById(R.id.tvMaHoaDon);
            holder.tvTenNhanVien = convertView.findViewById(R.id.tvTenNhanVien);
            holder.tvTenKhachHang = convertView.findViewById(R.id.tvTenKhachHang);
            holder.tvNgayLap = convertView.findViewById(R.id.tvNgayLap);
            holder.tvTongTien = convertView.findViewById(R.id.tvTongTien);
            holder.imgXoaHoaDon = convertView.findViewById(R.id.imgXoaHoaDon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HoaDonDto hoaDon = danhSachHoaDon.get(position);
        holder.tvMaHoaDon.setText(hoaDon.getMaHoaDon());
        holder.tvTenNhanVien.setText(hoaDon.getTenNhanVien());
        holder.tvTenKhachHang.setText(hoaDon.getTenKhachHang());
        holder.tvNgayLap.setText(hoaDon.getNgayLap());
        holder.tvTongTien.setText(String.valueOf(hoaDon.getTongTien()));
        holder.imgXoaHoaDon.setOnClickListener(v -> onHoaDonClickListener.onDeleteHoaDon(hoaDon));
        convertView.setOnClickListener(v -> onHoaDonClickListener.onOpenHDCT(hoaDon));

        return convertView;
    }

    public void setOnHoaDonClickListener(OnHoaDonClickListener onHoaDonClickListener) {
        this.onHoaDonClickListener = onHoaDonClickListener;
    }

    @Override
    public int getCount() {
        return danhSachHoaDon.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachHoaDon.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder {
        TextView tvMaHoaDon;
        TextView tvTenNhanVien;
        TextView tvTenKhachHang;
        TextView tvNgayLap;
        TextView tvTongTien;
        ImageView imgXoaHoaDon;
    }
}
