// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SwapStudentsActivity_ViewBinding implements Unbinder {
  private SwapStudentsActivity target;

  private View view7f0a00a3;

  private View view7f0a0097;

  private View view7f0a0098;

  @UiThread
  public SwapStudentsActivity_ViewBinding(SwapStudentsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SwapStudentsActivity_ViewBinding(final SwapStudentsActivity target, View source) {
    this.target = target;

    View view;
    target.swapStudParentLayout = Utils.findRequiredViewAsType(source, R.id.swapStudParentLayout, "field 'swapStudParentLayout'", LinearLayout.class);
    target.leftSideLayout = Utils.findRequiredViewAsType(source, R.id.leftSide, "field 'leftSideLayout'", RelativeLayout.class);
    target.rightSideLayout = Utils.findRequiredViewAsType(source, R.id.rightSide, "field 'rightSideLayout'", RelativeLayout.class);
    target.recyclerView_leftSide = Utils.findRequiredViewAsType(source, R.id.recyclerView_leftSide, "field 'recyclerView_leftSide'", RecyclerView.class);
    target.recyclerView_rightSide = Utils.findRequiredViewAsType(source, R.id.recyclerView_rightSide, "field 'recyclerView_rightSide'", RecyclerView.class);
    target.spinner_group_left = Utils.findRequiredViewAsType(source, R.id.spinner_group_left, "field 'spinner_group_left'", Spinner.class);
    target.spinner_student_rightSide = Utils.findRequiredViewAsType(source, R.id.spinner_student_rightSide, "field 'spinner_student_rightSide'", Spinner.class);
    target.spinner_village = Utils.findRequiredViewAsType(source, R.id.spinner_village, "field 'spinner_village'", Spinner.class);
    target.tv_programName = Utils.findRequiredViewAsType(source, R.id.tv_selProg, "field 'tv_programName'", TextView.class);
    target.stateName = Utils.findRequiredViewAsType(source, R.id.tv_selState, "field 'stateName'", TextView.class);
    target.villageName = Utils.findRequiredViewAsType(source, R.id.tv_selVillage, "field 'villageName'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_swap_and_push, "field 'btn_swap_and_push' and method 'pushToDB'");
    target.btn_swap_and_push = Utils.castView(view, R.id.btn_swap_and_push, "field 'btn_swap_and_push'", Button.class);
    view7f0a00a3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.pushToDB();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_move_to_left, "method 'moveLeft'");
    view7f0a0097 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.moveLeft();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_move_to_right, "method 'moveRight'");
    view7f0a0098 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.moveRight();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SwapStudentsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.swapStudParentLayout = null;
    target.leftSideLayout = null;
    target.rightSideLayout = null;
    target.recyclerView_leftSide = null;
    target.recyclerView_rightSide = null;
    target.spinner_group_left = null;
    target.spinner_student_rightSide = null;
    target.spinner_village = null;
    target.tv_programName = null;
    target.stateName = null;
    target.villageName = null;
    target.btn_swap_and_push = null;

    view7f0a00a3.setOnClickListener(null);
    view7f0a00a3 = null;
    view7f0a0097.setOnClickListener(null);
    view7f0a0097 = null;
    view7f0a0098.setOnClickListener(null);
    view7f0a0098 = null;
  }
}
