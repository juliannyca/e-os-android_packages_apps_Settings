/*
 * Copyright (C) 2019-2021 E FOUNDATION
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings;

import android.content.Intent;
import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;

import com.android.internal.logging.nano.MetricsProto.MetricsEvent;

public class Contributors extends SettingsPreferenceFragment {

    private static final String LOG_TAG = "Contributors";
    private static final String URL_E_CONTRIBUTORS = "https://e.foundation/about-e/#people";
    private static final String KEY_E_CONTRIBUTORS = "e_contributors";
    private static final String URL_E_SUPPORTERS = "https://e.foundation/donate/#earlybackers";
    private static final String KEY_E_SUPPORTERS = "e_supporters";

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.contributors);
    }

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.CONTRIBUTORS;
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getKey().equals(KEY_E_CONTRIBUTORS)
         || preference.getKey().equals(KEY_E_SUPPORTERS)) {
            final Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            if (preference.getKey().equals(KEY_E_CONTRIBUTORS)) {
              intent.setData(Uri.parse(URL_E_CONTRIBUTORS));
            } else if (preference.getKey().equals(KEY_E_SUPPORTERS)) {
              intent.setData(Uri.parse(URL_E_SUPPORTERS));
            }

            try {
                startActivity(intent);
            } catch (Exception e) {
                Log.e(LOG_TAG, "Unable to start activity " + intent.toString());
            }
        }
        return super.onPreferenceTreeClick(preference);
    }
}
