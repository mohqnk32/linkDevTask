package com.org.mohammedabdullah3296.linkdevtask.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.org.mohammedabdullah3296.linkdevtask.R;
import com.org.mohammedabdullah3296.linkdevtask.interfaces.ArticleClick;
import com.org.mohammedabdullah3296.linkdevtask.model.Article;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    public List<Article> data = Collections.emptyList();
    Article current;
    int currentPos = 0;
    private ArticleClick lOnClickListener;

    public ArticleAdapter(ArticleClick listener) {
        lOnClickListener = listener;
    }

    public void setArticleData(List<Article> recipesIn, Context context) {
        data = recipesIn;
        mContext = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.article_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        MyHolder viewHolder = new MyHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyHolder myHolder = (MyHolder) holder;
        current = data.get(position);
        myHolder.title.setText(current.getTitle());
        Picasso.get().load(current.getUrlToImage()).placeholder(R.drawable.placeholder).into(myHolder.image);
        myHolder.publicher.setText(String.format("By %s", current.getAuthor()));
       String newDateFormate = dateFormate(current.getPublishedAt());
      //  String newDateFormate = current.getPublishedAt().substring(0 , current.getPublishedAt().indexOf('T')) ;
        myHolder.date.setText(newDateFormate);
     //  current.setPublishedAt(newDateFormate);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, publicher, date;
        ImageView image;

        public MyHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.media_image);
            title = (TextView) itemView.findViewById(R.id.primary_text);
            publicher = (TextView) itemView.findViewById(R.id.sub_text);
            date = (TextView) itemView.findViewById(R.id.action_button_1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            lOnClickListener.onListItemClick(clickedPosition);
        }
    }

    public void clear() {
        final int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }

    public String dateFormate(String date1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        String mmm = null ;
        try {
          Date  date = sdf.parse(date1);
         mmm   = new SimpleDateFormat("MMM dd, yyyy").format(date) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mmm;
    }
}
