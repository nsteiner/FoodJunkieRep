package com.example.foodjunkie;

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

import java.util.List;

public class MyRecipeListAdapter extends ArrayAdapter<String> {
    private static final String TAG = "Recipe List Adapter";
    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    //thanks internet! #1
    static class ViewHolder {
        TextView name;
        TextView birthday;
        TextView sex;
        ImageView img;
    }


    public MyRecipeListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //this "getView" is responsible for getting the view and then attaching it to the list view

        //get the persons info:
        String name = getItem(position);
        //String imgURL = getItem(position).getImgURL();
        //String imgURL = getItem(position).getImgResID();
        //int resID = getContext().getResources().getIdentifier(imgURL, "drawable", getContext().getPackageName());
        //"drawable://" + R.drawable.;
        //getItem(position).getRecipeName();


        //create the view result for showing the animation
        final View result;
        ViewHolder holder;

        //this allows only a few items to be loaded at a time, instead of them all being loaded...

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.myRecipeListText);
            result = convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        //declare animation

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.name.setText(name);


        return convertView;
    }

}

