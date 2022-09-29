package com.carte.navigator.menu.models;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.carte.navigator.R;

public class Model_Image_Text {
 //   private final String optionName;

    private final String optionText;

    public Model_Image_Text(String optionText, Context context,int i) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }

}
