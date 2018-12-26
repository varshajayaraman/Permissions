package com.example.varsh.projectthreeapp3;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class SF extends FragmentActivity implements TitlesFragment.ListSelectionListener {

    public static String[] mTitleArray;
    public static String[] mWebPageArray;


    private final WebPageFragment mWebPageFragment = new WebPageFragment();
    private TitlesFragment titlesFragment = new TitlesFragment();
    private FragmentManager mFragmentManager;
    private FrameLayout mTitleFrameLayout, mWebPageFrameLayout;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "SF";
    private static  int indexPosition=1;

    //Uses Fragment Transactions for dynamically adding fragments for showing points of interests and it's corresponding webpage.
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);


            // Get the string arrays with the titles and qutoes
            mTitleArray = getResources().getStringArray(R.array.Titles);
            mWebPageArray = getResources().getStringArray(R.array.WebPages);

            setContentView(R.layout.fragments);

            // Get references to the TitleFragment and to the WebPageFragment
            mTitleFrameLayout = (FrameLayout) findViewById(R.id.title_fragment_container);
            mWebPageFrameLayout = (FrameLayout) findViewById(R.id.quote_fragment_container);


            // Get a reference to the FragmentManager
            mFragmentManager = getSupportFragmentManager();

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the TitleFragment to the layout

            fragmentTransaction.replace(R.id.title_fragment_container,
                    titlesFragment, "titlesFragment");

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Add a OnBackStackChangedListener to reset the layout when the back stack changes
            mFragmentManager
                    .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                        public void onBackStackChanged() {
                            setLayout();
                        }
                    });


    }

    //Called when orientation is changed from portrait to landscape or vice-versa.
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        Log.d("tag", "config changed");
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;
        Log.i("SF", "inConfigChanged");

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("tag", "Landscape");
            if(!mWebPageFragment.isAdded()){
                Log.i("SF", "titlefragment");
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                mWebPageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            }
            else{
                Log.i("SF", "webpagefragment");
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the WEBPAGE take 2/3's of the layout's width
                mWebPageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
        }
        else{
            Log.d("tag", "Portrait");
            if(!mWebPageFragment.isAdded()){
                Log.i("SF", "titlesFragment");
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                mWebPageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            }
            else{
                Log.i("SF", "webpagefragment");
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));

                // Make the WEBPAGE LAYOUT take 2/3's of the layout's width
                mWebPageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));
            }
        }


    }

    //Called when Backstack contents are modified
    private void setLayout() {

        // Determine whether the QuoteFragment has been added
        if (!mWebPageFragment.isAdded()) {

            // Make the TitleFragment occupy the entire layout
            Log.i("SF", "webpage not added");
            mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mWebPageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Make the TitleLayout take 1/3 of the layout's width
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the QuoteLayout take 2/3's of the layout's width
                mWebPageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
            else{
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        0, MATCH_PARENT));
                mWebPageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));
            }
        }
    }

    // Called when the user selects an item in the points of interest fragment
    @Override
    public void onListSelection(int index) {

        indexPosition = index;
        // If the QuoteFragment has not been added, add it now
        if (!mWebPageFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the QuoteFragment to the layout

            fragmentTransaction.replace(R.id.quote_fragment_container,
                    mWebPageFragment, "webPageFragment");

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

        if (mWebPageFragment.getShownIndex() != index) {

            // Tell the QuoteFragment to show the quote string at position index
            mWebPageFragment.showQuoteAtIndex(index);
            Log.i("SF", "currIdx called with index: "+index);

        }


    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }


}