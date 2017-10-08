package com.knz21.zipcode.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.knz21.zipcode.R;
import com.knz21.zipcode.store.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressListAdapter extends BaseAdapter {
    private List<Address> mAddresses = new ArrayList<>();

    @Override
    public int getCount() {
        return mAddresses.size();
    }

    @Override
    public Object getItem(int position) {
        return mAddresses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AlbumViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_address, null);
            holder = new AlbumViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (AlbumViewHolder) convertView.getTag();
        }
        holder.addressText.setText(getItem(position).toString());
        return convertView;
    }

    class AlbumViewHolder {
        @BindView(R.id.address_text)
        TextView addressText;

        AlbumViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void update(List<Address> addresses) {
        mAddresses = addresses;
        notifyDataSetChanged();
    }

    public void clear() {
        mAddresses = new ArrayList<>();
        notifyDataSetChanged();
    }
}
