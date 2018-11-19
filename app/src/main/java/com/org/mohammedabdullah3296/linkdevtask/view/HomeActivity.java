package com.org.mohammedabdullah3296.linkdevtask.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.org.mohammedabdullah3296.linkdevtask.R;
import com.org.mohammedabdullah3296.linkdevtask.model.Article;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ArticleListFragment.OnStepClickListener {
    private boolean mTwoPane;
    private static String currentImage;
    private static String currentTitle;
    private static String currentDescription;
    private static String currentPublisher;
    private static String currentDate;
    private static String currentContent;
    private static String currentUrl;

    @BindView(R.id.media_image_tablet)
    ImageView articleImage;
    @BindView(R.id.primary_text)
    TextView articleTitle;
    @BindView(R.id.sub_text)
    TextView articlePublicher;
    @BindView(R.id.action_button_2)
    TextView articleDescribtion;
    @BindView(R.id.action_button_1)
    TextView articleContent;
    @BindView(R.id.action_button_)
    TextView articleDate;

    @BindView(R.id.loading_indicator_tab)
    ProgressBar loading_indicator_tab;

    @BindView(R.id.websitbtn)
    Button websitbtn;

    private ArticleListFragment articleListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        articleListFragment = new ArticleListFragment();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.laoyoutfor, articleListFragment, "articles").commit();

        if (findViewById(R.id.details_fragment) != null) {
            ButterKnife.bind(this);

            websitbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(currentUrl) || currentUrl == null) {

                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentUrl));
                        startActivity(intent);
                    }

                }
            });

            mTwoPane = true;
            if (savedInstanceState != null) {
                websitbtn.setVisibility(View.VISIBLE);
                loading_indicator_tab.setVisibility(View.GONE);
                articleTitle.setText(savedInstanceState.getString("currentTitle"));
                articlePublicher.setText(savedInstanceState.getString("currentPublisher"));
                setVisibility(articleDescribtion, savedInstanceState.getString("currentDescription"));
                setVisibility(articleContent, savedInstanceState.getString("currentContent"));
                articleDate.setText(dateFormate(savedInstanceState.getString("currentDate")));
                Picasso.get().load(savedInstanceState.getString("currentImage"))
                        .placeholder(R.drawable.placeholder).into(articleImage);
                currentUrl = savedInstanceState.getString("currentUrl");

            } else {
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                if (articleListFragment.articles != null) {
                                    Article firstArticle = articleListFragment.articles.get(0);
                                    articleTitle.setText(firstArticle.getTitle());
                                    articlePublicher.setText(String.format("By %s", firstArticle.getAuthor()));
                                    setVisibility(articleDescribtion, firstArticle.getDescription());
                                    setVisibility(articleContent, firstArticle.getContent());
                                    articleDate.setText(dateFormate(firstArticle.getPublishedAt()));
                                    Picasso.get().load(firstArticle.getUrlToImage())
                                            .placeholder(R.drawable.placeholder).into(articleImage);

                                    currentTitle = firstArticle.getTitle();
                                    currentPublisher = String.format("By %s", firstArticle.getAuthor());
                                    currentContent = firstArticle.getContent();
                                    currentDescription = firstArticle.getDescription();
                                    currentImage = firstArticle.getUrlToImage();
                                    currentDate = firstArticle.getPublishedAt();
                                    currentUrl = firstArticle.getUrl();
                                    websitbtn.setVisibility(View.VISIBLE);
                                }

                                loading_indicator_tab.setVisibility(View.GONE);
                            }
                        }, 3000);
            }
        } else {
            // We're in single-pane mode and displaying fragments on a phone in separate activities
            mTwoPane = false;
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage2) {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage3) {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void OnArticleClickListener(int postion) {
        Article clickedArticle = articleListFragment.articles.get(postion);
        if (mTwoPane) {
            websitbtn.setVisibility(View.VISIBLE);
            loading_indicator_tab.setVisibility(View.GONE);
            currentTitle = clickedArticle.getTitle();
            currentPublisher = String.format("By %s", clickedArticle.getAuthor());
            currentContent = clickedArticle.getContent();
            currentDescription = clickedArticle.getDescription();
            currentImage = clickedArticle.getUrlToImage();
            currentDate = clickedArticle.getPublishedAt();
            currentUrl = clickedArticle.getUrl();

            articleTitle.setText(clickedArticle.getTitle());
            articlePublicher.setText(String.format("By %s", clickedArticle.getAuthor()));
            setVisibility(articleDescribtion, clickedArticle.getDescription());
            setVisibility(articleContent, clickedArticle.getContent());
            articleDate.setText(dateFormate(clickedArticle.getPublishedAt()));
            Picasso.get().load(clickedArticle.getUrlToImage())
                    .placeholder(R.drawable.placeholder).into(articleImage);

        } else {
            // Put this information in a Bundle and attach it to an Intent that will launch an Details Activity
            Bundle b = new Bundle();
            b.putString("currentTitle", clickedArticle.getTitle());
            b.putString("currentPublisher", clickedArticle.getAuthor());
            b.putString("currentContent", clickedArticle.getContent());
            b.putString("currentDescription", clickedArticle.getDescription());
            b.putString("currentImage", clickedArticle.getUrlToImage());
            b.putString("currentDate", clickedArticle.getPublishedAt());
            b.putString("currentUrl", clickedArticle.getUrl());


            // Attach the Bundle to an intent
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mTwoPane) {
            outState.putString("currentImage", currentImage);
            outState.putString("currentTitle", currentTitle);
            outState.putString("currentPublisher", currentPublisher);
            outState.putString("currentDate", currentDate);
            outState.putString("currentDescription", currentDescription);
            outState.putString("currentContent", currentContent);
            outState.putString("currentUrl", currentUrl);
        }
    }

    public void setVisibility(TextView v, String msg) {
        if (TextUtils.isEmpty(msg)) {
            v.setVisibility(View.GONE);
        } else {
            v.setText(msg);
        }
    }

    public String dateFormate(String date1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        String mmm = null ;
        try {
            Date date = sdf.parse(date1);
            mmm   = new SimpleDateFormat("MMM dd, yyyy").format(date) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mmm;
    }
}
