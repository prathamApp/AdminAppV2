// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

public class CustomDialog_ViewBinding implements Unbinder {
  private CustomDialog target;

  private View view7f0a00a1;

  private View view7f0a00da;

  private View view7f0a0097;

  private View view7f0a03c7;

  @UiThread
  public CustomDialog_ViewBinding(CustomDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomDialog_ViewBinding(final CustomDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_pushData, "field 'btn_pushData' and method 'uploadChanges'");
    target.btn_pushData = Utils.castView(view, R.id.btn_pushData, "field 'btn_pushData'", Button.class);
    view7f0a00a1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.uploadChanges();
      }
    });
    view = Utils.findRequiredView(source, R.id.clear_changes, "field 'clear_changes' and method 'clearChanges'");
    target.clear_changes = Utils.castView(view, R.id.clear_changes, "field 'clear_changes'", TextView.class);
    view7f0a00da = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clearChanges();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_close_village, "field 'btn_close' and method 'closeDialog'");
    target.btn_close = Utils.castView(view, R.id.btn_close_village, "field 'btn_close'", ImageButton.class);
    view7f0a0097 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.closeDialog();
      }
    });
    view = Utils.findRequiredView(source, R.id.txt_cancel, "field 'txt_cancel' and method 'skip'");
    target.txt_cancel = Utils.castView(view, R.id.txt_cancel, "field 'txt_cancel'", TextView.class);
    view7f0a03c7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.skip();
      }
    });
    target.txt_count = Utils.findRequiredViewAsType(source, R.id.txt_count_village, "field 'txt_count'", TextView.class);
    target.txt_message = Utils.findRequiredViewAsType(source, R.id.txt_message_village, "field 'txt_message'", TextView.class);
    target.recyclerView_unUploaded = Utils.findRequiredViewAsType(source, R.id.flowLayout, "field 'recyclerView_unUploaded'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_pushData = null;
    target.clear_changes = null;
    target.btn_close = null;
    target.txt_cancel = null;
    target.txt_count = null;
    target.txt_message = null;
    target.recyclerView_unUploaded = null;

    view7f0a00a1.setOnClickListener(null);
    view7f0a00a1 = null;
    view7f0a00da.setOnClickListener(null);
    view7f0a00da = null;
    view7f0a0097.setOnClickListener(null);
    view7f0a0097 = null;
    view7f0a03c7.setOnClickListener(null);
    view7f0a03c7 = null;
  }
}
