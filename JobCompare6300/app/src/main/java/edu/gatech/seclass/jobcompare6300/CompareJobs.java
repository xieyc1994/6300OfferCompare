package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CompareJobs extends AppCompatActivity {
    ComparisonManager cm;
    ComparisonManager dbAppWrapper;

    private Button returnButton;
    private View.OnClickListener rListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickReturn(view);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_jobs);

        cm = ComparisonManager.getInstance(); 
        dbAppWrapper = (ComparisonManager) getApplication();

        returnButton = findViewById(R.id.button_return_from_compare);
        returnButton.setOnClickListener(rListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();

        long job1id = intent.getLongExtra("Job1", 0);
        long job2id = intent.getLongExtra("Job2", 0);

        Log.d("DYU", String.format("CompareJobs %d %d", job1id, job2id));

        JobDetails job1 = SearchForJob(job1id);
        JobDetails job2 = SearchForJob(job2id);

        SetJobDetails(job1, job2);

        //JobDetails test = new JobDetails((long) 999, "title", "company", "state", "city", 1.5, 100000, 50000, 30000, .05, 20, false);

        //SetJobDetails(null, test);
    }

    private void clickReturn(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void SetJobDetails(JobDetails job1, JobDetails job2) {

        if (job1 != null) {
            TextView tvTitle = findViewById(R.id.title1);
            TextView tvCompany = findViewById(R.id.company1);
            TextView tvCity = findViewById(R.id.city1);
            TextView tvState = findViewById(R.id.state1);
            TextView tvSalary = findViewById(R.id.adjusted_salary1);
            TextView tvASB = findViewById(R.id.asb1);
            TextView tvAYB = findViewById(R.id.ayb1);
            TextView tvRetirement = findViewById(R.id.retirement_benefits1);
            TextView tvLeave = findViewById(R.id.leave_time1);
            TextView tvScore = findViewById(R.id.score1);
            SetJobDetail(job1, tvTitle, tvCompany, tvCity, tvState, tvSalary, tvASB, tvAYB, tvRetirement, tvLeave, tvScore);
        }

        if (job2 != null) {
            TextView tvTitle = findViewById(R.id.title2);
            TextView tvCompany = findViewById(R.id.company2);
            TextView tvCity = findViewById(R.id.city2);
            TextView tvState = findViewById(R.id.state2);
            TextView tvSalary = findViewById(R.id.adjusted_salary2);
            TextView tvASB = findViewById(R.id.asb2);
            TextView tvAYB = findViewById(R.id.ayb2);
            TextView tvRetirement = findViewById(R.id.retirement_benefits2);
            TextView tvLeave = findViewById(R.id.leave_time2);
            TextView tvScore = findViewById(R.id.score2);
            SetJobDetail(job2, tvTitle, tvCompany, tvCity, tvState, tvSalary, tvASB, tvAYB, tvRetirement, tvLeave, tvScore);

        }
    }

    @SuppressLint("DefaultLocale")
    private void SetJobDetail(JobDetails job,
                              TextView tvTitle,
                              TextView tvCompany,
                              TextView tvCity,
                              TextView tvState,
                              TextView tvSalary,
                              TextView tvASB,
                              TextView tvAYB,
                              TextView tvRetirement,
                              TextView tvLeave,
                              TextView tvScore) {

        job.populateAdjustedValues(dbAppWrapper.getComparisonWeights());
        tvTitle.setText(job.getTitle());
        tvCompany.setText(job.getCompany());
        tvCity.setText(job.getCity());
        tvState.setText(job.getState());
        tvSalary.setText(String.format("%.2f", job.getYearlySalary()));
        tvASB.setText(String.format("%.2f", job.getASB()));
        tvAYB.setText(String.format("%.2f", job.getAYB()));
        tvRetirement.setText(String.format("%.4f", job.getRBP()));
        tvLeave.setText(String.format("%d", job.getLeaveTime()));
        tvScore.setText(String.format("%.5f", job.getScore()));
    }

    private JobDetails SearchForJob(long jobId) {
        JobDetails current = dbAppWrapper.getCurrentJob();
        //Log.d("DYU", String.format("SearchForJob %d checking %d", jobId, current.getId()));
        if (current != null && current.getId() != null && jobId == current.getId()) {
                return current;
        }

        ArrayList<JobDetails> offers = (ArrayList<JobDetails>) dbAppWrapper.getJobOffers();
        for (JobDetails job : offers) {
            Log.d("DYU", String.format("SearchForJob %d checking %d", jobId, job.getId()));
            if (job.getId() == jobId) {
                return job;
            }
        }

        return null;
    }
}
