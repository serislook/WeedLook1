package com.skyweednet.weedlook.views.finder;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.skyweednet.weedlook.R;
import com.skyweednet.weedlook.models.Sample;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinderDialogFragment extends DialogFragment implements FinderCallback {

    private EditText editText;
    private ImageButton imageButton;
    private SpinKitView loading;

    public static FinderDialogFragment newInstance(Sample sample) {
        FinderDialogFragment finderDialogFragment = new FinderDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("sample", sample);
        finderDialogFragment.setArguments(bundle);
        return finderDialogFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_finder, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = (EditText) view.findViewById(R.id.userImputEt);
        imageButton = (ImageButton) view.findViewById(R.id.sendBtn);
        loading = (SpinKitView) view.findViewById(R.id.loadingSkv);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCancelable(false);
                editText.setError(null);
                String email = editText.getText().toString();
                editText.setVisibility(View.GONE);
                imageButton.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                Sample sample = (Sample) getArguments().getSerializable("sample");
                Toast.makeText(getContext(), sample.getName(), Toast.LENGTH_SHORT).show();
                new UserValidation(FinderDialogFragment.this, getContext()).init(email);
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
    }

    @Override
    public void error(String error) {
        restoreViews();
        editText.setError(error);


    }

    @Override
    public void success() {
        dismiss();

    }

    @Override
    public void notFound() {
        restoreViews();
        Toast.makeText(getContext(), "Usuario no hallado", Toast.LENGTH_SHORT).show();

    }

    private void restoreViews() {
        editText.setVisibility(View.VISIBLE);
        imageButton.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        setCancelable(true);

    }
}
