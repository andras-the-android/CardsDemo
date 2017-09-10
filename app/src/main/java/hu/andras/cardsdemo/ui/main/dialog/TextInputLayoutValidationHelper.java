package hu.andras.cardsdemo.ui.main.dialog;

import android.graphics.PorterDuff;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class TextInputLayoutValidationHelper {

    private EditText editText;
    private TextInputLayout textInputLayout;
    private int red;


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            dismissError();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public TextInputLayoutValidationHelper(TextInputLayout textInputLayout, String errorMessage) {
        this.textInputLayout = textInputLayout;
        editText = (EditText) textInputLayout.getEditText();
        editText.addTextChangedListener(textWatcher);
        red = ContextCompat.getColor(textInputLayout.getContext(), android.R.color.holo_red_dark);
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(errorMessage);
        editText.getBackground().setColorFilter(red, PorterDuff.Mode.SRC_ATOP);
    }

    public void dismissError() {
        textInputLayout.setErrorEnabled(false);
        editText.getBackground().clearColorFilter();
        editText.removeTextChangedListener(textWatcher);
    }

}
