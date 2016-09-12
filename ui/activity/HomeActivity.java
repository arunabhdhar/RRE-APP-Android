package org.rehab.app.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.adapter.LeftMenuAdapter;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.ui.fragment.AboutFragment;
import org.rehab.app.ui.fragment.CalculatorFragment;
import org.rehab.app.ui.fragment.ChangePasswordFragment;
import org.rehab.app.ui.fragment.CompareFragment;
import org.rehab.app.ui.fragment.ContactUsFragment;
import org.rehab.app.ui.fragment.FAQFragment;
import org.rehab.app.ui.fragment.NewDealFragment;
import org.rehab.app.ui.fragment.PrivacyPolicyFragment;
import org.rehab.app.ui.fragment.SavedDealParentsFragment;
import org.rehab.app.ui.fragment.SettingsFragment;
import org.rehab.app.ui.fragment.TermsConditionsFragment;
import org.rehab.app.ui.fragment.TutorialFragment;
import org.rehab.app.ui.fragment.UserProfileFragment;
import org.rehab.app.utils.HideKeyboard;
import org.rehab.app.utils.TakeImageClass;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class HomeActivity extends AppCompatActivity implements IASCommon {


    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_drawer)
    FrameLayout mDrawerView;
    @BindView(R.id.navigation_drawer_menu)
    ListView mDrawerMenu;
    @BindView(R.id.navigation_drawer_scrim)
    View mDrawerScrim;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView tvToolBarTitle;

    private Menu mMenu;
    private Fragment mFragment;
    private ActionBarDrawerToggle mDrawerToggle;
    private LeftMenuAdapter leftMenuAdapter;
    private String[] menuData;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(HomeActivity.this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_menu_btn);
        toolbar.setTitleTextColor(getResources().getColor(R.color.red));
        TypedArray colorPrimaryDark =
                getTheme().obtainStyledAttributes(new int[]{R.attr.colorPrimaryDark});
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        colorPrimaryDark.recycle();

        menuData = getResources().getStringArray(R.array.left_menu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Set the drawer width accordingly with the guidelines: window_width - toolbar_height.
            toolbar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int left, int top, int right, int bottom,
                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (left == 0 && top == 0 && right == 0 && bottom == 0) {
                        return;
                    }
                    DisplayMetrics metrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    float logicalDensity = metrics.density;
                    int maxWidth = (int) Math.ceil(getResources().getInteger(R.integer.left_menu_width) * logicalDensity);
                    DrawerLayout.LayoutParams params =
                            (DrawerLayout.LayoutParams) mDrawerView.getLayoutParams();
                    int newWidth = view.getWidth() - view.getHeight();
                    params.width = ((newWidth > maxWidth ? maxWidth : newWidth)) - 100;
                    mDrawerView.setLayoutParams(params);
                }
            });
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            mDrawerView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
                @Override
                public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                    // Set scrim height to match status bar height.
                    mDrawerScrim.setLayoutParams(new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            insets.getSystemWindowInsetTop()));
                    return insets;
                }
            });
        }
        leftMenuAdapter = new LeftMenuAdapter(HomeActivity.this, HomeActivity.this);
        mDrawerMenu.setAdapter(leftMenuAdapter);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                HideKeyboard.keyboardHide(HomeActivity.this);
                super.onDrawerOpened(drawerView);
            }
        };
        openFragment(1);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.overflow_menu, menu);
