// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
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

public class CustomDialogQRActivity_ViewBinding implements Unbinder {
  private CustomDialogQRActivity target;

  private View view7f0a03b5;

  private View view7f0a03b7;

  @UiThread
  public CustomDialogQRActivity_ViewBinding(CustomDialogQRActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomDialogQRActivity_ViewBinding(final CustomDialogQRActivity target, View source) {
    this.target = target;

    View view;
    target.txt_count = Utils.findRequiredViewAsType(source, R.id.count, "field 'txt_count'", TextView.class);
    target.message = Utils.findRequiredViewAsType(source, R.id.message, "field 'message'", TextView.class);
    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.txt_Ok, "method 'update'");
    view7f0a03b5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.update();
      }
    });
    view = Utils.findRequiredView(source, R.id.txt_clear_changes_village, "method 'clearChangesList'");
    view7f0a03b7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clearChangesList();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomDialogQRActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txt_count = null;
    target.message = null;
    target.recycler = null;

    view7f0a03b5.setOnClickListener(null);
    view7f0a03b5 = null;
    view7f0a03b7.setOnClickListener(null);
    view7f0a03b7 = null;
  }
}
