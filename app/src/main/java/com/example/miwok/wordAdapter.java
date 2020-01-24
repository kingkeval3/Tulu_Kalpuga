package com.example.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class wordAdapter extends ArrayAdapter<word> {


    /** Resource ID for the background color for this list of words */
    private int mColorResourceId;



    public wordAdapter(Activity context, ArrayList<word> words,int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorResourceId = colorResourceId;


    }






    //@androidx.annotation.NonNull



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.row, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        word currentWord = getItem(position);





        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView tulu = (TextView) listItemView.findViewById(R.id.tulu);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        tulu.setText(currentWord.getMiwokTranslation());





        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView eng = (TextView) listItemView.findViewById(R.id.eng);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        eng.setText(currentWord.getDefaultTranslation());






        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentWord.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }




        //FOR CUSTOM BACKGROUND COLOR IN EACH ACTIVITY

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);





        return listItemView;
    }

}