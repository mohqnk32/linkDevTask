package com.org.mohammedabdullah3296.linkdevtask.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.org.mohammedabdullah3296.linkdevtask.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.media_image)
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
	 @BindView(R.id.websitbtn)
     Button websitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        final Bundle bundle = getIntent().getExtras();
        articleTitle.setText(bundle.getString("currentTitle"));
        articlePublicher.setText(String.format("By %s", bundle.getString("currentPublisher")));
        setVisibility(articleDescribtion, bundle.getString("currentDescription"));
        setVisibility(articleContent, bundle.getString("currentContent"));
        articleDate.setText(dateFormate(bundle.getString("currentDate")));
        Picasso.get().load(bundle.getString("currentImage"))
                .placeholder(R.drawable.placeholder).into(articleImage);
        websitbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(bundle.getString("currentUrl")));
                    startActivity(intent);
                }
            });
    }

    public void setVisibility(TextView v, String msg) {
        if (TextUtils.isEmpty(msg)) {
            v.setVisibility(View.GONE);
        } else {
            v.setText(msg);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
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
