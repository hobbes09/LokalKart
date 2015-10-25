package com.lokalkart.views.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.lokalkart.R;
import com.lokalkart.activities.HomeScreen;
import com.lokalkart.fragments.HomeScreenFragment;
import com.lokalkart.utils.GlobalConstants;
import com.lokalkart.views.customviews.SubCategoryCustomGridView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sourin on 10/09/15.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private static HashMap<String, Bitmap> mListDataCategories;
    private static HashMap<String, List<Bitmap>> mListDataSubCategories;
    private LayoutInflater mInflater;

    public ExpandableListAdapter(Context context, HashMap<String, Bitmap> listDataHeader,
                                 HashMap<String, List<Bitmap>> listChildData) {
        mContext = context;
        mListDataCategories = listDataHeader;
        mListDataSubCategories = listChildData;
//        mInflater = LayoutInflater.from(context);
        mInflater = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getGroupCount() {
        return mListDataCategories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListDataCategories.get(Integer.toString(groupPosition));
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListDataSubCategories.get(Integer.toString(groupPosition)).size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Bitmap bitmapGroupView = (Bitmap)getGroup(groupPosition);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.view_category, null);
        }

        ImageView ivCategory = (ImageView)convertView.findViewById(R.id.ivCategory);
        ivCategory.setImageBitmap(bitmapGroupView);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.view_gridview_subcategory, null);
        }
        SubCategoryCustomGridView mSubCategoryCustomGridView = (SubCategoryCustomGridView)convertView.findViewById(R.id.gvSubCategory);
        mSubCategoryCustomGridView.setNumColumns(GlobalConstants.NUM_COLUMNS_SUBCATEGORY);

        List<Bitmap> listSubCategoryBitmaps = mListDataSubCategories.get(Integer.toString(groupPosition));
        SubCategoryCustomGridAdapter mSubCategoryGridAdapter = new SubCategoryCustomGridAdapter(mContext, listSubCategoryBitmaps);
        mSubCategoryCustomGridView.setAdapter(mSubCategoryGridAdapter);

        mSubCategoryCustomGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomeScreen.mApplicationContext, "Item clicked : " + position, Toast.LENGTH_SHORT).show();
            }
        });

        int totalHeight = 0;
        for(int size = 0; size < mSubCategoryGridAdapter.getCount(); size = size+2){
            LinearLayout linearLayout = (LinearLayout) mSubCategoryGridAdapter.getView(size, null, mSubCategoryCustomGridView);
            ImageView imageView = (ImageView) linearLayout.getChildAt(0);
            imageView.measure(0, 0);
            totalHeight += imageView.getMeasuredHeight();
        }
        mSubCategoryCustomGridView.SetHeight(totalHeight);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
