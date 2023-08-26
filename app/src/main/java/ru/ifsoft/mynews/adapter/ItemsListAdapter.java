package ru.ifsoft.mynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.ifsoft.mynews.R;
import ru.ifsoft.mynews.model.Item;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, date;
        public ImageView thumbnail;

        public MyViewHolder(View view) {

            super(view);

            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public ItemsListAdapter(Context mContext, List<Item> itemList) {

        this.mContext = mContext;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Item item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.date.setText(item.getDate());

        // loading album cover using Glide library
        Glide.with(mContext).load(item.getImgUrl()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }
}