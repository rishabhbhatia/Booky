package com.bookyard.booky.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bookyard.booky.R;
import com.bookyard.booky.models.Book;
import com.bookyard.booky.utils.BookyUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rishabh Bhatia on 6/17/2016.
 */
public class MainLandingBookCoverAdapter extends RecyclerView.Adapter<MainLandingBookCoverAdapter.MainLandingBookCoverHolder> {

    private ArrayList<Book> books;
    private Context context;

    public MainLandingBookCoverAdapter(Context context, ArrayList<Book> books) {
        this.books = books;
        this.context = context;
    }

    @Override
    public MainLandingBookCoverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_book_cover, parent, false);

        return new MainLandingBookCoverHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MainLandingBookCoverHolder holder, final int position) {

        try {
            holder.clear();

            final Book book = books.get(position);

            DisplayMetrics metrics = BookyUtils.getScreenResolution(context);

            int width = metrics.widthPixels;
            int height = metrics.heightPixels;

            holder.ivItemBookCover.requestLayout();
            holder.ivItemBookCover.getLayoutParams().height = height / 4;
            holder.ivItemBookCover.getLayoutParams().width = width / 3;

            if (book.getImageUrl() != null) {
                Glide
                        .with(context)
                        .load(book.getImageUrl())
                        .centerCrop()
                        .crossFade()
                        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                        .into(holder.ivItemBookCover);

            }

            if (book.getName() != null) {
                holder.tvItemBookName.setText(book.getName());
            } else {
                holder.tvItemBookName.setText("");
            }

            if (book.getPrice() != null) {
                holder.tvItemBookPrice.setText(book.getPrice());
            } else {
                holder.tvItemBookPrice.setText("");
            }

            holder.rlItemBookCoverHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BookyUtils.showToast(context, "Hello to book " + book.getName(), Toast.LENGTH_LONG);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class MainLandingBookCoverHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_book_cover)
        ImageView ivItemBookCover;
        @BindView(R.id.tv_item_book_name)
        TextView tvItemBookName;
        @BindView(R.id.tv_item_book_price)
        TextView tvItemBookPrice;
        @BindView(R.id.rl_item_book_cover_holder)
        RelativeLayout rlItemBookCoverHolder;

        public MainLandingBookCoverHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void clear() {
            ivItemBookCover.setImageDrawable(null);
            tvItemBookName.setText("");
            tvItemBookPrice.setText("");
        }
    }

}
