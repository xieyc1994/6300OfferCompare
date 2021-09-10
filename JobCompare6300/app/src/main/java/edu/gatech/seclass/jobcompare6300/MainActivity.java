package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    ComparisonManager cm;
    ComparisonManager dbAppWrapper;

    private Button enterJobDetailsButton;
    private View.OnClickListener ejdListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickEnterJobDetails(view);
        }
    };

    private Button enterCurrentJobButton;
    private View.OnClickListener ecjListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickEnterCurrentJob(view);
        }
    };

    private Button compareJobsButton;
    private View.OnClickListener cjListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickCompareJobs(view);
        }
    };

    private Button setComparisonWeightsButton;
    private View.OnClickListener scwListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickSetComparisonWeights(view);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cm = ComparisonManager.getInstance();
        dbAppWrapper = (ComparisonManager) getApplication();

        enterCurrentJobButton = findViewById(R.id.button_enter_current_job);
        enterCurrentJobButton.setOnClickListener(ecjListener);

        enterJobDetailsButton = findViewById(R.id.button_enter_job_details);
        enterJobDetailsButton.setOnClickListener(ejdListener);

        compareJobsButton = findViewById(R.id.button_compare_jobs);
        compareJobsButton.setOnClickListener(cjListener);

        setComparisonWeightsButton = findViewById(R.id.button_set_comparison_weights);
        setComparisonWeightsButton.setOnClickListener(scwListener);

        if (dbAppWrapper.getAllJobOffers() == null || dbAppWrapper.getAllJobOffers().size() == 0) {
            compareJobsButton.setEnabled(false);
        }
        // JobDetails job = cm.getCurrentJob();
        // if (job == null) {
        // JobDetails test = new JobDetails((long) 777, "title", "company", "state",
        // "city", 1, 100000, 50000, 30000, .05, 20, true);
        // cm.persistOfferDetail(test);
        //
        // JobDetails test1 = new JobDetails((long) 888, "title1", "company1", "state1",
        // "city1", 1.5, 100000, 50000, 30000, .05, 20, false);
        // JobDetails test2 = new JobDetails((long) 999, "title2", "company2", "state2",
        // "city2", 1.1, 100000, 50000, 30000, .05, 20, false);
        // cm.persistOfferDetail(test1);
        // cm.persistOfferDetail(test2);
        // }
    }

    public void clickEnterJobDetails(View view) {

        Intent i = new Intent(getApplicationContext(), EnterJobDetails.class);
        i.putExtra("CurrentJob", false);
        startActivity(i);
    }

    public void clickEnterCurrentJob(View view) {

        Intent i = new Intent(getApplicationContext(), EnterJobDetails.class);
        i.putExtra("CurrentJob", true);
        startActivity(i);
    }

    public void clickCompareJobs(View view) {

        Intent i = new Intent(getApplicationContext(), ViewRankedList.class);
        startActivity(i);

        // testCompareJobs();
    }

    /*
     * private void testCompareJobs() {
     * 
     * Intent i = new Intent(getApplicationContext(), CompareJobs.class);
     * 
     * JobDetails job = cm.getCurrentJob(); //long id =
     * cm.getRankedJobs().get(0).getId(); i.putExtra("Job1", job.getId());
     * i.putExtra("Job2", (long) 999); startActivity(i); }
     */

    public void clickSetComparisonWeights(View view) {

        Intent i = new Intent(getApplicationContext(), EnterWeights.class);
        startActivity(i);
    }

}
