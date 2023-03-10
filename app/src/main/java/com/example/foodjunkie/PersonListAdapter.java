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

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class PersonListAdapter extends ArrayAdapter<Food> {
    private static final String TAG = "Person List Adapter";
    private Context mContext;
    private int mResource;
    private int lastPostion = -1;

    ImageLoader imageLoader;



    //thanks internet! #1
    static class ViewHolder {
        TextView name;
        TextView birthday;
        TextView sex;
        ImageView img;
    }


    public PersonListAdapter(Context context, int resource, ArrayList<Food> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //this "getView" is responsible for getting the view and then attaching it to the list view

        //setup the image loader...
        setupImageLoader();
        //get the persons info:
        String name = getItem(position).getName();
        String birthday = getItem(position).getBirthday();
        String sex = getItem(position).getSex();
        String imgURL = getItem(position).getImageURL();


        //create the view result for showing the animation
        final View result;
        ViewHolder holder;

        //this allows only a few items to be loaded at a time, instead of them all being loaded...

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            holder.birthday = (TextView) convertView.findViewById(R.id.textView2);
            holder.sex = (TextView) convertView.findViewById(R.id.textView3);
            holder.img = (ImageView) convertView.findViewById(R.id.image);

            result = convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        //default image if nothing displays properly...
        int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed", null, mContext.getPackageName());

        imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();


        //declare animation

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPostion) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPostion = position;

        imageLoader.displayImage(imgURL, holder.img, options);

        holder.name.setText(name);
        holder.birthday.setText(birthday);
        holder.sex.setText(sex);


        return convertView;
    }

    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }
}

