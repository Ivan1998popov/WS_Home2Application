package ru.myproject.ws_home2application.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


import ru.myproject.ws_home2application.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class ShekelFragment extends Fragment {


    private EditText text_shekel;
    private Button btn_translate;
    private static final String TAG_ID_TRANSLATE="translate";
    private static final String TAG_VALUE_TRANSLATE="value";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shekel, container, false);

        text_shekel = view.findViewById(R.id.text_shekel);
        btn_translate = view.findViewById(R.id.btn_translate);
        btn_translate.setEnabled(false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            text_shekel.setText(bundle.getString(TAG_VALUE_TRANSLATE));
            btn_translate.setEnabled(true);
        }

        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double shekel = Double.parseDouble(text_shekel.getText().toString());
                Bundle toRubles = new Bundle();
                toRubles.putDouble(TAG_VALUE_TRANSLATE, translateToRubles(shekel));
                toRubles.putString(TAG_ID_TRANSLATE, "shekel");
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                RublesFragment rublesFragment = new RublesFragment();
                rublesFragment.setArguments(toRubles);
                fragmentTransaction.replace(R.id.container, rublesFragment);
                fragmentTransaction.commit();
                closeVirtualKeywords();
            }
        });

        text_shekel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btn_translate.setEnabled(false);
                } else {
                    btn_translate.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;


    }

    private Double translateToRubles(Double shekel) {
        return shekel /0.0559;
    }

    private void closeVirtualKeywords(){
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

}
