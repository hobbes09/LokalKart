package com.lokalkart.views.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lokalkart.R;

import java.util.List;

/**
 * Created by sourin on 18/09/15.
 */
public class SubCategoryCustomGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<Bitmap> mBitmaps;
    private Bitmap[] mBitmapArray;

    public SubCategoryCustomGridAdapter(Context context, List<Bitmap> bitmaps) {
        mContext = context;
        mBitmaps = bitmaps;
        mBitmapArray = new Bitmap[mBitmaps.size()];
        mBitmaps.toArray(mBitmapArray);
    }

    @Override
    public int getCount() {
        return mBitmapArray.length;
    }

    @Override
    public Object getItem(int position) {
        return mBitmapArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_subcategory, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.ivSubCategory = (ImageView)convertView.findViewById(R.id.ivSubCategory);
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.ivSubCategory.setImageBitmap(mBitmapArray[position]);

        return  convertView;
    }

    static class ViewHolder {
        ImageView ivSubCategory;
    }
}
