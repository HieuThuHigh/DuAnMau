package ph41045.fpoly.duanmau_ph41045.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.model.TopSanPham;

import java.util.List;

public class TopSanPhamAdapter extends BaseAdapter {
    private final List<TopSanPham> list;
    private final LayoutInflater inflater;

    public TopSanPhamAdapter(Context context, List<TopSanPham> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_top_san_pham, parent, false);
            holder = new ViewHolder();
            holder.anhSanPham = convertView.findViewById(R.id.img_anh_san_pham);
            holder.tenSanPham = convertView.findViewById(R.id.tv_ten_san_pham);
            holder.tongSoLuong = convertView.findViewById(R.id.tv_tong_so_luong);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TopSanPham thongKeSanPham = list.get(position);

        holder.tenSanPham.setText(thongKeSanPham.getTenSanPham());
        holder.tongSoLuong.setText("Tổng số lượng: " + thongKeSanPham.getTongSoLuong());

        return convertView;
    }

    private static class ViewHolder {
        ImageView anhSanPham;
        TextView tenSanPham;
        TextView tongSoLuong;
    }
}
