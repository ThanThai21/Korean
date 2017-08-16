package com.esp.korean.Home.Category;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esp.korean.CustomView.CustomTextView;
import com.esp.korean.Model.CategoryItem;
import com.esp.korean.Model.Item;
import com.esp.korean.Model.Subject;
import com.esp.korean.R;

import java.util.ArrayList;
import java.util.List;

public class VerticalRVAdapter extends RecyclerView.Adapter<VerticalRVAdapter.ItemViewHolder>{

    private Context context;
    private List<CategoryItem> categoryList;
    private List<Subject> basicList;
    private List<Subject> communicationList;

    public VerticalRVAdapter(Context context) {
        this.context = context;
        categoryList = new ArrayList<>();
        basicList = new ArrayList<>();
        communicationList = new ArrayList<>();
        initBasicList();
        initCommunicationList();
        initCategoryList();
    }

    private void initCategoryList() {
        categoryList.add(new CategoryItem("Tiếng Hàn cơ bản", basicList));
        categoryList.add(new CategoryItem("Tiếng Hàn giao tiếp", communicationList));
    }

    private void initBasicList() {
        basicList.add(new Subject());
        basicList.add(new Subject());
        basicList.add(new Subject());
        basicList.add(new Subject());
        basicList.add(new Subject());
        basicList.add(new Subject());
    }

    private void initCommunicationList() {
        communicationList.add(new Subject());
        communicationList.add(new Subject());
        communicationList.add(new Subject());
        communicationList.add(new Subject());
        communicationList.add(new Subject());
        communicationList.add(new Subject());
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_row, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        CategoryItem categoryItem = categoryList.get(position);
        holder.title.setText(categoryItem.getTitle());
        holder.horizontalAdapter = new HorizontalRVAdapter(context, categoryItem.getSubjectList());
        holder.recyclerView.setAdapter(holder.horizontalAdapter);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public CustomTextView title;
        public RecyclerView recyclerView;
        public HorizontalRVAdapter horizontalAdapter;
        public LinearLayoutManager layoutManager;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (CustomTextView) itemView.findViewById(R.id.category_title);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.category_rv);
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
        }
    }
}
