package hu.andras.cardsdemo.ui.main.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.andras.cardsdemo.businesslogic.GameRepository;
import hu.andras.cardsdemo.businesslogic.HighScoreRepository;
import hu.andras.cardsdemo.databinding.ViewMainDialogBinding;
import hu.andras.cardsdemo.di.Injector;
import lombok.Setter;

public class MainDialog extends DialogFragment implements MainDialogRouter {

    @Setter private GameRepository gameRepository;
    @Setter private HighScoreRepository highScoreRepository;
    private MainDialogViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
        viewModel = new MainDialogViewModel(this, getResources(), gameRepository, highScoreRepository);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewMainDialogBinding binding = ViewMainDialogBinding.inflate(inflater, container, false);
        binding.setModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void closeDialog() {
        dismiss();
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity parent = getActivity();
        if (parent != null && parent instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) parent).onDismiss(dialog);
        }
    }

    @Override
    public void onDestroyView() {
        // handles https://code.google.com/p/android/issues/detail?id=17423
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }
}
