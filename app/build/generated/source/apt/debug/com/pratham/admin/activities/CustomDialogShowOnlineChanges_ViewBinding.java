// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.LinearLayout;
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

public class CustomDialogShowOnlineChanges_ViewBinding implements Unbinder {
  private CustomDialogShowOnlineChanges target;

  private View view7f0a03a3;

  private View view7f0a03a5;

  @UiThread
  public CustomDialogShowOnlineChanges_ViewBinding(CustomDialogShowOnlineChanges target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomDialogShowOnlineChanges_ViewBinding(final CustomDialogShowOnlineChanges target,
      View source) {
    this.target = target;

    View view;
    target.txt_count = Utils.findRequiredViewAsType(source, R.id.count, "field 'txt_count'", TextView.class);
    target.message = Utils.findRequiredViewAsType(source, R.id.message, "field 'message'", TextView.class);
    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.txt_Ok, "method 'update'");
    view7f0a03a3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.update();
      }
    });
    view = Utils.findRequiredView(source, R.id.txt_clear_changes_village, "method 'clearChangesList'");
    view7f0a03a5 = view;
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
    CustomDialogShowOnlineChanges target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txt_count = null;
    target.message = null;
    target.recycler = null;
    target.title = null;

    view7f0a03a3.setOnClickListener(null);
    view7f0a03a3 = null;
    view7f0a03a5.setOnClickListener(null);
    view7f0a03a5 = null;
  }
}
