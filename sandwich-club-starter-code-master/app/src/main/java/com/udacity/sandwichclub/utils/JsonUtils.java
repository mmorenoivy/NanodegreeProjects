package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        /*https://www.androidhive.info/2012/01/android-json-parsing-tutorial/ source
        and Android - Networking course in Udacity - Parsing JSON in Android
        * */

        try {
            /** declare all nodes from the root object. Take the root object(jsonObj) and initialize to JSONObject
             * **/
            JSONObject jsonObj = new JSONObject(json);

            JSONObject jsonName = jsonObj.optJSONObject(NAME);

            String mainName = jsonName.optString(MAIN_NAME);

            JSONArray jsonArrAlsoKnownAs = jsonName.optJSONArray(ALSO_KNOWN_AS);

            /**Store JSON into an Arraylist**/
            ArrayList<String> alsoKnownAs = new ArrayList<>();

            for (int i = 0; i < jsonArrAlsoKnownAs.length(); i++) {
                alsoKnownAs.add(jsonArrAlsoKnownAs.getString(i));
            }
            String placeOfOrigin = jsonObj.optString(PLACE_OF_ORIGIN);

            String description = jsonObj.optString(DESCRIPTION);

            String image = jsonObj.optString(IMAGE);

            /**do the same here**/
            JSONArray jsonArrIngredients = jsonObj.getJSONArray(INGREDIENTS);
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
