package com.esp.korean.Home.Category;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esp.korean.R;

public class CategoryFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private VerticalRVAdapter verticalAdapter;
    private LinearLayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.vertical_recycler_view);
        verticalAdapter = new VerticalRVAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(verticalAdapter);
        recyclerView.setLayoutManager(layoutManager);
        return rootView;
    }
}
