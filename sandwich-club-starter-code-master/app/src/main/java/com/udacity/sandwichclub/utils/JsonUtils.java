package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        /*https://www.androidhive.info/2012/01/android-json-parsing-tutorial/ source
        and Android - Networking course in Udacity - Parsing JSON in Android
        * */
            try{
                JSONObject jsonObj = new JSONObject(json);

                JSONObject jsonName = jsonObj.optJSONObject("name");

                String mainName = jsonName.optString("mainName");

                JSONArray jsonArrAlsoKnownAs = jsonName.optJSONArray("alsoKnownAs");

                ArrayList<String> alsoKnownAs = new ArrayList<>();

                for (int i = 0; i < jsonArrAlsoKnownAs.length(); i++) {
                    alsoKnownAs.add(jsonArrAlsoKnownAs.getString(i));
                }
                String placeOfOrigin = jsonObj.optString("placeOfOrigin");

                String description = jsonObj.optString("description");

                String image = jsonObj.optString("image");

                JSONArray jsonArrIngredients = jsonObj.getJSONArray("ingredients");
                ArrayList<String> ingredients = new ArrayList<>();

                for (int i = 0; i < jsonArrIngredients.length(); i++) {
                    ingredients.add(jsonArrIngredients.optString(i));
                }

                return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
}
