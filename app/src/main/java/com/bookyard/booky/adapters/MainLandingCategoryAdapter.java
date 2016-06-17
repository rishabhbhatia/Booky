package com.bookyard.booky.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bookyard.booky.R;
import com.bookyard.booky.models.BookCategory;
import com.bookyard.booky.utils.BookyUtils;
import com.bookyard.booky.utils.Const;
import com.bumptech.glide.Glide;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rishabh Bhatia on 6/17/2016.
 */
public class MainLandingCategoryAdapter extends RecyclerView.Adapter<MainLandingCategoryAdapter.MainLandingCategoryHolder> {

    private ArrayList<BookCategory> categories;
    private Context context;

    public MainLandingCategoryAdapter(Context context, ArrayList<BookCategory> categories) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public MainLandingCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_landing_books_category, parent, false);

        return new MainLandingCategoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MainLandingCategoryHolder holder, final int position) {

        try {
            holder.clear();

            if(position != categories.size()-1)
            {
                holder.divider.setVisibility(View.VISIBLE);
            }

            final BookCategory category = categories.get(position);

            DisplayMetrics metrics = BookyUtils.getScreenResolution(context);

            int width = metrics.widthPixels;
            int height = metrics.heightPixels;

            holder.ivItemLandingCategory.requestLayout();
            holder.ivItemLandingCategory.getLayoutParams().height = height/10;
            holder.ivItemLandingCategory.getLayoutParams().width = width/6;

            holder.rvItemLandingCategorySampleBooks.requestLayout();
            holder.rvItemLandingCategorySampleBooks.getLayoutParams().height = height/3;
            holder.rvItemLandingCategorySampleBooks.getLayoutParams().width = width;

            if (category.getImageUrl() != null)
            {
                Glide
                        .with(context)
                        .load(category.getImageUrl())
                        .centerCrop()
                        .crossFade()
                        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                        .into(holder.ivItemLandingCategory);

            }

            if (category.getName() != null) {
                holder.tvItemLandingCategoryName.setText(category.getName());
            } else {
                holder.tvItemLandingCategoryName.setText("");
            }

            if(category.getPopularAuthors() != null)
            {
                holder.tvItemLandingCategoryPopularAuthors.setText(category.getPopularAuthors());
            }else
            {
                holder.tvItemLandingCategoryPopularAuthors.setText("");
            }

            if(category.getBooks() != null)
            {
                MainLandingBookCoverAdapter adapter = new MainLandingBookCoverAdapter(context, category.getBooks());

                LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false);
                holder.rvItemLandingCategorySampleBooks.setLayoutManager(layoutManager);

                holder.rvItemLandingCategorySampleBooks.setAdapter(adapter);

            }

            holder.rlItemLandingCategoryAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BookyUtils.showToast(context, "Hello to category "+category.getName(), Toast.LENGTH_LONG);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class MainLandingCategoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_landing_category)
        ImageView ivItemLandingCategory;
        @BindView(R.id.tv_item_landing_category_name)
        TextView tvItemLandingCategoryName;
        @BindView(R.id.tv_item_landing_category_popular_authors)
        TextView tvItemLandingCategoryPopularAuthors;
        @BindView(R.id.rl_item_landing_category_details)
        RelativeLayout rlItemLandingCategoryDetails;
        @BindView(R.id.iv_item_landing_category_action)
        ImageView ivItemLandingCategoryAction;
        @BindView(R.id.rl_item_landing_category_action)
        RelativeLayout rlItemLandingCategoryAction;
        @BindView(R.id.rv_item_landing_category_sample_books)
        SuperRecyclerView rvItemLandingCategorySampleBooks;
        @BindView(R.id.ll_item_landing_category_holder)
        LinearLayout llItemLandingCategoryHolder;
        @BindView(R.id.view_item_landing_divider)
        View divider;

        public MainLandingCategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void clear()
        {
            divider.setVisibility(View.GONE);
            tvItemLandingCategoryName.setText("");
            tvItemLandingCategoryPopularAuthors.setText("");
            ivItemLandingCategory.setImageDrawable(null);
        }
    }

}
