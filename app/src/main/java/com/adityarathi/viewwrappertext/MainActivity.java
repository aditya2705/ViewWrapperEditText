package com.adityarathi.viewwrappertext;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.adityarathi.viewwrapperedittext.CustomEditText;
import com.adityarathi.viewwrapperedittext.ViewTextWrapperLayout;

public class MainActivity extends AppCompatActivity {

    private ViewTextWrapperLayout imageTextWrapperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageTextWrapperLayout = (ViewTextWrapperLayout) findViewById(R.id.imageTextWrapperLayout);

        Button button = (Button)findViewById(R.id.add_image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.drawable.sample);
                imageView.setAdjustViewBounds(true);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageTextWrapperLayout.deleteViewWithinText(v);
                    }
                });
                CustomEditText customEditText = (CustomEditText) View.inflate(MainActivity.this,R.layout.custom_edit_text,null);
                imageTextWrapperLayout.addViewWithinText(imageView,customEditText);
            }
        });

    }


}
