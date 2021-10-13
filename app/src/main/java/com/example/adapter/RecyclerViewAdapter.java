package com.example.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookrecommendationsystem.Book;
import com.example.bookrecommendationsystem.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Book>bookList;

    public RecyclerViewAdapter(Context context,List<Book>bookList) {
        this.context = context;
        this.bookList=bookList;
    }

    //Where to get the single card as ViewHolder object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    //What will happen after we create the viewHolder Object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Book book=bookList.get(position);
        holder.bookName.setText(book.getName());
        holder.bookAuthor.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView bookName;
        public  TextView bookAuthor;
        public ImageView iconButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            bookName=itemView.findViewById(R.id.BookName);
            bookAuthor=itemView.findViewById(R.id.BookAuthor);
            iconButton=itemView.findViewById(R.id.BookImage);

            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}