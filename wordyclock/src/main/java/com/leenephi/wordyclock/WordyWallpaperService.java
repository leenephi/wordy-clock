package com.leenephi.wordyclock;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.text.Layout;
import android.text.StaticLayout;
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

    public class WordyViewEngine extends Engine
            implements SharedPreferences.OnSharedPreferenceChangeListener {

        // Theme order = { background, default text, main color text, seconds text }
        private final int BACKGROUND = 0;
        private final int DEFAULT_TEXT = 1;
        private final int COLOR_TEXT = 2;
        private final int SECONDS_TEXT = 3;
        private final String THEMES[][] = {
                {"#111111", "#333333", "#D95B43", "#2E8D95"},    // dark theme
                {"#F7F7F5", "#D5D5D5", "#F45D4C", "#80B990"},   // light theme
                {"#000000", "#222222", "#DCE9BE", "#99173C"}    // vampire theme
        };

        // Number to divide the canvas width by for padding
        private final int PADDING[] = {
                20,     // small - default for left/right padding
                15,     // medium
                10      // large
        };

        private final Handler mHandler;
        private final Runnable mUpdate;

        private int mTheme = 0;
        private int mTopPadding = 0;
        private int mBottomPadding = 0;
        private int mLeftRightPadding;
        private int mNewWidth;

        private boolean mInitialized = false;
        private boolean mVisible = false;

        private StaticLayout mLayout;
        private SharedPreferences mSharedPreferences;
        private TextPaint mDefaultText;

        public WordyViewEngine() {
            mHandler = new Handler();
            mUpdate = new Runnable() {
                @Override
                public void run() {
                    draw();
                }
            };

            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                    WordyWallpaperService.this);
            mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
            mTheme = Integer.parseInt(mSharedPreferences.getString("theme", "0"));

            Typeface montserratRegular = Typeface.createFromAsset(
                    getAssets(), "fonts/montserrat_regular.ttf");

            float fontSizePixels = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());

            mDefaultText = new TextPaint();
            mDefaultText.setColor(Color.parseColor(THEMES[mTheme][DEFAULT_TEXT]));
            mDefaultText.setTypeface(montserratRegular);
            mDefaultText.setTextSize(fontSizePixels);
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
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("theme")) {
                int theme = Integer.parseInt(sharedPreferences.getString(key, "0"));
                updateTheme(theme);
            }
        }

        private void updateTheme(int theme) {
            mTheme = theme;
            mDefaultText.setColor(Color.parseColor(THEMES[mTheme][DEFAULT_TEXT]));
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
                        int topPadding = Integer.parseInt(
                                mSharedPreferences.getString("top_padding", "0"));
                        int bottomPadding = Integer.parseInt(
                                mSharedPreferences.getString("bottom_padding", "0"));

                        int oldWidth = canvas.getWidth();
                        mLeftRightPadding = oldWidth / PADDING[0];
                        mTopPadding = oldWidth / PADDING[topPadding];
                        mBottomPadding = oldWidth / PADDING[bottomPadding];
                        mNewWidth = oldWidth - (2 * mLeftRightPadding);

                        mInitialized = true;
                    }

                    mLayout = new StaticLayout(
                            Wordy.getWords(THEMES[mTheme][COLOR_TEXT],
                                    THEMES[mTheme][SECONDS_TEXT]),
                            mDefaultText, mNewWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);

                    canvas.drawColor(Color.parseColor(THEMES[mTheme][BACKGROUND]));
                    canvas.save();
                    canvas.translate(mLeftRightPadding, mLeftRightPadding);
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
