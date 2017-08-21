package com.esp.korean.SubjectDetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.esp.korean.R;

import java.util.List;

public class SubjectDetailAdapter extends RecyclerView.Adapter<SubjectDetailAdapter.SubjectViewHolder>{

    private Context context;
    private List<SubjectDetailItem> itemList;

    public SubjectDetailAdapter(Context context, List<SubjectDetailItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_row, null);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        SubjectDetailItem item = itemList.get(position);
        holder.korean.setText(item.getKorean());
        holder.pronunciation.setText(item.getPronunciation());
        holder.vietnamese.setText(item.getVietnamese());
        if (item.isFavorite()) {
            holder.favoriteButton.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.favoriteButton.setImageResource(R.drawable.ic_nofavorite);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play audio
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        public TextView korean;
        public TextView pronunciation;
        public TextView vietnamese;
        public ImageButton favoriteButton;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            korean = (TextView) itemView.findViewById(R.id.korean);
            pronunciation = (TextView) itemView.findViewById(R.id.pronunciation);
            vietnamese = (TextView) itemView.findViewById(R.id.vietnamese);
            favoriteButton = (ImageButton) itemView.findViewById(R.id.favorite_button);
        }
    }
}
