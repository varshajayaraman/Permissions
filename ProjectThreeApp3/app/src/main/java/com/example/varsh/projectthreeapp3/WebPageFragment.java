package com.example.varsh.projectthreeapp3;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

//Several Activity and Fragment lifecycle methods are instrumented to emit LogCat output
//so you can follow the class' lifecycle
public class WebPageFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "WebPageFragment";

    private TextView mQuoteView = null;
    private int mCurrIdx = -1;
    private int mQuoteArrLen;
    private WebView mWebView;

    int getShownIndex() {
        return mCurrIdx;
    }

    // Show the webpage of location chosen
    void showQuoteAtIndex(int newIndex) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onShowQ()");
        if (newIndex < 0 || newIndex >= mQuoteArrLen) {
            Log.i("WebPageFragment", "currId: Returning");
            return;
        }
        mCurrIdx = newIndex;

        Log.i("WebPageFragment", "currIdx: "+mCurrIdx);

        if(mWebView == null){
            Log.i("WebPageFragment", "webview null");
        }
        else{
            Log.i("WebPageFragment", " currI webview not null");

        }
        String url = SF.mWebPageArray[mCurrIdx];
        mWebView.loadUrl(url);


    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    // Creates webview for the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

        // Inflate the layout defined in quote_fragment.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup

        View v = inflater.inflate(R.layout.quote_fragment, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview);


        if (mWebView == null) {
            Log.i("WebPageFragment", "webview null");
        } else {
            Log.i("WebPageFragment", " currI webview not null");

        }
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        return v;

    }

    // Remembers state and loads the URL appropriately.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);


        mQuoteArrLen = SF.mWebPageArray.length;
        if(mCurrIdx != -1){
            mWebView.loadUrl(SF.mWebPageArray[mCurrIdx]);
        }
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }


    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }


    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }

}
