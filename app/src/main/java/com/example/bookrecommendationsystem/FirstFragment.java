package com.example.bookrecommendationsystem;

import android.content.res.AssetManager;
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
import android.widget.Toast;

import com.example.adapter.RecyclerViewAdapter;
import com.example.bookrecommendationsystem.databinding.FragmentFirstBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


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

    String[][] fileRead(String fileName){
        File file=new File(fileName);
        int lineCount=0;
        try {
            //BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(fileName)));
            String st;
            while((st=br.readLine())!=null){
                //System.out.println(st);
                lineCount++;
            }
        }catch (Exception e){
            System.out.println("not possible");
        }
        //Toast.makeText(getApplicationContext().getApplicationContext(), lineCount+"",Toast.LENGTH_LONG).show();
        String[][]allData=new String[lineCount][];
        int lineNumber=0;
        try {
            //BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(fileName)));
            String st;
            while((st=br.readLine())!=null){
                Log.d("checking",st);
                allData[lineNumber]=st.split(">");
                lineNumber++;
            }
        }catch (Exception e){
            System.out.println("not possible");
        }
        return  allData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater,container,false);

        recyclerView= binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String [][] books_displaying = fileRead("bookColumns.txt");

        bookArrayList=new ArrayList<Book>();

//        for(int i=0;i<10;i++) {
//            bookArrayList.add(new Book("2 States","Cheatan Bhagat"));
//            bookArrayList.add(new Book("Gravity","Brahmagupta"));
//        }

        for(int i=0;i<20;i++)
        {
            bookArrayList.add(new Book(books_displaying[i][1],books_displaying[i][2]));
        }

        recyclerViewAdapter=new RecyclerViewAdapter(getActivity(),bookArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        return binding.getRoot();
    }
}