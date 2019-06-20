package com.example.mar.hokmpick.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mar.hokmpick.R;
import com.example.mar.hokmpick.classes.Utility;

import java.util.List;

/**
 * Created by M.A.R on 14/07/2018.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private Context context;
    private List<ScoreData> scores;

    public ScoreAdapter(Context context, List<ScoreData> scores) {
        this.context = context;
        this.scores = scores;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.score_list, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {


        Typeface TF = Utility.getTypeFace(context , Utility.FONT_TYPE_REGULAR);

        ScoreData scoreData = scores.get(position);
        holder.txtScore1.setText(Utility.numToPersion(scoreData.getScore1() + ""));
        if (scoreData.getScore1() < 0) {
            holder.txtScore1.setTextColor(Color.RED);
        } else if (scoreData.getScore1() >= 70 && SecondActivity.group_one_catched_7) {
            holder.txtScore1.setTextColor(Color.GREEN);
        }
        holder.txtScore2.setText(Utility.numToPersion(scoreData.getScore2() + ""));
        if (scoreData.getScore2() < 0) {
            holder.txtScore2.setTextColor(Color.RED);
        } else if (scoreData.getScore2() >= 70 && SecondActivity.group_two_catched_7) {
            holder.txtScore2.setTextColor(Color.GREEN);
        }

        holder.txtSumScore1.setText(Utility.numToPersion(scoreData.getSum_score1() + ""));
        holder.txtSumScore2.setText(Utility.numToPersion(scoreData.getSum_score2() + ""));

        holder.txtScore1.setTypeface(TF,Typeface.ITALIC);
        holder.txtScore2.setTypeface(TF,Typeface.ITALIC);
        holder.txtSumScore1.setTypeface(TF);
        holder.txtSumScore2.setTypeface(TF);

    }

    @Override
    public int getItemCount() {

        return scores.size();
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {

        private TextView txtScore1;
        private TextView txtScore2;
        private TextView txtSumScore1;
        private TextView txtSumScore2;

        public ScoreViewHolder(View itemView) {
            super(itemView);

            txtScore1 = itemView.findViewById(R.id.txt_score1);
            txtScore2 = itemView.findViewById(R.id.txt_score2);
            txtSumScore1 = itemView.findViewById(R.id.txt_sumscore1);
            txtSumScore2 = itemView.findViewById(R.id.txt_sumscore2);

        }
    }
}
