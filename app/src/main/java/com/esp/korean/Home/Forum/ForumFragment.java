package com.esp.korean.Home.Forum;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.esp.korean.R;


public class ForumFragment extends Fragment {


    private View rootView;
    private RecyclerView recyclerView;
    private ForumAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_forum, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.forum_recycler_view);
        adapter = new ForumAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        fixedData();
        return rootView;
    }

    private void fixedData() {
        adapter.add(new ForumItem("Cho em hỏi..."));
        adapter.add(new ForumItem("Câu này dịch thế nào ạ..."));
    }

}
