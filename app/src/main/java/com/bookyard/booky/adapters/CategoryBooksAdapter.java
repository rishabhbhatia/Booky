package com.bookyard.booky.adapters;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * Created by Rishabh Bhatia on 6/20/2016.
 */
public class CategoryBooksAdapter extends RecyclerView.Adapter<CategoryBooksAdapter.CategoryBooksHolder> {


    private ArrayList<Book> books;
    private Context context;

    public CategoryBooksAdapter(Context context, ArrayList<Book> books) {
        this.books = books;
        this.context = context;
    }

    @Override
    public CategoryBooksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_category_book, parent, false);

        return new CategoryBooksHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryBooksHolder holder, final int position) {

        try {
            holder.clear();

            final Book book = books.get(position);

            if (book.getImageUrl() != null) {
                Glide
                        .with(context)
                        .load(book.getImageUrl())
                        .centerCrop()
                        .crossFade()
                        .into(holder.ivItemCategoryBook);

            }

            if (book.getName() != null) {
                holder.tvItemCategoryBookName.setText(book.getName());
            } else {
                holder.tvItemCategoryBookName.setText("");
            }

            if (book.getPrice() != null) {
                holder.tvItemCategoryBookPrice.setText(book.getPrice());
            } else {
                holder.tvItemCategoryBookPrice.setText("");
            }

            holder.prlItemCategoryBook.setOnClickListener(new View.OnClickListener() {
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

    static class CategoryBooksHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_category_book)
        ImageView ivItemCategoryBook;
        @BindView(R.id.ll_item_category_book_image_holder)
        LinearLayout llItemCategoryBookImageHolder;
        @BindView(R.id.tv_item_category_book_name)
        TextView tvItemCategoryBookName;
        @BindView(R.id.tv_item_category_book_price)
        TextView tvItemCategoryBookPrice;
        @BindView(R.id.prl_item_category_book)
        PercentRelativeLayout prlItemCategoryBook;

        public CategoryBooksHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void clear() {
            ivItemCategoryBook.setImageDrawable(null);
            tvItemCategoryBookName.setText("");
            tvItemCategoryBookPrice.setText("");
        }
    }

}
