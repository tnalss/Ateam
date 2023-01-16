package com.example.lastproject.Org_Chart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

public class ContentsPagerAdapter extends FragmentStateAdapter {
    private int mPageCount = 4;


    public ContentsPagerAdapter(AppCompatActivity fm) {
        super(fm);
        //this.mPageCount = pageCount;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {

            case 0:
                Org_all_Fragment f1 = new Org_all_Fragment();
                return f1;
            case 1:
                Org_branch_Fragment f2 = new Org_branch_Fragment();
                return f2;
            case 2:
                Org_rank_Fragment f3 = new Org_rank_Fragment();
                return f3;
            case 3:
                Org_dept_Fragment f4 = new Org_dept_Fragment();
                return f4;

            default:
                return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mPageCount;
    }
}
