package ph41045.fpoly.duanmau_ph41045.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.model.DanhMuc;

import java.util.List;

public class DanhMucAdapter extends BaseAdapter {
    private final Context context;
    private final List<DanhMuc> danhSachDanhMuc;
    private OnDanhMucClickListener onDanhMucClickListener;

    public interface OnDanhMucClickListener {
        void onEditDanhMuc(DanhMuc danhMuc);
        void onDeleteDanhMuc(DanhMuc danhMuc);
    }

    public DanhMucAdapter(Context context, List<DanhMuc> danhSachDanhMuc) {
        this.context = context;
        this.danhSachDanhMuc = danhSachDanhMuc;
    }

    public void setOnDanhMucClickListener(OnDanhMucClickListener onDanhMucClickListener) {
        this.onDanhMucClickListener = onDanhMucClickListener;
    }

    @Override
    public int getCount() {
        return danhSachDanhMuc.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachDanhMuc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_danh_muc, parent, false);
            holder = new ViewHolder();
            holder.tvTenDanhMuc = convertView.findViewById(R.id.tvGiaSanPham);
            holder.tvMaDanhMuc = convertView.findViewById(R.id.tvTenSanPham);
            holder.imgSuaDanhMuc = convertView.findViewById(R.id.imgSuaDanhMuc);
            holder.imgXoaDanhMuc = convertView.findViewById(R.id.imgXoaDanhMuc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DanhMuc sp = danhSachDanhMuc.get(position);
        holder.tvTenDanhMuc.setText(sp.getTenDanhMuc());
        holder.tvMaDanhMuc.setText(sp.getMaDanhMuc());
        holder.imgSuaDanhMuc.setOnClickListener(v -> onDanhMucClickListener.onEditDanhMuc(sp));
        holder.imgXoaDanhMuc.setOnClickListener(v -> onDanhMucClickListener.onDeleteDanhMuc(sp));

        return convertView;
    }

    static class ViewHolder {
        ImageView imgProduct;
        TextView tvTenDanhMuc;
        TextView tvMaDanhMuc;
        ImageView imgSuaDanhMuc;
        ImageView imgXoaDanhMuc;
    }
}
