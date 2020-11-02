package com.example.friendscircle.behavior;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.DescendantOffsetUtils;

public class CustomFloatingBehavior extends CoordinatorLayout.Behavior<View> {
    private boolean autoHideEnabled;

    public CustomFloatingBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull final View child, @NonNull View dependency) {
        ((AppBarLayout) dependency).addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int y) {
                int distance = appBarLayout.getTotalScrollRange() - Math.abs(y);
                if (distance == 0) {
                    child.setVisibility(View.INVISIBLE);
                }else{
                    child.setVisibility(View.VISIBLE);
                }
            }
        });
        return false;

    }


    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View
            child, @NonNull View dependency) {
        return dependency instanceof AppBarLayout;
    }
}
