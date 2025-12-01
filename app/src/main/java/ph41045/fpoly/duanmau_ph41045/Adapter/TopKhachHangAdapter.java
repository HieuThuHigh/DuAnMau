package ph41045.fpoly.duanmau_ph41045.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ph41045.fpoly.duanmau_ph41045.Model.TopKhachHang;
import ph41045.fpoly.duanmau_ph41045.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TopKhachHangAdapter extends BaseAdapter {
    private final List<TopKhachHang> topKhachHangList;
    private final LayoutInflater inflater;


    public TopKhachHangAdapter(Context context, List<TopKhachHang> topKhachHangList) {
        this.topKhachHangList = topKhachHangList;
//        this.inflater = inflater;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return topKhachHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return topKhachHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_top_khach_hang, parent, false);
            holder = new ViewHolder();
            holder.imgCustomer = convertView.findViewById(R.id.imgCustomer);
            holder.txtCustomerName = convertView.findViewById(R.id.tvCustomerName);
            holder.txtTotalSpent = convertView.findViewById(R.id.tvTotalSpent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TopKhachHang topKhachHang = topKhachHangList.get(position);
        holder.txtCustomerName.setText(topKhachHang.getTenKhachHang());

        // Định dạng tiền tệ
        NumberFormat currencyFormat = NumberFormat.getInstance(Locale.US);
        holder.txtCustomerName.setText(topKhachHang.getTenKhachHang());
        holder.txtTotalSpent.setText("Tổng chi tiêu: " + currencyFormat.format(topKhachHang.getTongChiTieu()) + "VNĐ");
        return convertView;
    }

    private static class ViewHolder {
        ImageView imgCustomer;
        TextView txtCustomerName;
        TextView txtTotalSpent;
    }
}
