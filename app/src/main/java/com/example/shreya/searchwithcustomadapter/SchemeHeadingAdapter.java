package com.example.shreya.searchwithcustomadapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SchemeHeadingAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Schemes> schemeslist = null;
    private ArrayList<Schemes> arraylist;

    public SchemeHeadingAdapter(Context context, List<Schemes> schemelist) {
        mContext = context;
        this.schemeslist = schemelist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Schemes>();
        this.arraylist.addAll(schemelist);
    }

    public class ViewHolder {
        TextView schemeName;

    }

    @Override
    public int getCount() {
        return schemeslist.size();
    }

    @Override
    public Schemes getItem(int position) {
        return schemeslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item2, null);
            // Locate the TextViews in listview_item.xml
            holder.schemeName = (TextView) view.findViewById(R.id.scheme_name);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.schemeName.setText(schemeslist.get(position).getHeading());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SchemeDescriptionActivity.class);
                // Pass all data rank
                intent.putExtra("heading",schemeslist.get(position).getHeading());
                intent.putExtra("brief", schemeslist.get(position).getBrief());
                intent.putExtra("eligibility", schemeslist.get(position).getEligibility());
                intent.putExtra("benefits", schemeslist.get(position).getBenefits());
                intent.putExtra("apply", schemeslist.get(position).getApply());
                intent.putExtra("more", schemeslist.get(position).getMore_info());
                // Pass all data flag
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        Log.d("char text in filter",charText);
        schemeslist.clear();
        if (charText.length() == 0) {
            schemeslist.addAll(arraylist);
            Log.d("charText.len", String.valueOf(charText.length()));
        }
        else
        {
            for (Schemes wp : arraylist)
            {

                Log.d("heading retrieved", mContext.getResources().getString((wp.getHeading())));
                if (mContext.getResources().getString(wp.getHeading()).toLowerCase().contains(charText))
                {
                   schemeslist.add(wp);
                   Log.d("search results", "entered");
                   Log.d("schemelist", String.valueOf(schemeslist));
                }
            }
        }
        notifyDataSetChanged();
    }

}