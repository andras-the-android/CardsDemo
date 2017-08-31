package hu.andras.cardsdemo.data;


import android.support.annotation.DrawableRes;

import hu.andras.cardsdemo.R;

public enum CardType {

    cc(R.drawable.card_cc),
    cloud(R.drawable.card_cloud),
    console(R.drawable.card_console),
    multiscreen(R.drawable.card_multiscreen),
    remote(R.drawable.card_remote),
    tablet(R.drawable.card_tablet),
    tv(R.drawable.card_tv),
    vr(R.drawable.card_vr);

    private int imageResId;

    CardType(@DrawableRes int imageResId) {
        this.imageResId = imageResId;
    }

    public int getImageResId() {
        return imageResId;
    }
}
