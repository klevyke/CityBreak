package com.example.android.citybreak;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle extras = getIntent().getExtras();

        TextView name = findViewById(R.id.name);
        name.setText(extras.getString("name"));

        TextView description = findViewById(R.id.description);
        description.setText(extras.getString("description"));

        ImageView image = findViewById(R.id.image);
        image.setImageResource(extras.getInt("image"));
    }

}
