package com.pratham.admin.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pratham.admin.R;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.ui.CRL_ProfileFragment;
import com.pratham.admin.ui.CRL_ProfileFragment_;
import com.pratham.admin.ui.blockLeader.Inventory.InventoryFragment_;
import com.pratham.admin.ui.blockLeader.home.BlockLeaderHomeFragment_;
import com.pratham.admin.ui.blockLeader.tabHolders.TabHolderFragment;
import com.pratham.admin.ui.blockLeader.tabHolders.TabHolderFragment_;
import com.pratham.admin.ui.home.replaceTablet.ReplaceFormFragment;
import com.pratham.admin.ui.home.replaceTablet.ReplaceFormFragment_;
import com.pratham.admin.ui.home.reportLost.ReportlostFormFragment_;
import com.pratham.admin.util.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@SuppressLint("NonConstantResourceId")
@EActivity(R.layout.activity_home)
public class Home extends BaseActivity {

    @AfterViews
    protected void init() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // as soon as the application opens the first
        // fragment should be shown to the user
        // in this case it is algorithm fragment
        if (FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("6"))
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment_()).commit();
        else if (FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("5") ||
                FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("3") ||
                FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("1"))
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlockLeaderHomeFragment_()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    if (FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("6"))
                        selectedFragment = new HomeFragment_();
                    else if (FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("5") ||
                            FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("3") ||
                            FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("1"))
                        selectedFragment = new BlockLeaderHomeFragment_();
                    break;
                case R.id.notification:
                    selectedFragment = new TabHolderFragment_();
                    break;
                case R.id.setting:
                    selectedFragment = new CRL_ProfileFragment_();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        }
    };
}