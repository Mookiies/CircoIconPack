package com.mookie.circo.fragment;


import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.mookie.circo.R;
import com.mookie.circo.adapter.ApplyLauncherAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment handling clicks on launcher apply buttons. If installed sends to launcher apply page,
 * if not installed sends to google play store page, and if not supported displays toast.
 *
 * @author mgscr
 */
public class ApplyLauncherFragment extends Fragment {

    GridView gridView;
    final List<Integer> applyLauncher = new ArrayList<>();

    // Background layout that gets inflated behind the list view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container_launcher,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.apply_launcher_behind, null);
        gridView = (GridView) view.findViewById(R.id.grid);
        return view;
    }

    // Starts when the MainFragment is launched
    // Fills with all the the launchers with the apply options
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //add launchers to view in alphabetical order
        applyLauncher.add(ApplyLauncherAdapter.ACTION);
        applyLauncher.add(ApplyLauncherAdapter.ADW);
        applyLauncher.add(ApplyLauncherAdapter.APEX);
        applyLauncher.add(ApplyLauncherAdapter.ATOM);
        applyLauncher.add(ApplyLauncherAdapter.AVIATE);
        applyLauncher.add(ApplyLauncherAdapter.GO);
        applyLauncher.add(ApplyLauncherAdapter.HOLO);
        applyLauncher.add(ApplyLauncherAdapter.NEXT);
        applyLauncher.add(ApplyLauncherAdapter.NOVA);
        applyLauncher.add(ApplyLauncherAdapter.SMART);

        ApplyLauncherAdapter adapter = new ApplyLauncherAdapter(getActivity(),
                applyLauncher);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                final String ACTION_APPLY_ICON_THEME = "com.teslacoilsw.launcher.APPLY_ICON_THEME";
                final String NOVA_PACKAGE = "com.teslacoilsw.launcher";
                final String EXTRA_ICON_THEME_PACKAGE = "com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE";
                final String EXTRA_ICON_THEME_TYPE = "com.teslacoilsw.launcher.extra.ICON_THEME_TYPE";
                final String APEX_ACTION_SET_THEME = "com.anddoes.launcher.SET_THEME";
                final String APEX_EXTRA_PACKAGE_NAME = "com.anddoes.launcher.THEME_PACKAGE_NAME";
                final String AVIATE_ACTION_SET_THEME = "com.tul.aviate.SET_THEME";
                final String AVIATE_EXTRA_PACKAGE_NAME = "THEME_PACKAGE";

                switch (position) {
                    case ApplyLauncherAdapter.ACTION:
                        Intent al = getActivity().getPackageManager().getLaunchIntentForPackage(
                                "com.actionlauncher.playstore");
                        if (al != null) {
                            String packageName = getResources().getString(R.string.package_name);
                            al.putExtra("apply_icon_pack", packageName);
                            startActivity(al);
                            Toast applied = Toast.makeText(getActivity().getBaseContext(),
                                    getResources().getString(R.string.finish_action_apply),
                                    Toast.LENGTH_LONG);
                            applied.show();
                        } else {
                            notInstalledHandler(getResources().getString(R.string.al_market));
                        }
                        break;
                    case ApplyLauncherAdapter.ADW:
                        Intent adw = new Intent("org.adw.launcher.SET_THEME");
                        adw.putExtra("org.adw.launcher.theme.NAME",
                                getResources().getString(R.string.package_name));
                        try {
                            startActivity(adw);
                        } catch (ActivityNotFoundException e) {
                            notInstalledHandler(getResources().getString(R.string.adw_market));
                        }
                        break;
                    case ApplyLauncherAdapter.APEX:
                        Intent apex = new Intent(APEX_ACTION_SET_THEME);
                        apex.putExtra(APEX_EXTRA_PACKAGE_NAME, getActivity().getPackageName());
                        apex.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        try {
                            startActivity(apex);
                        } catch (ActivityNotFoundException e) {
                            notInstalledHandler(getResources().getString(R.string.apex_market));
                        }
                        break;
                    case ApplyLauncherAdapter.ATOM: //not supported
                        Toast atomNotSupported = Toast
                                .makeText(getActivity().getBaseContext(), getResources().getString
                                        (R.string.coming_soon), Toast.LENGTH_SHORT);
                        atomNotSupported.show();
                        break;
                    case ApplyLauncherAdapter.AVIATE:
                        Intent intent = new Intent(AVIATE_ACTION_SET_THEME);
                        intent.putExtra(AVIATE_EXTRA_PACKAGE_NAME,
                                getResources().getString(R.string.package_name));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            notInstalledHandler(getResources().getString(R.string.aviate_market));
                        }
                        break;
                    case ApplyLauncherAdapter.GO: //not supported
                        Toast goNotSupported = Toast
                                .makeText(getActivity().getBaseContext(), getResources().getString
                                        (R.string.coming_soon), Toast.LENGTH_SHORT);
                        goNotSupported.show();
                        break;
                    case ApplyLauncherAdapter.HOLO:
                        Intent holo = new Intent(Intent.ACTION_MAIN);
                        holo.setComponent(new ComponentName("com.mobint.hololauncher",
                                "com.mobint.hololauncher.Launcher"));
                        try {
                            startActivity(holo);

                            Toast applied = Toast.makeText(getActivity().getBaseContext(),
                                    getResources().getString(R.string.finish_holo_apply),
                                    Toast.LENGTH_LONG);
                            applied.show();
                        } catch (ActivityNotFoundException e) {
                            notInstalledHandler(getResources().getString(R.string.holo_market));
                        }
                        break;
                    case ApplyLauncherAdapter.NEXT: //not supported
                        Toast nextNotSupported = Toast
                                .makeText(getActivity().getBaseContext(), getResources().getString
                                        (R.string.coming_soon), Toast.LENGTH_SHORT);
                        nextNotSupported.show();
                        break;
                    case ApplyLauncherAdapter.NOVA:
                        Intent nova = new Intent(ACTION_APPLY_ICON_THEME);
                        nova.setPackage(NOVA_PACKAGE);
                        nova.putExtra(EXTRA_ICON_THEME_TYPE, "GO");
                        nova.putExtra(EXTRA_ICON_THEME_PACKAGE,
                                getResources().getString(R.string.package_name));
                        try {
                            startActivity(nova);
                        } catch (ActivityNotFoundException e) {
                            notInstalledHandler(getResources().getString(R.string.nova_market));
                        }
                        break;
                    case ApplyLauncherAdapter.SMART:
                        Intent smart = new Intent("ginlemon.smartlauncher.setGSLTHEME");
                        smart.putExtra("package", getResources().getString(R.string.package_name));
                        smart.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        try {
                            startActivity(smart);
                        } catch (ActivityNotFoundException e) {
                            notInstalledHandler(getResources().getString(R.string.smart_market));
                        }
                        break;
                }
            }
        });
    }

    /**
     * Displaysa a {@link Toast} with short length of given toastMessage to inform user that
     * the given laucnher was no installed.
     *
     * @param toastMessage message of toast to be displayed
     */
    private void notInstalledHandler(String toastMessage) {
        Toast failedLauncher = Toast.makeText(getActivity().getBaseContext(), toastMessage, Toast.LENGTH_SHORT);
        failedLauncher.show();
    }
}