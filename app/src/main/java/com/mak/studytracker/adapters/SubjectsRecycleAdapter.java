package com.mak.studytracker.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mak.studytracker.R;
import com.mak.studytracker.models.Subject;

import java.util.List;

/**
 * Created by worthmate on 18/5/16.
 */
public class SubjectsRecycleAdapter extends RecyclerView.Adapter<SubjectsRecycleAdapter.MyViewHolder> {
    public Context mContext;
    private List<Subject> subjectList;
    private SharedPreferences sharedPreferences;
    private Typeface face;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView contentText, titleText;


        public MyViewHolder(View view) {
            super(view);
            contentText = (TextView) view.findViewById(R.id.blog_content_text);
            titleText = (TextView) view.findViewById(R.id.blog_title_text);

        }
    }

    public SubjectsRecycleAdapter(Context context, List<Subject> subjectList) {
        this.mContext = context;
        this.subjectList = subjectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_list_post, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        //        face= Typeface.createFromAsset(mContext.getAssets(),sharedPreferences.getString(UserSharedPreferencesHelper.FONT_LOCATION_KEY,UserSharedPreferencesHelper.LOCATION_NEWSHUNT));

        holder.titleText.setText(Html.fromHtml(subjectList.get(position).getName()));
        holder.contentText.setText(Html.fromHtml(
                "<b><big> Target Date :</big></b> " + subjectList.get(position).getTargetDate()
                        + "<br>Remaining Pages :</big></b> " +(subjectList.get(position).getUnitsValue()- subjectList.get(position).getCurrentProgress())
                )
        )        ;
        ;
//        holder.titleText.setTypeface(face);
//        holder.titleText.setTextSize(sharedPreferences.getFloat(UserSharedPreferencesHelper.FONT_SIZE_KEY, UserSharedPreferencesHelper.FONT_SIZE_DEFAULT)+5);


    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }
}
