package com.bookyard.booky.ui.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookyard.booky.R;
import com.bookyard.booky.adapters.MainLandingCategoryAdapter;
import com.bookyard.booky.interfaces.RecyclerItemClickListener;
import com.bookyard.booky.models.Book;
import com.bookyard.booky.models.BookCategory;
import com.bookyard.booky.ui.fragments.CategoryBooksFragment;
import com.bookyard.booky.utils.Const;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.razorpay.Checkout;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rishabh Bhatia on 6/PADDING_MEDIUM/2016.
 */
public class MainLandingActivity extends BookyActivity {

    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_toolbar_label)
    TextView tvToolbarLabel;
    @BindView(R.id.iv_toolbar_search)
    SearchView ivToolbarSearch;
    @BindView(R.id.iv_toolbar_refresh)
    ImageView ivToolbarRefresh;
    @BindView(R.id.rl_toolbar_holder)
    RelativeLayout rlToolbarHolder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_category_left)
    TextView tvCategoryLeft;
    @BindView(R.id.tv_category_right)
    TextView tvCategoryRight;
    @BindView(R.id.rv_main_landing)
    SuperRecyclerView rvMainLanding;

    private final int PADDING_LARGE = 25;
    private final int PADDING_MEDIUM = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);
        ButterKnife.bind(this);

        populateDummyCategories();
    }

    private void populateDummyCategories()
    {
        ArrayList<String> categoryNames = new ArrayList<>();
        categoryNames.add("Thriller");
        categoryNames.add("Drama");
        categoryNames.add("Romance");
        categoryNames.add("Misery");
        categoryNames.add("Comic");
        categoryNames.add("Animes");
        categoryNames.add("Biography");
        categoryNames.add("Discovery");
        categoryNames.add("Travel");
        categoryNames.add("Fashion");
        categoryNames.add("Magazines");

        ArrayList<BookCategory> bookCategories = new ArrayList<>();

        for(int i =0; i<categoryNames.size(); i++)
        {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setName(categoryNames.get(i));
            bookCategory.setPopularAuthors("Daniel Judson, James Patterson, Marc McCluskey");
            bookCategory.setImageUrl(BookCategory.getDemoImage("60","60","https://pixabay.com/static/uploads/photo/2016" +
                    "/03/11/02/08/speed-1249610_960_720.jpg"));
            bookCategory.setBooks(generateRandomBooks());

            bookCategories.add(bookCategory);

        }

        MainLandingCategoryAdapter adapter = new MainLandingCategoryAdapter(MainLandingActivity.this, bookCategories);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainLandingActivity.this);
        rvMainLanding.setLayoutManager(layoutManager);

        rvMainLanding.setAdapter(adapter);

        rvMainLanding.addOnItemTouchListener(new RecyclerItemClickListener(MainLandingActivity.this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switchFragment(MainLandingActivity.this.getSupportFragmentManager(),
                                CategoryBooksFragment.newInstance(), Const.CATEGORY_BOOKS_FRAG_TAG,
                                Const.FRAGMENT_SWITCH_REPLACE);
                    }
                }));

//        startPayment();
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

    @Override
    protected void onResume() {
        super.onResume();
        tvCategoryLeft.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                PADDING_LARGE, PADDING_MEDIUM);
        tvCategoryRight.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                PADDING_LARGE, PADDING_MEDIUM);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.tv_category_left, R.id.tv_category_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_category_left:
                tvCategoryLeft.setBackgroundResource(R.drawable.category_left_black_bg);
                tvCategoryRight.setBackgroundResource(R.drawable.category_right_white_bg);
                tvCategoryLeft.setTextColor(Color.parseColor("#FFFFFF"));
                tvCategoryRight.setTextColor(Color.parseColor("#000000"));
                tvCategoryLeft.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                        PADDING_LARGE, PADDING_MEDIUM);
                tvCategoryRight.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                        PADDING_LARGE, PADDING_MEDIUM);
                break;
            case R.id.tv_category_right:
                tvCategoryRight.setBackgroundResource(R.drawable.category_right_black_bg);
                tvCategoryLeft.setBackgroundResource(R.drawable.category_left_white_bg);
                tvCategoryRight.setTextColor(Color.parseColor("#FFFFFF"));
                tvCategoryLeft.setTextColor(Color.parseColor("#000000"));
                tvCategoryLeft.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                        PADDING_LARGE, PADDING_MEDIUM);
                tvCategoryRight.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                        PADDING_LARGE, PADDING_MEDIUM);
                break;
        }
    }

    public void startPayment()
    {
        /**
         * Replace with your public key
         */
        final String public_key = Const.RAZORPAY_KEY_ID;

        /**
         * You need to pass current activity in order to let razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();
        co.setPublicKey(public_key);

        try{
            JSONObject options = new JSONObject("{" +
                    "description: 'Lul Charges'," +
                    "image: 'https://rzp-mobile.s3.amazonaws.com/images/rzp.png'," +
                    "currency: 'INR'}"
            );

            options.put("amount", "100");
            options.put("name", "Booky Inc");
            options.put("prefill", new JSONObject("{email: 'rishabh.bhatia08@gmail.com', contact: '9650166311'}"));

            co.open(activity, options);

        } catch(Exception e){
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     *   onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    public void onPaymentSuccess(String razorpayPaymentID)
    {
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.e("com.merchant", e.getMessage(), e);
        }
    }

    /**
     * The name of the function has to be
     *   onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    public void onPaymentError(int code, String response)
    {
        try {
            Toast.makeText(this, "Payment failed: " + Integer.toString(code) + " " + response, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.e("com.merchant", e.getMessage(), e);
        }
    }
}
