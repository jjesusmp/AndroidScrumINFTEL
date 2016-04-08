package com.example.asus.androidscruminftel;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by RMA on 07/04/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] tittle;
    private String[] description;
    private ProjectsScrum myProject;

    public void setMyProject (ProjectsScrum theProject) {
        this.myProject = theProject;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView mCardView;
        public TextView mTextView;
        public TextView mTextView2;
        public RelativeLayout rlLayout;
        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
            mTextView2 = (TextView) v.findViewById(R.id.tv_blah);
            rlLayout = (RelativeLayout) v.findViewById(R.id.rlLayout);
        }

        @Override
        public void onClick(View v) {

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset, String [] myDataset2) {

        tittle = myDataset;
        description = myDataset2;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextView.setText(tittle[position]);
        holder.mTextView2.setText(description[position]);
        holder.rlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myProject,Task.class);
                intent.putExtra("tittle",tittle[position]);
                myProject.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return tittle.length;
    }
}