//
// DO NOT EDIT THIS FILE.
// Generated using AndroidAnnotations 4.7.0.
// 
// You can create a larger work that contains this file and distribute that work under terms of your choice.
//

package com.pratham.admin.ui.blockLeader.home;

import java.util.HashMap;
import java.util.Map;
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

public final class BlockLeaderHomeFragment_
    extends com.pratham.admin.ui.blockLeader.home.BlockLeaderHomeFragment
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
            contentView_ = inflater.inflate(R.layout.fragment_block_leader_home, container, false);
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
        tv_desputedCount = null;
        tv_assignedCount = null;
        tv_unassignedCount = null;
        tv_totalNoOfTabs = null;
        switchMaterial = null;
        rl_tabStatusTwo = null;
        rl_tabStatus = null;
        ll_spinner = null;
        rl_spinnerParent = null;
        rl_spinnerParentTwo = null;
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static BlockLeaderHomeFragment_.FragmentBuilder_ builder() {
        return new BlockLeaderHomeFragment_.FragmentBuilder_();
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
        this.tv_desputedCount = hasViews.internalFindViewById(R.id.tv_desputed_tab);
        this.tv_assignedCount = hasViews.internalFindViewById(R.id.tv_assigned_tab);
        this.tv_unassignedCount = hasViews.internalFindViewById(R.id.tv_unassigned_tab);
        this.tv_totalNoOfTabs = hasViews.internalFindViewById(R.id.tv_totalNoOfTabs);
        this.switchMaterial = hasViews.internalFindViewById(R.id.switch_vendor);
        this.rl_tabStatusTwo = hasViews.internalFindViewById(R.id.rl_tabStatusTwo);
        this.rl_tabStatus = hasViews.internalFindViewById(R.id.rl_tabStatus);
        this.ll_spinner = hasViews.internalFindViewById(R.id.ll_spinner);
        this.rl_spinnerParent = hasViews.internalFindViewById(R.id.rl_spinnerParent);
        this.rl_spinnerParentTwo = hasViews.internalFindViewById(R.id.rl_spinnerParentTwo);
        View view_btn_go = hasViews.internalFindViewById(R.id.btn_go);
        View view_btn_refresh = hasViews.internalFindViewById(R.id.btn_refresh);

        if (view_btn_go!= null) {
            view_btn_go.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    BlockLeaderHomeFragment_.this.loadCountBlockWise();
                }
            }
            );
        }
        if (view_btn_refresh!= null) {
            view_btn_refresh.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    BlockLeaderHomeFragment_.this.refreshTabCount();
                }
            }
            );
        }
        init();
    }

    public static class FragmentBuilder_
        extends FragmentBuilder<BlockLeaderHomeFragment_.FragmentBuilder_, com.pratham.admin.ui.blockLeader.home.BlockLeaderHomeFragment>
    {

        @Override
        public com.pratham.admin.ui.blockLeader.home.BlockLeaderHomeFragment build() {
            BlockLeaderHomeFragment_ fragment_ = new BlockLeaderHomeFragment_();
            fragment_.setArguments(args);
            return fragment_;
        }
    }
}
