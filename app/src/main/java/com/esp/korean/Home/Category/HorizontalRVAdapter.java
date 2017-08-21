package com.esp.korean.Home.Category;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esp.korean.Model.Subject;
import com.esp.korean.R;
import com.esp.korean.SubjectDetail.SubjectDetailActivity;

import java.util.List;

public class HorizontalRVAdapter extends RecyclerView.Adapter<HorizontalRVAdapter.ItemViewHolder> {

    private Context context;
    private List<Subject> subjectList;

    public HorizontalRVAdapter(Context context, List<Subject> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubjectDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView title;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            title = (TextView) itemView.findViewById(R.id.item_title);
        }
    }
}
