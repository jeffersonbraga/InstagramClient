package com.theostanton.InstragramClient.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;
import com.theostanton.InstragramClient.R;
import com.theostanton.InstragramClient.helpers.ViewHelper;

/**
 * Created by theo on 02/01/15.
 */
public class FabNew extends ImageButton {

    private static final String TAG = "FabNew";

    private Paint paint;
    private static final float IMAGE_PORTION = 0.8f;

    private float radius;
    private int baseColour = Color.MAGENTA;

    public FabNew(Context context) {
        super(context);
        init(context,null);
    }

    public FabNew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public FabNew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public FabNew(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attributeSet){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);

        radius = getWidth() / 2.0f;

        TypedArray attr = context.obtainStyledAttributes(attributeSet,R.styleable.FabNew);
        if(attr!=null) {
            try {
                baseColour = attr.getColor(R.styleable.FabNew_fab_base_colour, Color.GREEN);
                Log.d(TAG, "baseColour = " + baseColour);
            } finally {
                attr.recycle();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int width = getMeasuredWidth();

        Bitmap bitmap = Bitmap.createBitmap(width,width, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(baseColour);
        mCanvas.drawCircle(width/2,width/2,width/2,paint);

        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        setBackground(drawable);

        setOutlineProvider(ViewHelper.getCicularOutline());
    }
}
