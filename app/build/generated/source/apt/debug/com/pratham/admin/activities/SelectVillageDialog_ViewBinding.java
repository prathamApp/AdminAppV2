// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectVillageDialog_ViewBinding implements Unbinder {
  private SelectVillageDialog target;

  private View view7f0a03c8;

  private View view7f0a0097;

  private View view7f0a03cd;

  @UiThread
  public SelectVillageDialog_ViewBinding(SelectVillageDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectVillageDialog_ViewBinding(final SelectVillageDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.txt_clear_changes_village, "field 'clear_changes' and method 'clearChanges'");
    target.clear_changes = Utils.castView(view, R.id.txt_clear_changes_village, "field 'clear_changes'", TextView.class);
    view7f0a03c8 = view;
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
    target.txt_message_village = Utils.findRequiredViewAsType(source, R.id.txt_message_village, "field 'txt_message_village'", TextView.class);
    target.flowLayout = Utils.findRequiredViewAsType(source, R.id.flowLayout, "field 'flowLayout'", GridLayout.class);
    view = Utils.findRequiredView(source, R.id.txt_ok_village, "method 'ok'");
    view7f0a03cd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ok();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectVillageDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.clear_changes = null;
    target.btn_close = null;
    target.txt_message_village = null;
    target.flowLayout = null;

    view7f0a03c8.setOnClickListener(null);
    view7f0a03c8 = null;
    view7f0a0097.setOnClickListener(null);
    view7f0a0097 = null;
    view7f0a03cd.setOnClickListener(null);
    view7f0a03cd = null;
  }
}
