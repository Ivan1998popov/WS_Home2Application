package ru.myproject.ws_home2application.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

import ru.myproject.ws_home2application.R;
import ru.myproject.ws_home2application.ui.fragments.EuroFragment;
import ru.myproject.ws_home2application.ui.fragments.RublesFragment;
import ru.myproject.ws_home2application.ui.fragments.ShekelFragment;
import ru.myproject.ws_home2application.ui.fragments.TengeFragment;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nav_name_user;

    private static final String TAG_ID_TRANSLATE="translate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation_drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        nav_name_user = view.findViewById(R.id.nav_name_user);
        nav_name_user.setText(Objects.requireNonNull(getIntent().getExtras())
                .getString("name_user"));



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Bundle bundle=new Bundle();

        if (id == R.id.tenge) {
            bundle.putString(TAG_ID_TRANSLATE,"tenge");
            FragmentTransaction fragmentTransactionTenge = getSupportFragmentManager().beginTransaction();
            RublesFragment rublesFragment = new RublesFragment();
            rublesFragment.setArguments(bundle);
            fragmentTransactionTenge.replace(R.id.container, rublesFragment);
            TengeFragment tengeFragment =new TengeFragment();
            fragmentTransactionTenge.add(R.id.container, tengeFragment).commit();
        } else if (id == R.id.euro) {

            bundle.putString(TAG_ID_TRANSLATE,"euro");
            FragmentTransaction fragmentTransactionEuro = getSupportFragmentManager().beginTransaction();
            RublesFragment rublesFragment = new RublesFragment();
            rublesFragment.setArguments(bundle);
            fragmentTransactionEuro.replace(R.id.container, rublesFragment);
            EuroFragment euroFragment =new EuroFragment();
            fragmentTransactionEuro.add(R.id.container, euroFragment).commit();

        } else if (id == R.id.shekel) {

            bundle.putString(TAG_ID_TRANSLATE,"shekel");
            FragmentTransaction fragmentTransactionShekel = getSupportFragmentManager().beginTransaction();
            RublesFragment rublesFragment = new RublesFragment();
            rublesFragment.setArguments(bundle);
            fragmentTransactionShekel.replace(R.id.container, rublesFragment);
            ShekelFragment shekelFragment = new ShekelFragment();
            fragmentTransactionShekel.add(R.id.container, shekelFragment).commit();

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
