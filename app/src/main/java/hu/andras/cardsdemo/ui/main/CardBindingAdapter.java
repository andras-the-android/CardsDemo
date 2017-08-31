package hu.andras.cardsdemo.ui.main;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CardBindingAdapter {

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}
