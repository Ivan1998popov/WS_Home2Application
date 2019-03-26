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

import java.math.BigDecimal;
import java.util.Objects;

import ru.myproject.ws_home2application.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class RublesFragment extends Fragment {

    private EditText text_rubles;
    private Button btn_translate;
    private Double rubles;
    private static final String TAG_ID_TRANSLATE="translate";
    private static final String TAG_VALUE_TRANSLATE="value";
    private String translate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rubles, container, false);

        text_rubles = view.findViewById(R.id.text_rubles);
        btn_translate = view.findViewById(R.id.btn_translate);
        Bundle bundle = getArguments();
        assert bundle != null;
        translate = bundle.getString(TAG_ID_TRANSLATE);

        if (bundle.getDouble(TAG_VALUE_TRANSLATE) != 0.0)
            text_rubles.setText(String.valueOf(rounding(bundle.getDouble(TAG_VALUE_TRANSLATE))));


        text_rubles.requestFocus();
        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubles = Double.parseDouble(text_rubles.getText().toString());
                translate_function(translate);
                closeVirtualKeywords();

            }
        });
        text_rubles.addTextChangedListener(new TextWatcher() {
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

    private void translate_function(String translate) {
        Bundle bundle = new Bundle();
        switch (translate) {
            case "shekel":
                double shekel = rubles * 0.0559;
                bundle.putString(TAG_VALUE_TRANSLATE, String.valueOf(rounding(shekel)));
                FragmentTransaction fragmentTransactionShekel = Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager().beginTransaction();
                ShekelFragment shekelFragment = new ShekelFragment();
                shekelFragment.setArguments(bundle);
                fragmentTransactionShekel.replace(R.id.container, shekelFragment).commit();

                break;
            case "euro":
                double euro = rubles / 73.10;
                bundle.putString(TAG_VALUE_TRANSLATE, String.valueOf(rounding(euro)));
                FragmentTransaction fragmentTransactionEuro = Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager().beginTransaction();
                EuroFragment euroFragment =new EuroFragment();
                euroFragment.setArguments(bundle);
                fragmentTransactionEuro.replace(R.id.container, euroFragment).commit();
                break;
            case "tenge":
                double tenge = rubles *5.85;
                bundle.putString(TAG_VALUE_TRANSLATE, String.valueOf(rounding(tenge)));
                FragmentTransaction fragmentTransactionTenge = Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager().beginTransaction();
                TengeFragment tengeFragment =new TengeFragment();
                tengeFragment.setArguments(bundle);
                fragmentTransactionTenge.replace(R.id.container, tengeFragment).commit();
                break;
        }

    }

    private BigDecimal rounding(Double number) {

        BigDecimal bigDecimal = new BigDecimal(number);
        BigDecimal roundedWithScale = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);

        return roundedWithScale;
    }

    private void closeVirtualKeywords() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

}