//        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return (mDrawerToggle.onOptionsItemSelected(item)
                || super.onOptionsItemSelected(item));
    }


    @Override
    public void onResponse(Object type, Object data) {
        if (type.toString().equals("left_menu")) {
            openFragment(Integer.parseInt(data.toString()));
        }
    }


    /**
     * Method for handle the click listener of leftmenu item and open the respect fragment.
     *
     * @param i
     */

    public void openFragment(int i) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (i) {
            case AppConstants.LM_QUICK_CALCULATOR:
                if (!(mFragment instanceof CalculatorFragment)) {
                    clearBackStack();
                    tvToolBarTitle.setText(getResources().getString(R.string.txt_quick_calculator));
                    tvToolBarTitle.setBackgroundResource(0);
                    mFragment = new CalculatorFragment();
                    ft.replace(R.id.container, mFragment, "CalculatorFragment");  //Tag of fragement
                    ft.commit();
                }
                mDrawerLayout.closeDrawers();
                break;
            case AppConstants.LM_NEW_DEAL:
//                Intent mIntent = new Intent(HomeActivity.this, EX2OperatingExpenseActivity.class);
//                startActivityForResult(mIntent, AppConstants.ND_EX2_OPER_PRO_INCOME);
                if (!(mFragment instanceof NewDealFragment)) {
                    clearBackStack();
                    tvToolBarTitle.setText(getResources().getString(R.string.txt_new_deal));
                    tvToolBarTitle.setBackgroundResource(0);
                    mFragment = new NewDealFragment();
                    ft.replace(R.id.container, mFragment, "NewDealFragment");  //Tag of fragement
                    ft.commit();
                }
                mDrawerLayout.closeDrawers();
                break;
            case AppConstants.LM_ABOUT_US:

                if (!(mFragment instanceof AboutFragment)) {
                    clearBackStack();
                    tvToolBarTitle.setText("About Us");
                    tvToolBarTitle.setBackgroundResource(0);
                    mFragment = new AboutFragment();
                    ft.replace(R.id.container, mFragment, "AboutFragment");  //Tag of fragement
                    ft.commit();
                }
                mDrawerLayout.closeDrawers();
                break;
            case AppConstants.LM_SETTINGS:
                if (!(mFragment instanceof SettingsFragment)) {
                    clearBackStack();
                    tvToolBarTitle.setText("Settings");
                    tvToolBarTitle.setBackgroundResource(0);
                    mFragment = new SettingsFragment();
                    ft.replace(R.id.container, mFragment, "SettingsFragment");  //Tag of fragement
                    ft.commit();
                }
                mDrawerLayout.closeDrawers();
                break;
            case AppConstants.LM_CONTACT_US:
                if (!(mFragment instanceof ContactUsFragment)) {
                    clearBackStack();
                    tvToolBarTitle.setText("Contact Us");
                    tvToolBarTitle.setBackgroundResource(0);
                    mFragment = new ContactUsFragment();
                    ft.replace(R.id.container, mFragment, "ContactUsFragment");  //Tag of fragement
                    ft.commit();
                }
                mDrawerLayout.closeDrawers();
                break;
            case AppConstants.LM_TUTORIALS:
                if(!(mFragment instanceof TutorialFragment)){
                    clearBackStack();
                    tvToolBarTitle.setText("Tutorial");
                    tvToolBarTitle.setBackgroundResource(0);
                    mFragment = new TutorialFragment();
                    ft.replace(R.id.container, mFragment, "Tutorial");  //Tag of fragement
                    ft.commit();
                }
                mDrawerLayout.closeDrawers();
                break;
            case AppConstants.LM_USER_PROFILE:
                if(!(mFragment instanceof UserProfileFragment)){
                    clearBackStack();
                    tvToolBarTitle.setText("User Profile");
                    tvToolBarTitle.setBackgroundResource(0);
                    mFragment = new UserProfileFragment();
                    ft.replace(R.id.container, mFragment, "UserProfileFragment");  //Tag of fragement
                    ft.commit();
                }
                mDrawerLayout.closeDrawers();
                break;
            case AppConstants.LM_SAVE_DEAL:
                if(!(mFragment instanceof SavedDealParentsFragment)){
                    clearBackStack();
                    tvToolBarTitle.setText("Saved Deal");
                    tvToolBarTitle.setBackgroundResource(0);
                    mFragment = new SavedDealParentsFragment();
                    ft.replace(R.id.container, mFragment, "SavedDealParentsFragment");  //Tag of fragement
                    ft.commit();
                }
                mDrawerLayout.closeDrawers();
                break;
            case AppConstants.LM_COMPARE:
                if(!(mFragment instanceof CompareFragment)){
                    clearBackStack();
                    tvToolBarTitle.setText("Comparison");
                    tvToolBarTitle.setBackgroundResource(0);
                    mFragment = new CompareFragment();
                    ft.replace(R.id.container, mFragment, "CompareFragment");  //Tag of fragement
                    ft.commit();
                }
                mDrawerLayout.closeDrawers();
                break;
        }
    }



    public void setTvToolBarTitle(String title){
        tvToolBarTitle.setText(title);
        tvToolBarTitle.setBackgroundResource(0);
    }
    private void clearBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstants.ND_CLOSING_COSTS || requestCode == AppConstants.ND_HOLDING_COSTS
                    || requestCode == AppConstants.ND_RADIANT_COSTS || requestCode == AppConstants.ND_EX2_OPER_PRO_INCOME || requestCode==AppConstants.ND_EX2_OPER_PRO_EXPENSE) {
                if (mFragment instanceof NewDealFragment) {
                    mFragment.onActivityResult(requestCode, resultCode, data);
                }
            }else if (requestCode == TakeImageClass.REQUEST_CODE_TAKE_PICTURE || requestCode == TakeImageClass.REQUEST_CODE_GALLERY
                    || requestCode == TakeImageClass.REQUEST_CODE_CROP_IMAGE) {
                if (resultCode == Activity.RESULT_OK) {
                    switch (requestCode) {
                        case TakeImageClass.REQUEST_CODE_TAKE_PICTURE:
                            if(mFragment instanceof UserProfileFragment){
                                ((UserProfileFragment)mFragment).onActivityResult(requestCode, resultCode, data);
                            }else if(mFragment instanceof NewDealFragment){
                                ((NewDealFragment)mFragment).onActivityResult(requestCode, resultCode, data);
                            }

                            break;
                        case TakeImageClass.REQUEST_CODE_GALLERY:
                            if(mFragment instanceof UserProfileFragment){
                                ((UserProfileFragment)mFragment).onActivityResult(requestCode, resultCode, data);
                            }else if(mFragment instanceof NewDealFragment){
                                ((NewDealFragment)mFragment).onActivityResult(requestCode, resultCode, data);
                            }
                            break;
                        case TakeImageClass.REQUEST_CODE_CROP_IMAGE:
                            if(mFragment instanceof UserProfileFragment){
                                ((UserProfileFragment)mFragment).onActivityResult(requestCode, resultCode, data);
                            }else if(mFragment instanceof NewDealFragment){
                                ((NewDealFragment)mFragment).onActivityResult(requestCode, resultCode, data);
                            }


                            break;
                    }
                }
            }

        }
    }

    public void onPrivacyClick(){
        tvToolBarTitle.setText("Privacy Policy");
        tvToolBarTitle.setBackgroundResource(0);
        ft = getSupportFragmentManager().beginTransaction();
        mFragment = new PrivacyPolicyFragment();
        ft.replace(R.id.container, mFragment, "PrivacyPolicyFragment");  //Tag of fragement
        ft.addToBackStack("PrivacyPolicyFragment");
        ft.commit();
    }

    public void onTermsCondition(){
        tvToolBarTitle.setText("Terms & Conditions");
        tvToolBarTitle.setBackgroundResource(0);
        ft = getSupportFragmentManager().beginTransaction();
        mFragment = new TermsConditionsFragment();
        ft.replace(R.id.container, mFragment, "TermsConditionsFragment");  //Tag of fragement\
        ft.addToBackStack("TermsConditionsFragment");
        ft.commit();
    }

    public void onFaq() {
        tvToolBarTitle.setText("FAQ");
        tvToolBarTitle.setBackgroundResource(0);
        ft = getSupportFragmentManager().beginTransaction();
        mFragment = new FAQFragment();
        ft.replace(R.id.container, mFragment, "FAQFragment");  //Tag of fragement\
        ft.addToBackStack("FAQFragment");
        ft.commit();
    }

    public void onChangePassword(){
        tvToolBarTitle.setText("Change Password");
        tvToolBarTitle.setBackgroundResource(0);
        ft = getSupportFragmentManager().beginTransaction();
        mFragment = new ChangePasswordFragment();
        ft.replace(R.id.container, mFragment, "ChangePasswordFragment");  //Tag of fragement\
        ft.addToBackStack("ChangePasswordFragment");
        ft.commit();
    }


    public void updateLeftMenuData() {
        leftMenuAdapter.notifyDataSetInvalidated();
    }
}
