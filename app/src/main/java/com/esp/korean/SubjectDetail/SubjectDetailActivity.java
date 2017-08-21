package com.esp.korean.SubjectDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.esp.korean.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageButton toolbarBackButton;
    private TextView toolbarTitle;
    private ImageView headerImageView;
    private RecyclerView recyclerView;
    private SubjectDetailAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<SubjectDetailItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarBackButton = (ImageButton) toolbar.findViewById(R.id.toolbar_back);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarBackButton.setOnClickListener(this);
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.vertical_recycler_view);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        itemList = new ArrayList<>();
        adapter = new SubjectDetailAdapter(this, itemList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.toolbar_back) {
            finish();
        }
    }
}
