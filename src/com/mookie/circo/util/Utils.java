package com.mookie.circo.util;


import android.content.Context;
import android.content.pm.PackageManager;


/**
 * Class for miscellaneous convenience methods.
 *
 * @author mgscr
 */
public class Utils
{
    /**
     * Checks to see if the specified package is installed.<br>
     * Return true if it is or false if it's not installed.
     *
     * @param packageName name of package to look for
     * @param context Context used with PackageManager to check for specified package
     * @return true if specified package is installed
     */
    public static boolean isPackageInstalled(String packageName, Context context)
    {
        PackageManager pm = context.getPackageManager();

        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}