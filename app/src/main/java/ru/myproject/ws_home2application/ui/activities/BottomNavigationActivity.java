package ru.myproject.ws_home2application.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import ru.myproject.ws_home2application.R;
import ru.myproject.ws_home2application.ui.fragments.EuroFragment;
import ru.myproject.ws_home2application.ui.fragments.RublesFragment;
import ru.myproject.ws_home2application.ui.fragments.ShekelFragment;
import ru.myproject.ws_home2application.ui.fragments.TengeFragment;

public class BottomNavigationActivity extends AppCompatActivity {

    private static final String TAG_ID_TRANSLATE="translate";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Bundle bundle=new Bundle();
            RublesFragment rublesFragment = new RublesFragment();
            switch (item.getItemId()) {
                case R.id.shekel:
                    bundle.putString(TAG_ID_TRANSLATE,"tenge");
                    FragmentTransaction fragmentTransactionTenge = getSupportFragmentManager().beginTransaction();
                    rublesFragment.setArguments(bundle);
                    fragmentTransactionTenge.replace(R.id.container1, rublesFragment);
                    TengeFragment tengeFragment =new TengeFragment();
                    fragmentTransactionTenge.replace(R.id.container2, tengeFragment).commit();
                    return true;
                case R.id.tenge:
                    bundle.putString(TAG_ID_TRANSLATE,"shekel");
                    FragmentTransaction fragmentTransactionShekel = getSupportFragmentManager().beginTransaction();
                    rublesFragment.setArguments(bundle);
                    fragmentTransactionShekel.replace(R.id.container1, rublesFragment);
                    ShekelFragment shekelFragment = new ShekelFragment();
                    fragmentTransactionShekel.replace(R.id.container2, shekelFragment).commit();
                    return true;
                case R.id.euro:
                    bundle.putString(TAG_ID_TRANSLATE,"euro");
                    FragmentTransaction fragmentTransactionEuro = getSupportFragmentManager().beginTransaction();
                    rublesFragment.setArguments(bundle);
                    fragmentTransactionEuro.replace(R.id.container1, rublesFragment);
                    EuroFragment euroFragment =new EuroFragment();
                    fragmentTransactionEuro.replace(R.id.container2, euroFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
