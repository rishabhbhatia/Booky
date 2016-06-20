package com.bookyard.booky.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bookyard.booky.R;
import com.bookyard.booky.adapters.CategoryBooksAdapter;
import com.bookyard.booky.models.Book;
import com.bookyard.booky.models.BookCategory;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rishabh Bhatia on 6/21/2016.
 */
public class CategoryBooksFragment extends BookyFragment {

    @BindView(R.id.backdrop_category_books)
    ImageView backdropCategoryBooks;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_category_books)
    CollapsingToolbarLayout collapsingCategoryBooks;
    @BindView(R.id.appbar_category_books)
    AppBarLayout appbarCategoryBooks;
    @BindView(R.id.rv_category_books)
    SuperRecyclerView rvCategoryBooks;
    @BindView(R.id.coordinator_category_books)
    CoordinatorLayout coordinatorCategoryBooks;

    public static CategoryBooksFragment newInstance() {
        return new CategoryBooksFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_books, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CategoryBooksAdapter adapter = new CategoryBooksAdapter(getActivity(), generateRandomBooks());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvCategoryBooks.setLayoutManager(gridLayoutManager);
        rvCategoryBooks.setAdapter(adapter);
    }

    @Override
    public String onBackPressed() {
        return super.onBackPressed();
    }

    private ArrayList<Book> generateRandomBooks()
    {
        ArrayList<Book> books = new ArrayList<>();

        for(int i=0; i<15;i++)
        {
            Book book = new Book();
            book.setName("Half Girlfriend");
            book.setPrice("Rs 125");
            book.setImageUrl(BookCategory.getDemoImage("60","60","https://pixabay.com/static/uploads/photo/2016" +
                    "/03/11/02/08/speed-1249610_960_720.jpg"));

            books.add(book);
        }

        return books;
    }
}
