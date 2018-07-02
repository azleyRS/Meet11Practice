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
import android.widget.TextView;

public class Fragment1 extends Fragment {
    private TextView mTextView;
    private Button mButton;


    private IActivityCallbacks mCallBacks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mButton = view.findViewById(R.id.show_button);
        mTextView = view.findViewById(R.id.show_textview);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText(mCallBacks.getText());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBacks = ((MainActivity)context);
    }
}
