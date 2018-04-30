package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView mTextViewAlsoKnownAs = findViewById(R.id.also_known_tv);
        TextView mTextViewPlaceOfOrigin = findViewById(R.id.origin_tv);
        TextView mTextViewDescription = findViewById(R.id.description_tv);
        TextView mTextViewIngredients = findViewById(R.id.ingredients_tv);

        for(int x=0; x < sandwich.getAlsoKnownAs().size(); x++)
        {
            mTextViewAlsoKnownAs.append(sandwich.getAlsoKnownAs().get(x) + "\n");
        }

        for (int i = 0; i < sandwich.getIngredients().size(); i++) {
            mTextViewIngredients.append(sandwich.getIngredients().get(i) + "\n");
        }

        mTextViewPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());
        mTextViewDescription.setText(sandwich.getDescription());

    }
}
