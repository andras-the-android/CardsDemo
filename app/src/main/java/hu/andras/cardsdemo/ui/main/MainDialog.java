package hu.andras.cardsdemo.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.andras.cardsdemo.databinding.ViewMainDialogBinding;
import hu.andras.cardsdemo.di.Injector;
import lombok.Setter;

public class MainDialog extends DialogFragment {

    @Setter private MainDialogViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Injector.inject(this);
        ViewMainDialogBinding binding = ViewMainDialogBinding.inflate(inflater, container, false);
        binding.setModel(viewModel);
        return binding.getRoot();
    }
}
