package com.maro.newsly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maro.newsly.data.News;
import com.maro.newsly.data.Response;
import com.maro.newsly.data.Results;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private OnNewsListener mOnNewsListener;
    private static List<Results> resultsList;
    Context context;
    private Response response;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        OnNewsListener onNewsListener;
        TextView sectionName, title, type;
        public ViewHolder(@NonNull View itemView, OnNewsListener onNewsListener) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.sectionName);
            title = itemView.findViewById(R.id.webTitle);
            this.onNewsListener = onNewsListener;
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position = getAdapterPosition();
                   onNewsListener.onNewsClick(resultsList.get(position));
               }
           });
        }


        public void setOnNewsListener(OnNewsListener listener){
            this.onNewsListener = listener;
        }

//        @Override
//        public void onClick(View v) {
//            onNewsListener.onNewsClick(this.getLayoutPosition());
//        }
    }
    public void setResultsList (List<Results> resultsList){
        this.resultsList = resultsList;
    }

    public RecyclerAdapter(List<Results> resultsList, Context context) {
        this.resultsList = resultsList;
        this.context = context;
//        this.mOnNewsListener = mOnNewsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView, mOnNewsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Results results = resultsList.get(position);
        holder.sectionName.setText(results.getSectionName());
        holder.title.setText(results.getWebTitle());
    }

    public interface OnNewsListener {
        void onNewsClick(Results results);
    }

    public void setmOnNewsListener(OnNewsListener listener){
        mOnNewsListener = listener;
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

}
