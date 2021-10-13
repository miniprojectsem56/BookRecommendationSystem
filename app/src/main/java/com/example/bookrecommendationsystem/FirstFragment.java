package com.example.bookrecommendationsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.adapter.RecyclerViewAdapter;
import com.example.bookrecommendationsystem.databinding.FragmentFirstBinding;

import java.util.ArrayList;


public class FirstFragment extends Fragment {

    FragmentFirstBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    public ArrayList<Book> bookArrayList;
    private ArrayAdapter<String> arrayAdapter;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater,container,false);

        recyclerView= binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bookArrayList=new ArrayList<Book>();
        for(int i=0;i<10;i++) {
            bookArrayList.add(new Book("2 States","Cheatan Bhagat"));
            bookArrayList.add(new Book("Gravity","Brahmagupta"));
        }
        recyclerViewAdapter=new RecyclerViewAdapter(getActivity(),bookArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        return binding.getRoot();
    }
}