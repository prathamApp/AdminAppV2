//
// DO NOT EDIT THIS FILE.
// Generated using AndroidAnnotations 4.7.0.
// 
// You can create a larger work that contains this file and distribute that work under terms of your choice.
//

package com.pratham.admin.ui.home;

import java.util.HashMap;
import java.util.Map;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.pratham.admin.R;
import org.androidannotations.api.bean.BeanHolder;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@SuppressLint({
    "NonConstantResourceId"
})
public final class HomeFragment_
    extends com.pratham.admin.ui.home.HomeFragment
    implements BeanHolder, HasViews, OnViewChangedListener
{
    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private View contentView_;
    private final Map<Class<?> , Object> beans_ = new HashMap<Class<?> , Object>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    @Override
    public<T extends View> T internalFindViewById(int id) {
        return ((T)((contentView_ == null)?null:contentView_.findViewById(id)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(R.layout.fragment_home, container, false);
        }
        return contentView_;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        contentView_ = null;
        assigendToMe = null;
        rl_homeLayout = null;
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static HomeFragment_.FragmentBuilder_ builder() {
        return new HomeFragment_.FragmentBuilder_();
    }

    @Override
    public<T> T getBean(Class<T> key) {
        return ((T) beans_.get(key));
    }

    @Override
    public<T> void putBean(Class<T> key, T value) {
        beans_.put(key, value);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        this.assigendToMe = hasViews.internalFindViewById(R.id.assigned_to_me);
        this.rl_homeLayout = hasViews.internalFindViewById(R.id.rl_homeLayout);
        View view_replaceTab = hasViews.internalFindViewById(R.id.replaceTab);
        View view_reportLost = hasViews.internalFindViewById(R.id.reportLost);

        if (this.assigendToMe!= null) {
            this.assigendToMe.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    HomeFragment_.this.setAssigendToMe();
                }
            }
            );
        }
        if (view_replaceTab!= null) {
            view_replaceTab.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    HomeFragment_.this.replaceTab();
                }
            }
            );
        }
        if (view_reportLost!= null) {
            view_reportLost.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    HomeFragment_.this.reportLost();
                }
            }
            );
        }
        init();
    }

    public static class FragmentBuilder_
        extends FragmentBuilder<HomeFragment_.FragmentBuilder_, com.pratham.admin.ui.home.HomeFragment>
    {

        @Override
        public com.pratham.admin.ui.home.HomeFragment build() {
            HomeFragment_ fragment_ = new HomeFragment_();
            fragment_.setArguments(args);
            return fragment_;
        }
    }
}
