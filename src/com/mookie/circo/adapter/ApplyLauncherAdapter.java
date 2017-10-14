package com.mookie.circo.adapter;

import java.util.List;

import com.mookie.circo.R;
import com.mookie.circo.util.*;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter to handle the launcher selections. Sets up all adapters in view and sets install status.
 *
 * @author mgscr
 * @see android.widget.BaseAdapter
 */
public class ApplyLauncherAdapter extends BaseAdapter
{
    private ColorMatrixColorFilter grayscaleFilter;
    private Context context;
    private List<Integer> gridItem;
    private Resources res;

    // Flag Constants
    public static final int ACTION = 0;
    public static final int ADW = 1;
    public static final int APEX = 2;
    public static final int ATOM = 3;
    public static final int AVIATE = 4;
    public static final int GO = 5;
    public static final int HOLO = 6;
    public static final int NEXT = 7;
    public static final int NOVA = 8;
    public static final int SMART = 9;
    //TODO add support for ATOM Launcher
    //TODO add support for KK Launcher
    //TODO add support for LG Launcher
    //TODO add support for SOLO Launcher
    //TODO add support for CMTE Launcher
    //TODO add support for Inspite Launcher
    //TODO add support for Lucid Launcher (PRO)
    //TODO add support for Solo Launcher
    //TODO add support for Themer
    //TODO add support for Unicon
    //TODO add support for TSF Launcher 3D
    //TODO add support for Nine Launcher (PRO)
    //TODO add support for Everything.me
    //TODO add support for Cyanogen

    public ApplyLauncherAdapter(Context context, List<Integer> gridItem) {
        this.gridItem = gridItem;
        this.context = context;
        this.res = context.getResources();

        // Set up color filter
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0); //0 means grayscale
        this.grayscaleFilter = new ColorMatrixColorFilter(matrix);
    }

    @Override
    @SuppressWarnings( "deprecation" )
    //deprecation warning suppressed because colors need to be accessed in this manner to allow for
    //compatibility with the support.v4 library and fragment setup of the MainActivity
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder;
        int entry = gridItem.get(position);

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.apply_launcher_layout, null);

            holder = new ViewHolder();
            holder.title = (TextView) v.findViewById(R.id.title);
            holder.launcher_Image = (ImageView) v.findViewById(R.id.list_image);

            v.setTag(holder);
        }
        else
            holder = (ViewHolder) v.getTag();

        switch(entry)
        {
            case ACTION:
                holder.title.setText(res.getString(R.string.launcher_al));
                setInstalledStatus(holder, res.getString(R.string.launcher_al_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.action_banner);
                break;
            case ADW:
                holder.title.setText(res.getString(R.string.launcher_adw));
                setInstalledStatus(holder, res.getString(R.string.launcher_adw_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.adw_banner);
                break;
            case APEX:
                holder.title.setText(res.getString(R.string.launcher_apex));
                setInstalledStatus(holder, res.getString(R.string.launcher_apex_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.apex_banner);
                break;
            case ATOM:
                holder.title.setText(res.getString(R.string.launcher_atom));
                setInstalledStatus(holder, res.getString(R.string.not_supported));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.atom_banner);
                break;
            case AVIATE:
                holder.title.setText(res.getString(R.string.launcher_aviate));
                setInstalledStatus(holder, res.getString(R.string.launcher_aviate_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.aviate_banner);
                break;
            case GO:
                holder.title.setText(res.getString(R.string.launcher_go));
                setInstalledStatus(holder, res.getString(R.string.not_supported));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.go_banner);
                break;
            case HOLO:
                holder.title.setText(res.getString(R.string.launcher_holo));
                setInstalledStatus(holder, res.getString(R.string.launcher_holo_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.holo_banner);
                break;
            case NEXT:
                holder.title.setText(res.getString(R.string.launcher_next));
                setInstalledStatus(holder, res.getString(R.string.not_supported));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.next_banner);
                break;
            case NOVA:
                holder.title.setText(res.getString(R.string.launcher_nova));
                setInstalledStatus(holder, res.getString(R.string.launcher_nova_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.nova_banner);
                break;
            case SMART:
                holder.title.setText(res.getString(R.string.launcher_smart));
                setInstalledStatus(holder, res.getString(R.string.launcher_smart_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.smart_banner);
                break;
        }

        return v;
    }

    /**
     * Helper method for getView that sets the color of the viewholder based off the package name
     * given. If not supported or not installed will be grayscaled, otherwise will be in color.
     *
     * @param holder viewholder to set color in
     * @param packageName package name to look for
     */
    private void setInstalledStatus(ViewHolder holder, String packageName)
    {
        // Set installed status
        if(Utils.isPackageInstalled(packageName, context))
        {
            holder.launcher_Image.clearColorFilter();
        }
        else if (packageName.equals(res.getString(R.string.not_supported))) {
            holder.launcher_Image.setColorFilter(grayscaleFilter);
        }
        else
        {
            holder.launcher_Image.setColorFilter(grayscaleFilter);
        }
    }

    @Override
    public int getCount() {
        return gridItem.size();
    }

    @Override
    public Integer getItem(int position) {
        return gridItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Simple ViewHolder added to facillate drawing and to reduce redraws
     */
    private class ViewHolder {
        public TextView title;
        public ImageView launcher_Image;
    }
}