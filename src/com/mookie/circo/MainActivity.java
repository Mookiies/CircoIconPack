/*
Copyright 2017, mookie

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.mookie.circo;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.mookie.circo.fragment.ApplyLauncherFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;

import java.util.Locale;

/**
 * Main screen of icon pack. Offers selection of all supported launchers to apply
 * the icon pack.
 *
 * @author mgscr
 */
public class MainActivity extends FragmentActivity {

    /**
     * Initiates the launcher selection screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_launcher_main);

        ActionBar actionBar = getActionBar();
        Resources res = MainActivity.this.getResources();
        actionBar.setBackgroundDrawable(new ColorDrawable(res.getColor(R.color.icon_dark_gray)));

        //Sets the action bar text color by using html font tag with color tag
        //htmlColor is the color converted to work with html
        int actionFontColor = getResources().getColor(R.color.white);
        String htmlColor =
                String.format(Locale.US, "#%06X", (0xFFFFFF & Color.argb(0, Color.red(actionFontColor),
                        Color.green(actionFontColor), Color.blue(actionFontColor))));
        getActionBar().setTitle(Html.fromHtml("<font color=\""+ htmlColor + "\">"
                + getString(R.string.app_name) + "</font>"));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_launcher,
                        new ApplyLauncherFragment())
                .commit();

    }

}
