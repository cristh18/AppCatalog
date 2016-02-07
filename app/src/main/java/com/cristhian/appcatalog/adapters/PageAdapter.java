package com.cristhian.appcatalog.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cristhian.appcatalog.fragments.PagerFragment;
import com.cristhian.appcatalog.models.CategoryAttribute;
import com.cristhian.appcatalog.repository.CategoryRepository;

import java.util.List;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    Context context;

    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        return PagerFragment.viewFragments[i];
    }

    @Override
    public int getCount() {
        return PagerFragment.NUM_PAGES;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return getNamePage(context, position);
    }

    /**
     * @param context
     * @param category
     * @return
     */
    private String getNamePage(Context context, int category) {

        for (int i = 0; i < PagerFragment.viewFragments.length; i++) {
            if (i == category) {
                return searchCategoryName(category);
            }
        }
        return null;
    }

    private String searchCategoryName(int category) {
        String categoryName = "";
        CategoryRepository categoryRepository = CategoryRepository.getCateRepoInstance();
        List<CategoryAttribute> categories = categoryRepository.getCategories(context);
        for (CategoryAttribute c : categories) {
            if ((category+1) == Integer.parseInt(c.getImId())) {
                categoryName = c.getLabel();
                break;
            }
        }
        return categoryName;
    }
}
