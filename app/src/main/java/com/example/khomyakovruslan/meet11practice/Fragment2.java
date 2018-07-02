package com.example.khomyakovruslan.meet11practice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment2 extends Fragment {

    private Button setButton;
    private EditText mEditText;


    private IActivityCallbacks mCallBacks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mEditText = view.findViewById(R.id.edit_edittext);
        setButton = view.findViewById(R.id.edit_button);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = String.valueOf(mEditText.getText());
                mCallBacks.setText(text);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBacks = ((MainActivity)context);
    }
}
