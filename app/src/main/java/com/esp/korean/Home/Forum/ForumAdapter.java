package com.esp.korean.Home.Forum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esp.korean.R;

import java.util.ArrayList;
import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumViewHolder>{

    private Context context;
    private List<ForumItem> itemList;

    public ForumAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
    }

    public void add(ForumItem item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ForumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.forum_row, parent, false);
        return new ForumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForumViewHolder holder, int position) {
        ForumItem item = itemList.get(position);
        holder.subjectName.setText(item.getSubjectName());
        holder.like.setText(item.getLike() + "");
        holder.comment.setText(item.getComment() + "");
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder {

        TextView subjectName;
        TextView like;
        TextView comment;
        public ForumViewHolder(View itemView) {
            super(itemView);
            subjectName = (TextView) itemView.findViewById(R.id.subject_name);
            like = (TextView) itemView.findViewById(R.id.like);
            comment = (TextView) itemView.findViewById(R.id.comment);
        }
    }
}
