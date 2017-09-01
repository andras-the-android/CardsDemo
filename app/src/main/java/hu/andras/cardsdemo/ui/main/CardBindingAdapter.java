package hu.andras.cardsdemo.ui.main;

import android.animation.Animator;
import android.databinding.BindingAdapter;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CardBindingAdapter {

    public static final int ANIMATION_DURATION = 200;

    @BindingAdapter("imageResource")
    public static void setImageResource(final ImageView imageView, final int resource){
        ViewPropertyAnimator animator = imageView.animate().scaleX(0f).setDuration(ANIMATION_DURATION);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                imageView.setImageResource(resource);
                imageView.animate().scaleX(1f).setDuration(ANIMATION_DURATION).start();
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

    @BindingAdapter("hideCard")
    public static void hideCard(final ImageView imageView, boolean hide){
        if (hide) {
            imageView.animate().alpha(0f).setDuration(ANIMATION_DURATION).start();
        }
    }
}
