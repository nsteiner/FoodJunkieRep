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

import java.util.List;
import java.util.Locale;

public class DefaultRecipeListAdapter extends ArrayAdapter<String> {
    private static final String TAG = "Recipe List Adapter";
    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    ImageLoader imageLoader;

    String imagePath = "drawable://R.drawable.";



    //thanks internet! #1
    static class ViewHolder {
        TextView name;
        TextView birthday;
        TextView sex;
        ImageView img;
    }


    public DefaultRecipeListAdapter(Context context, int resource, List<String> objects) {
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
            holder.name = (TextView) convertView.findViewById(R.id.textView1);
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
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        String imgURL = name.replace(",", "").replace(" ", "").replace("-","").replace("/", "").replace(")", "").replace("(", "").toLowerCase(Locale.ROOT);
        int resID = mContext.getResources().getIdentifier(imgURL, "drawable", mContext.getPackageName());
        holder.img.setImageResource(resID);

        holder.name.setText(name);


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

