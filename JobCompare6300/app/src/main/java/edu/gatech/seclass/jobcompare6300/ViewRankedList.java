package edu.gatech.seclass.jobcompare6300;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewRankedList extends AppCompatActivity {
    ComparisonManager cm;
    ComparisonManager dbAppWrapper;

    private Button returnButton;
    private View.OnClickListener rListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickReturn(view);
        }
    };

    private Button compareButton;
    private View.OnClickListener cListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickCompare(view);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ranked_list);

        cm = ComparisonManager.getInstance();
        dbAppWrapper = (ComparisonManager) getApplication();

        returnButton = findViewById(R.id.button_return_from_compare);
        returnButton.setOnClickListener(rListener);

        compareButton = findViewById(R.id.button_compare_2_jobs);
        compareButton.setOnClickListener(cListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        int counter = 1;
        List<JobDetails> jobList = dbAppWrapper.getRankedJobs();
        LinearLayout viewList = findViewById(R.id.layout_job_list);
        viewList.removeAllViews();
        for (JobDetails job : jobList) {
            LinearLayout entry = CreateEntry(counter++, job);
            viewList.addView(entry);
        }
    }

    @SuppressLint("DefaultLocale")
    private LinearLayout CreateEntry(int rank, JobDetails job) {
        LinearLayout entry = new LinearLayout(this);
        entry.setOrientation(LinearLayout.HORIZONTAL);

        entry.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

        double score = job.getScore();
        String title = job.getTitle();
        String company = job.getCompany();
        boolean isCurrent = job.getIsCurrentJob();

        CheckBox cbSelect = new CheckBox(this);
        int id = Math.toIntExact(job.getId());
        cbSelect.setId(id);
        cbSelect.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, .5f));

        TextView tvRank = new TextView(this);
        tvRank.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, .5f));
        tvRank.setText(String.format("%d", rank));

        TextView tvInfo = new TextView(this);
        tvInfo.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2.0f));
        String info = title + " : " + company;
        if (isCurrent)
            info += " *Current Job*";
        tvInfo.setText(info);

        TextView tvScore = new TextView(this);
        tvScore.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        tvScore.setText(String.format("%.4f", score));

        entry.addView(cbSelect);
        entry.addView(tvRank);
        entry.addView(tvInfo);
        entry.addView(tvScore);

        return entry;
    }

    private void clickReturn(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void clickCompare(View view) {
        Intent i = new Intent(getApplicationContext(), CompareJobs.class);
//        List<JobDetails> jobList = dbAppWrapper.getRankedJobs();
        LinearLayout viewList = findViewById(R.id.layout_job_list);
        TextView warnTextView = findViewById(R.id.warnText);

        int id =0;
        ArrayList<Integer> checked = new ArrayList<>();
        final int childCount = viewList.getChildCount();
        for (int k = 0; k < childCount; k++) {
            View v = viewList.getChildAt(k);
            if (v instanceof LinearLayout) {
                LinearLayout ll = (LinearLayout)v;
                final int rows = ll.getChildCount();
                for (int j = 0; j < rows; j++) {
                    View child = ll.getChildAt(j);
                    if (child instanceof CheckBox &&  ((CheckBox) child).isChecked()) {
                        checked.add(child.getId());
                    }
                }
            }
        }
//        JobDetails job = dbAppWrapper.getCurrentJob();
        if (checked.size() != 2) {
            Log.e("ViewRankedList", "User didn't select 2 job offers for compare");
            warnTextView.setText("Please select exactly two offers to compare");
            return;
        }
        i.putExtra("Job1", (long) checked.get(0));
        i.putExtra("Job2", (long) checked.get(1));
        startActivity(i);
    }

}
