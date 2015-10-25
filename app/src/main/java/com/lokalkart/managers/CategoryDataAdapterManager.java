package com.lokalkart.managers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.lokalkart.activities.HomeScreen;
import com.lokalkart.fragments.HomeScreenFragment;

import com.lokalkart.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sourin on 08/09/15.
 */
public class CategoryDataAdapterManager {

    private static ExpandableListView elvCategories;
    private static HashMap<String, Bitmap> mListDataCategories;
    private static HashMap<String, List<Bitmap>> mListDataSubCategories;
    private static ExpandableListAdapter mExpandableListAdapter;

    public static void prepareCategoryDataAdapter(ExpandableListView mExpandableListView) {
        elvCategories = mExpandableListView;

        prepareListData();

        mExpandableListAdapter = new com.lokalkart.views.adapters.ExpandableListAdapter(HomeScreen.mApplicationContext, mListDataCategories, mListDataSubCategories);

        elvCategories.setAdapter(mExpandableListAdapter);

        elvCategories.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        elvCategories.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    elvCategories.collapseGroup(previousItem);
                previousItem = groupPosition;
            }
        });

    }


    public static void prepareListData() {

        Bitmap bitmapCategoryImage = BitmapFactory.decodeResource(HomeScreen.mResources, R.drawable.home_appliances);
        Bitmap bitmapSubCategoryIcon = BitmapFactory.decodeResource(HomeScreen.mResources, R.drawable.fridge);

        mListDataCategories = new HashMap<String, Bitmap>();
        mListDataSubCategories = new HashMap<String, List<Bitmap>>();

        mListDataCategories.put("0", bitmapCategoryImage);
        mListDataCategories.put("1", bitmapCategoryImage);
        mListDataCategories.put("2", bitmapCategoryImage);
        mListDataCategories.put("3", bitmapCategoryImage);

        List<Bitmap> category1List = new ArrayList<Bitmap>();
        category1List.add(bitmapSubCategoryIcon);

        List<Bitmap> category2List = new ArrayList<Bitmap>();
        category2List.add(bitmapSubCategoryIcon);
        category2List.add(bitmapSubCategoryIcon);

        List<Bitmap> category3List = new ArrayList<Bitmap>();
        category3List.add(bitmapSubCategoryIcon);
        category3List.add(bitmapSubCategoryIcon);
        category3List.add(bitmapSubCategoryIcon);

        List<Bitmap> category4List = new ArrayList<Bitmap>();
        category4List.add(bitmapSubCategoryIcon);
        category4List.add(bitmapSubCategoryIcon);
        category4List.add(bitmapSubCategoryIcon);
        category4List.add(bitmapSubCategoryIcon);

        mListDataSubCategories.put("0", category1List);
        mListDataSubCategories.put("1", category2List);
        mListDataSubCategories.put("2", category3List);
        mListDataSubCategories.put("3", category4List);


    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    }
