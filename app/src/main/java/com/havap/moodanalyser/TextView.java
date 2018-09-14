package com.havap.moodanalyser;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by ALI on 09-09-2018.
 */

class Mytextview extends android.widget.TextView{

        public Mytextview(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        public Mytextview(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public Mytextview(Context context) {
            super(context);
            init();
        }

        private void init() {
            if (!isInEditMode()) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/chantal.ttf");
                setTypeface(tf);
            }
        }

}
