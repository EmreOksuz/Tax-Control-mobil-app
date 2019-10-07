package tax.megakent.org.vergicontrol.Main.Activity.TabActivitys;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import tax.megakent.org.vergicontrol.R;

public class TabGeneratorActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private String aboneId,beyanId,gelirId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_generator);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    TabBeyanAyrıntıFragment tab1 = new TabBeyanAyrıntıFragment();
                    Bundle extars = getIntent().getExtras();
                    aboneId = extars.getString("aboneId");
                    beyanId = extars.getString("beyanId");
                    Bundle bundle = new Bundle();
                    bundle.putString("aboneId",aboneId);
                    bundle.putString("beyanId",beyanId);
                    tab1.setArguments(bundle);
                    return tab1;
                case 1:
                    TabBeyanTahakkukFragment tab2 = new TabBeyanTahakkukFragment();
                    Bundle extars2 = getIntent().getExtras();
                    aboneId = extars2.getString("aboneId");
                    gelirId = extars2.getString("gelirId");
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("aboneId",aboneId);
                    bundle2.putString("gelirId",gelirId);
                    tab2.setArguments(bundle2);
                    return tab2;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Beyan Ayrıntı";
                case 1:
                    return "Beyan Tahakkuk";
            }
            return null;
        }
    }
}
