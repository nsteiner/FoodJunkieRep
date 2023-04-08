package com.example.foodjunkie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
        TextView name;
        TextView unit;
        TextView quantity;
    }



    public PantryListAdapter(Context context, int resource, List<PantryModel> objects) {
        super(context, resource, objects);
        this.context = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //this "getView" is responsible for getting the view and then attaching it to the list view

        //setup the image loader...

        //get the persons info:
        String name = getItem(position).getIngredientName();
        String quantity = String.valueOf(getItem(position).getQuantity());
        String unit = getItem(position).getUnit();
        //   String imgURL = getItem(position).getRecipeName().replaceAll("\\s", "").toLowerCase();

        //getItem(position).getRecipeName();


        //create the view result for showing the animation
        final View result;
        ViewHolder holder;

        //this allows only a few items to be loaded at a time, instead of them all being loaded...

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView3);
            holder.unit = (TextView) convertView.findViewById(R.id.textView2);
            holder.quantity = (TextView) convertView.findViewById(R.id.textView1);

            result = convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        //default image if nothing displays properly...


        //declare animation

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;



        holder.name.setText(name);
        holder.quantity.setText(quantity);
        holder.unit.setText(unit);




        return convertView;
    }

}