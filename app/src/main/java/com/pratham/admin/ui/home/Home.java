package com.pratham.admin.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
import com.pratham.admin.ui.notification.NotificationFragment;
import com.pratham.admin.ui.notification.NotificationFragment_;
import com.pratham.admin.util.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.greenrobot.eventbus.EventBus;

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
                FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("1") ||
                FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("13"))
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
                            FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("1") ||
                            FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("13"))
                        selectedFragment = new BlockLeaderHomeFragment_();
                    // It will help to replace the
                    // one fragment to other.
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();

                    break;
                case R.id.tabholders:
                    selectedFragment = new TabHolderFragment_();
                    // It will help to replace the
                    // one fragment to other.
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();

                    break;
                case R.id.inventory:
                    selectedFragment = new InventoryFragment_();
                    // It will help to replace the
                    // one fragment to other.
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();

                    break;
                case R.id.notification:
                    //Toast.makeText(Home.this, "Under Construction!", Toast.LENGTH_SHORT).show();
                    selectedFragment = new NotificationFragment_();
                    // It will help to replace the
                    // one fragment to other.
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    break;
                case R.id.setting:
                    Toast.makeText(Home.this, "Setting", Toast.LENGTH_SHORT).show();
                    break;
            }
/*
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
*/
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
/*
        if (fragment instanceof BlockLeaderHomeFragment_) {
            new MaterialAlertDialogBuilder(Home.this)
                    .setTitle("EXIT APP")
                    .setMessage("You sure you wanna exit?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finishAffinity();
                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        }
*/
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            Log.e("KIX b1: ", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
            new MaterialAlertDialogBuilder(Home.this)
                    .setTitle("EXIT APP")
                    .setMessage("You sure you wanna exit?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finishAffinity();
                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        } else {
            Log.e("KIX b2: ", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
            getSupportFragmentManager().popBackStackImmediate();
        }
    }
}