// Generated by view binder compiler. Do not edit!
package com.fatec.regrowth.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.fatec.regrowth.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MainTasksBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView tasksContent;

  @NonNull
  public final TextView tasksTitle;

  private MainTasksBinding(@NonNull CardView rootView, @NonNull TextView tasksContent,
      @NonNull TextView tasksTitle) {
    this.rootView = rootView;
    this.tasksContent = tasksContent;
    this.tasksTitle = tasksTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static MainTasksBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MainTasksBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.main_tasks, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MainTasksBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.tasksContent;
      TextView tasksContent = ViewBindings.findChildViewById(rootView, id);
      if (tasksContent == null) {
        break missingId;
      }

      id = R.id.tasksTitle;
      TextView tasksTitle = ViewBindings.findChildViewById(rootView, id);
      if (tasksTitle == null) {
        break missingId;
      }

      return new MainTasksBinding((CardView) rootView, tasksContent, tasksTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}