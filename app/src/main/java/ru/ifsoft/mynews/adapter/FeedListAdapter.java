package ru.ifsoft.mynews.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;

import ru.ifsoft.mynews.R;
import ru.ifsoft.mynews.app.App;
import ru.ifsoft.mynews.model.Item;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.ViewHolder> {

    private Context ctx;
    private List<Item> itemList;

    ImageLoader imageLoader = App.getInstance().getImageLoader();

    public class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView mTitle;
        public TextView mTime;
        public ImageView mImage;
        public MaterialRippleLayout mParent;

        public ViewHolder(View v) {

            super(v);

            mTitle = (TextView) v.findViewById(R.id.title);
            mTime = (TextView) v.findViewById(R.id.time);
            mImage = (ImageView) v.findViewById(R.id.image);
            mParent = (MaterialRippleLayout) v.findViewById(R.id.parent);
        }
    }

    public FeedListAdapter(Activity activity, List<Item> itemList) {

        if (imageLoader == null) {

            imageLoader = App.getInstance().getImageLoader();
        }

        this.ctx = activity;

        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Item c = itemList.get(position);

        holder.mTitle.setText(c.getTitle());

        holder.mTime.setText(c.getDate());

        imageLoader.get(c.getImgUrl(), ImageLoader.getImageListener(holder.mImage, R.drawable.img_loading, R.drawable.img_loading));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public long getItemId(int position) {
        return itemList.get(position).getId();
    }
}