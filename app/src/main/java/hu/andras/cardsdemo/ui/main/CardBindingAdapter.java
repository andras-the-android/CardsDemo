package hu.andras.cardsdemo.ui.main;

import android.animation.Animator;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CardBindingAdapter {

    public static final int ANIMATION_DURATION = 200;

    @BindingAdapter({"imageResource", "expandingAnimationOnly"})
    public static void setImageResource(final ImageView imageView, final int resource, boolean expandingAnimationOnly){
        Log.d("xxx", "" + resource);
        if (expandingAnimationOnly) {
            imageView.setScaleX(0f);
            expandCard(imageView, resource);
        } else {
            ViewPropertyAnimator animator = imageView.animate().scaleX(0f).setDuration(ANIMATION_DURATION);
            animator.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    expandCard(imageView, resource);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            animator.start();
        }

    }

    private static void expandCard(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
        imageView.animate().setListener(null).scaleX(1f).setDuration(ANIMATION_DURATION).start();
    }

    @BindingAdapter("hideCard")
    public static void hideCard(final ImageView imageView, boolean hide){
        if (hide) {
            imageView.animate().alpha(0f).setDuration(ANIMATION_DURATION).start();
        } else {
            imageView.setAlpha(1f);
        }
    }
}
