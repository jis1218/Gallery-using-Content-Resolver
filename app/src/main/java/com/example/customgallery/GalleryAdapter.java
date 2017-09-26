package com.example.customgallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.customgallery.R.id.imageView;
import static com.example.customgallery.R.id.imageView2;

/**
 * Created by 정인섭 on 2017-09-26.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.Holder> {
    List<String> list;
    Context context;

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> data) {
        this.list = data;
        //데이터가 변경되었다고 알려줘야만 그린다.
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new Holder(view);
    }
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String path = list.get(position);
        Uri uri = Uri.parse(path);
        holder.setImageUri(uri);
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView imageView2;
        TextView tvDate;
        private Uri uri;

        public void setImageUri(Uri uri) {
            this.uri = uri;
            imageView2.setImageURI(uri);
        }

        public void setTvDate(TextView tvDate) {
            this.tvDate = tvDate;
        }

        public Holder(View itemView) {

            super(itemView);

            imageView2 = itemView.findViewById(R.id.imageView2);
            tvDate = itemView.findViewById(R.id.tvDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    //intent.putExtra("imagePath", uri.getPath());
                    intent.putExtra("imagePath", uri);
                    ((Activity) context).setResult(Activity.RESULT_OK, intent);
                    ((Activity) context).finish();
                }
            });



        }
    }
}
