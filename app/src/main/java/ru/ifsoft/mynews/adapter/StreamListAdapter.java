package ru.ifsoft.mynews.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import github.ankushsachdeva.emojicon.EmojiconTextView;
import ru.ifsoft.mynews.CategoryActivity;
import ru.ifsoft.mynews.R;
import ru.ifsoft.mynews.app.App;
import ru.ifsoft.mynews.constants.Constants;
import ru.ifsoft.mynews.model.Item;

public class StreamListAdapter extends BaseAdapter implements Constants {

	private Activity activity;
	private LayoutInflater inflater;
	private List<Item> itemsList;


    ImageLoader imageLoader = App.getInstance().getImageLoader();

	public StreamListAdapter(Activity activity, List<Item> itemsList) {

		this.activity = activity;
		this.itemsList = itemsList;
	}

	@Override
	public int getCount() {

		return itemsList.size();
	}

	@Override
	public Object getItem(int location) {

		return itemsList.get(location);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}
	
	static class ViewHolder {

        public ImageView itemImg;
        public EmojiconTextView itemTitle;
        public TextView itemDate;
        public TextView itemCategory;
        public LinearLayout itemImgSeparatorLine;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder = null;

		if (inflater == null) {

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

		if (convertView == null) {
			
			convertView = inflater.inflate(R.layout.stream_list_row, null);
			
			viewHolder = new ViewHolder();

            viewHolder.itemImg = (ImageView) convertView.findViewById(R.id.itemImg);
            viewHolder.itemCategory = (TextView) convertView.findViewById(R.id.itemCategory);
            viewHolder.itemTitle = (EmojiconTextView) convertView.findViewById(R.id.itemTitle);
            viewHolder.itemDate = (TextView) convertView.findViewById(R.id.itemDate);
            viewHolder.itemImgSeparatorLine = (LinearLayout) convertView.findViewById(R.id.itemImgSeparatorLine);

            convertView.setTag(viewHolder);

		} else {
			
			viewHolder = (ViewHolder) convertView.getTag();
		}

        if (imageLoader == null) {

            imageLoader = App.getInstance().getImageLoader();
        }

        viewHolder.itemImg.setTag(position);
        viewHolder.itemCategory.setTag(position);
        viewHolder.itemTitle.setTag(position);
        viewHolder.itemDate.setTag(position);
        viewHolder.itemImgSeparatorLine.setTag(position);
		
		final Item item = itemsList.get(position);

        viewHolder.itemCategory.setText(item.getCategoryTitle());
        viewHolder.itemDate.setText(item.getDate());

        viewHolder.itemCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, CategoryActivity.class);
                intent.putExtra("categoryId", item.getCategoryId());
                intent.putExtra("title", item.getCategoryTitle());
                activity.startActivity(intent);
            }
        });


        if (item.getTitle().length() > 0) {

            viewHolder.itemTitle.setText(item.getTitle());

            viewHolder.itemTitle.setVisibility(View.VISIBLE);

        } else {

            viewHolder.itemTitle.setVisibility(View.GONE);
        }

        if (item.getImgUrl().length() > 0) {

            imageLoader.get(item.getImgUrl(), ImageLoader.getImageListener(viewHolder.itemImg, R.drawable.img_loading, R.drawable.img_loading));
            viewHolder.itemImg.setVisibility(View.VISIBLE);
            viewHolder.itemImgSeparatorLine.setVisibility(View.VISIBLE);

        } else {

            viewHolder.itemImg.setVisibility(View.GONE);
            viewHolder.itemImgSeparatorLine.setVisibility(View.GONE);
        }

		return convertView;
	}
}