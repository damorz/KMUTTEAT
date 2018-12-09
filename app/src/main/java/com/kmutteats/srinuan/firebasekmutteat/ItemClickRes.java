package com.kmutteats.srinuan.firebasekmutteat;

import android.view.MotionEvent;
import android.view.View;

public interface ItemClickRes
{
    void onClick (View view, int position, boolean isLongClick, MotionEvent event);
}
