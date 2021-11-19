package com.example.bookrecommendationsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.adapter.RecyclerViewAdapter;
import com.example.bookrecommendationsystem.databinding.FragmentSecondBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SecondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    public ArrayList <Book>bookArrayList;
    private ArrayAdapter<String>arrayAdapter;

    FragmentSecondBinding binding;

    public SecondFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/

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

    public Integer[] giveRandomNumbers(int a,int questionsRequired){
        Integer []ans=new Integer[questionsRequired];
        int total=a;
        if(questionsRequired>total){
            return ans;
        }
        for(int i=0;i<questionsRequired;i++){
            while(true){
                boolean isNew=true;
                ans[i]=(int)(Math.random()*total);
                for(int j=i-1;j>=0;j--){
                    if(ans[i]==ans[j]){
                        isNew=false;
                        break;
                    }
                }
                if(isNew){
                    break;
                }
            }
        }
        return  ans;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentSecondBinding.inflate(inflater,container,false);

        recyclerView= binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String [][] books_displaying = fileRead("bookColumns.txt");

        bookArrayList=new ArrayList<Book>();

//        for(int i=0;i<10;i++) {
//            bookArrayList.add(new Book("2 States","Cheatan Bhagat"));
//            bookArrayList.add(new Book("Gravity","Brahmagupta"));
//        }

        Integer [] indices = giveRandomNumbers(books_displaying.length,15);

        for(int i=0;i<15;i++)
        {
            int index=indices[i];
            bookArrayList.add(new Book(books_displaying[index][1],books_displaying[index][2]));
        }

        recyclerViewAdapter=new RecyclerViewAdapter(getActivity(),bookArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
          return binding.getRoot();
    }
}