package com.leenephi.wordyclock;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Created by leenephi on 5/8/14.
 */
public class WordyPreferenceActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new WordyPreferenceFragment()).commit();
    }

    public static class WordyPreferenceFragment extends PreferenceFragment {

        public WordyPreferenceFragment() {

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            this.addPreferencesFromResource(R.xml.preferences);
        }
    }
}
