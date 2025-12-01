package ph41045.fpoly.duanmau_ph41045.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import ph41045.fpoly.duanmau_ph41045.Common.GioHangItem;
import ph41045.fpoly.duanmau_ph41045.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class GioHangAdapter extends BaseAdapter {
    private final Context context;
    private final List<GioHangItem> gioHangItem;
    private OnSanPhamClickListener onSanPhamClickListener;
    NumberFormat currencyFormat;

    public GioHangAdapter(Context context, List<GioHangItem> gioHangItem) {
        this.context = context;
        this.gioHangItem = gioHangItem;

        // Định dạng tiền tệ cho Việt Nam (VND)
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gio_hang, parent, false);
            holder = new ViewHolder();
            holder.tvTenSanPham = convertView.findViewById(R.id.tvTenSanPham);
            holder.tvGiaSanPham = convertView.findViewById(R.id.tvGiaSanPham);
            holder.tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            holder.imgDeleteProduct = convertView.findViewById(R.id.imgXoaDanhMuc);
            holder.btnTang = convertView.findViewById(R.id.btnTang);
            holder.btnGiam = convertView.findViewById(R.id.btnGiam);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GioHangItem sp = gioHangItem.get(position);
        holder.tvTenSanPham.setText(sp.getSanPham().getTenSanPham());
        holder.tvGiaSanPham.setText(currencyFormat.format(sp.getSanPham().getGiaSanPham()));
        holder.tvSoLuong.setText(String.valueOf(sp.getSoLuong()));
        holder.imgDeleteProduct.setOnClickListener(v -> onSanPhamClickListener.onDeleteSanPham(sp));
        holder.btnTang.setOnClickListener(v -> onSanPhamClickListener.onIncrease(sp));
        holder.btnGiam.setOnClickListener(v -> onSanPhamClickListener.onDecrease(sp));

        return convertView;
    }

    public interface OnSanPhamClickListener {
        void onDeleteSanPham(GioHangItem gioHangItem);
        void onIncrease(GioHangItem gioHangItem);
        void onDecrease(GioHangItem gioHangItem);
    }

    public void setOnSanPhamClickListener(OnSanPhamClickListener onSanPhamClickListener) {
        this.onSanPhamClickListener = onSanPhamClickListener;
    }

    @Override
    public int getCount() {
        return gioHangItem.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView imgProduct;
        TextView tvTenSanPham;
        TextView tvGiaSanPham;
        TextView tvSoLuong;
        ImageView imgDeleteProduct;
        ImageButton btnTang, btnGiam;
    }
}
