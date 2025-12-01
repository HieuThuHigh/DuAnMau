package ph41045.fpoly.duanmau_ph41045.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ph41045.fpoly.duanmau_ph41045.Dto.HoaDonChiTietDto;
import ph41045.fpoly.duanmau_ph41045.R;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class HDCTAdapter extends BaseAdapter {
    private final Context context;
    private final List<HoaDonChiTietDto> gioHangItem;
    NumberFormat currencyFormat;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hoa_don_chi_tiet_item, parent, false);
            holder = new ViewHolder();
            holder.tvTenSanPham = convertView.findViewById(R.id.tvTenSanPham);
            holder.tvGiaSanPham = convertView.findViewById(R.id.tvGiaSanPham);
            holder.tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HoaDonChiTietDto hdct = gioHangItem.get(position);
        holder.tvTenSanPham.setText(hdct.getTenSanPham());
        holder.tvGiaSanPham.setText(currencyFormat.format(hdct.getDonGia()));
        holder.tvSoLuong.setText("Số lượng: " + hdct.getSoLuong());

        return convertView;
    }

    public HDCTAdapter(Context context, List<HoaDonChiTietDto> gioHangItem) {
        this.context = context;
        this.gioHangItem = gioHangItem;

        // Định dạng tiền tệ cho Việt Nam (VND)
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
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
        TextView tvTenSanPham;
        TextView tvGiaSanPham;
        TextView tvSoLuong;
    }
}
