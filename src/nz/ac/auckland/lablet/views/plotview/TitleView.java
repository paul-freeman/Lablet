/*
 * Copyright 2014.
 * Distributed under the terms of the GPLv3 License.
 *
 * Authors:
 *      Clemens Zeidler <czei002@aucklanduni.ac.nz>
 */
package nz.ac.auckland.lablet.views.plotview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.ViewGroup;


public class TitleView extends ViewGroup {
    private String title = "";
    private Paint penPaint = new Paint();

    final private int TOP_OFFSET = 3;

    public TitleView(Context context) {
        super(context);

        setWillNotDraw(false);

        penPaint.setColor(PlotView.DEFAULT_PEN_COLOR);
        penPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPreferredHeight() {
        if (title.equals(""))
            return 0;
        return penPaint.descent() - penPaint.ascent() + TOP_OFFSET;
    }

    @Override
    protected void onLayout(boolean b, int i, int i2, int i3, int i4) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        float titleLength = penPaint.measureText(title, 0, title.length());
        canvas.drawText(title, 0, title.length(), (getWidth() - titleLength) / 2,
                getPreferredHeight() - penPaint.descent(), penPaint);
    }
}
