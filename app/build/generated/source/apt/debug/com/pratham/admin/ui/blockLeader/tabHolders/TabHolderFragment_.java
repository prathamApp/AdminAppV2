//
// DO NOT EDIT THIS FILE.
// Generated using AndroidAnnotations 4.7.0.
// 
// You can create a larger work that contains this file and distribute that work under terms of your choice.
//

package com.pratham.admin.ui.blockLeader.tabHolders;

import java.util.HashMap;
import java.util.Map;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
public final class TabHolderFragment_
    extends com.pratham.admin.ui.blockLeader.tabHolders.TabHolderFragment
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
            contentView_ = inflater.inflate(R.layout.fragment_tab_holder, container, false);
        }
        return contentView_;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        contentView_ = null;
        spinner_program = null;
        spinner_state = null;
        spinner_block = null;
        spinner_tabHolder = null;
        rv_tabHolder = null;
        ll_spinnerOne = null;
        ll_spinnerTwo = null;
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static TabHolderFragment_.FragmentBuilder_ builder() {
        return new TabHolderFragment_.FragmentBuilder_();
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
        this.spinner_program = hasViews.internalFindViewById(R.id.spinner_program);
        this.spinner_state = hasViews.internalFindViewById(R.id.spinner_state);
        this.spinner_block = hasViews.internalFindViewById(R.id.spinner_block);
        this.spinner_tabHolder = hasViews.internalFindViewById(R.id.spinner_tabHolder);
        this.rv_tabHolder = hasViews.internalFindViewById(R.id.rv_tabHolder);
        this.ll_spinnerOne = hasViews.internalFindViewById(R.id.ll_spinnerOne);
        this.ll_spinnerTwo = hasViews.internalFindViewById(R.id.ll_spinnerTwo);
        init();
    }

    public static class FragmentBuilder_
        extends FragmentBuilder<TabHolderFragment_.FragmentBuilder_, com.pratham.admin.ui.blockLeader.tabHolders.TabHolderFragment>
    {

        @Override
        public com.pratham.admin.ui.blockLeader.tabHolders.TabHolderFragment build() {
            TabHolderFragment_ fragment_ = new TabHolderFragment_();
            fragment_.setArguments(args);
            return fragment_;
        }
    }
}
