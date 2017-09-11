package hu.andras.cardsdemo.ui.main.dialog;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;

public final class MainDialogBindingAdapter {

    private MainDialogBindingAdapter() {
    }

    @BindingAdapter("errorMessage")
    public static void addValidation(TextInputLayout til, String errorMessage) {
        if (errorMessage != null) {
            new TextInputLayoutValidationHelper(til, errorMessage);
        }
    }
}
