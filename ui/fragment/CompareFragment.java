package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import org.rehab.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class CompareFragment extends Fragment {

    @BindView(R.id.wv_compare)
    WebView wvCompare;
    private final String widgetData="<style>@import url(http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,700);.HOMES-simple-search-widget{font: 12px/1.5em 'Open Sans', Arial, sans-serif;color:#484848;padding: 7px 10px 10px;overflow: hidden;clear:both;}.HOMES-simple-search-widget::before,.HOMES-simple-search-widget::after{content: '';display: table;width:100%;clear:both;}.HOMES-simple-search-widget h1{color:#0054a0;margin:0;font-size:24px;line-height:1em;font-weight:700;}.HOMES-simple-search-widget .simple-search-frame{border:0;border-bottom: 1px solid #eeeae9;margin: 0 0 .5em;overflow: hidden;height: 16.5em;}.HOMES-simple-search-widget a{font-weight: 700;color:#0054a0;text-decoration:none;font-size:.75em;max-width:240px;-moz-box-sizing:border-box;box-sizing:border-box;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;}.HOMES-simple-search-widget a:hover{text-decoration: underline;}.HOMES-simple-search-widget .logo{float:right;background: transparent url(http://cdn1.static-homes.com/media/portalimgcache/widget/builder/logo-widget.png) 0 0 no-repeat;width:139px;height: 22px;overflow: hidden;direction: ltr;text-indent: -3000px;}.HOMES-simple-search-widget .footer a{margin:0;}.HOMES-simple-search-widget .footer .link{max-width: 50%;display: block;}.HOMES-simple-search-widget .simple-search-frame{height: 9.6em;}</style><!--[if lte IE 8]><style>.HOMES-simple-search-widget h1,.HOMES-simple-search-widget a{font-family:\"Open Sans Bold\", Arial, sans-serif;}</style><![endif]--><div class=\"HOMES-simple-search-widget\"><h1>Search Homes</h1><iframe src=\"http://www.homes.com/widget/simple-search/frame/?text_color=%230054a0&listing_status=FOR%20SALE%2CFOR%20RENT&cobrand=&location=Norfolk%2C%20VA\" class=\"simple-search-frame\" width=\"100%\" seamless frameborder=\"0\"></iframe><a href=\"http://www.homes.com/widgets/\" title=\"Homes.com\" class=\"logo\">Powered By Homes.com</a><script src=\"http://www.homes.com/widget/simple-search/remote.js?text_color=%230054a0&listing_status=FOR%20SALE%2CFOR%20RENT&cobrand=&location=Norfolk%2C%20VA\" type=\"text/javascript\"></script></div>";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_compare,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wvCompare.getSettings().setJavaScriptEnabled(true);
        wvCompare.loadDataWithBaseURL("", widgetData, "text/html", "UTF-8", "");
    }
}
