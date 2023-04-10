package com.example.foodjunkie;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class PantryListAdapter extends ArrayAdapter<PantryModel> {
    private static final String TAG = "Recipe List Adapter";
    private Context context;
    private int mResource;
    private int lastPosition = -1;
    static class ViewHolder {
        TextView ingredient;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String strname = getItem(position).getIngredientName();
        String strunit = getItem(position).getUnit();
        String strquantity = String.valueOf(getItem(position).getQuantity());
        String sentence = String.format("%s %s %s", strquantity, strunit, strname);

        final View result;
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.ingredient = (TextView) convertView.findViewById(R.id.textView1);
            result = convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
            result = convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;
        holder.ingredient.setText(sentence);
        return convertView;
    }


    public PantryListAdapter(Context context, int resource, List<PantryModel> objects) {
        super(context, resource, objects);
        this.context = context;
        mResource = resource;
    }
}
