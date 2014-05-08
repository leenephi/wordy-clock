package com.leenephi.wordyclock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.SurfaceHolder;

/**
 * Created by smithsonln on 5/7/14.
 */
public class WordyWallpaperService extends WallpaperService {

    @Override
    public Engine onCreateEngine() {
        return new WordyViewEngine();
    }

    public class WordyViewEngine extends Engine {

        private boolean mInitialized = false;
        private int mPadding;
        private int mNewWidth;
        private DynamicLayout mLayout;

        private boolean mVisible = false;

        private final Handler mHandler;
        private final Runnable mUpdate;

        private TextPaint mDefault;

        public WordyViewEngine() {
            mHandler = new Handler();
            mUpdate = new Runnable() {
                @Override
                public void run() {
                    draw();
                }
            };

            Typeface montserratRegular = Typeface.createFromAsset(
                    getAssets(), "fonts/montserrat_regular.ttf");

            float fontSizePixels = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());

            mDefault = new TextPaint();
            mDefault.setColor(Color.parseColor("#333333"));
            mDefault.setTypeface(montserratRegular);
            mDefault.setTextSize(fontSizePixels);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            mVisible = visible;
            if (mVisible) {
                draw();
            } else {
                mHandler.removeCallbacks(mUpdate);
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            draw();
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            mVisible = false;
            mHandler.removeCallbacks(mUpdate);
        }

        private void draw() {
            final SurfaceHolder holder = this.getSurfaceHolder();
            Canvas canvas = null;

            try {
                canvas = holder.lockCanvas();

                if (canvas != null) {
                    if (!mInitialized) {
                        int oldWidth = canvas.getWidth();
                        mPadding = oldWidth / 20;
                        mNewWidth = oldWidth - (2 * mPadding);

                        mInitialized = true;
                    }

                    mLayout = new DynamicLayout(
                            Wordy.getWords(), mDefault, mNewWidth,
                            Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);

                    canvas.drawColor(Color.parseColor("#111111"));
                    canvas.save();
                    canvas.translate(mPadding, mPadding);
                    mLayout.draw(canvas);
                    canvas.restore();
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }

            mHandler.removeCallbacks(mUpdate);

            if (mVisible) {
                mHandler.postDelayed(mUpdate, Wordy.getMillisLeft());
            }
        }
    }
}
