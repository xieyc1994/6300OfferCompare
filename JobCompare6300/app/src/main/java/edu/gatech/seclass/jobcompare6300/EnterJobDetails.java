package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.greendao.query.Query;
import java.util.List;

public class EnterJobDetails extends AppCompatActivity {
    private Button saveButton;
    private View.OnClickListener sListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickSave(view, getIntent().getExtras().getBoolean("CurrentJob"));
        }
    };

    private Button cancelButton;
    private View.OnClickListener cfListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickCancel(view);
        }
    };

    private ComparisonManager cm;
    private ComparisonManager dbAppWrapper;

    private EditText titleET;
    private EditText companyET;
    private EditText cityET;
    private EditText stateET;
    private EditText colET;
    private EditText salaryET;
    private EditText signingBonusET;
    private EditText yearlyBonusET;
    private EditText retirementBenefitsET;
    private EditText leaveTimeET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_job_details);

        saveButton = findViewById(R.id.button_save_job);
        saveButton.setOnClickListener(sListener);

        cancelButton = findViewById(R.id.button_cancel_job);
        cancelButton.setOnClickListener(cfListener);

        titleET = findViewById(R.id.editText_title);
        companyET = findViewById(R.id.editText_company);
        cityET = findViewById(R.id.editText_city);
        stateET = findViewById(R.id.editText_state);
        colET = findViewById(R.id.editText_col);
        salaryET = findViewById(R.id.editText_salary);
        signingBonusET = findViewById(R.id.editText_signing_bonus);
        yearlyBonusET = findViewById(R.id.editText_yearly_bonus);
        retirementBenefitsET = findViewById(R.id.editText_retirement_benefits);
        leaveTimeET = findViewById(R.id.editText_leave_time);

        cm = ComparisonManager.getInstance();
        dbAppWrapper = (ComparisonManager) getApplication();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent currentIntent = getIntent();
        TextView title = findViewById(R.id.textView_enter_job_header);

        // set a blank form
        titleET.setText("");
        companyET.setText("");
        cityET.setText("");
        stateET.setText("");
        colET.setText("");
        salaryET.setText("");
        signingBonusET.setText("");
        yearlyBonusET.setText("");
        retirementBenefitsET.setText("");
        leaveTimeET.setText("");

        if (currentIntent.getBooleanExtra("CurrentJob", false)) {
            title.setText(R.string.enter_current_job);
            JobDetails currentJob = dbAppWrapper.getCurrentJob();
            if (currentJob != null) {
                titleET.setText(currentJob.getTitle());
                companyET.setText(currentJob.getCompany());
                cityET.setText(currentJob.getCity());
                stateET.setText(currentJob.getState());
                colET.setText(Double.toString(currentJob.getCostOfLiving()));
                salaryET.setText(Double.toString(currentJob.getYearlySalary()));
                signingBonusET.setText(Double.toString(currentJob.getSigningBonus()));
                yearlyBonusET.setText(Double.toString(currentJob.getYearlyBonus()));
                retirementBenefitsET.setText(Double.toString(currentJob.getRetirementBenefits()));
                leaveTimeET.setText(Integer.toString(currentJob.getLeaveTime()));
            }
        } else {
            title.setText(R.string.enter_job_details);
        }
    }

    public void clickSave(View view, boolean isCurrentJob) {
        boolean noErrors = true;
        String title = titleET.getText().toString();
        String company = companyET.getText().toString();
        String city = cityET.getText().toString();
        String state = stateET.getText().toString();
        double col = 0.0;
        double salary = 0.0;
        double signingBonus = 0.0;
        double yearlyBonus = 0.0;
        double retirementBenefits = 0.0;
        int leaveTime = 0;
        try {
            col = Double.parseDouble(colET.getText().toString());
        } catch (NumberFormatException e) {
            colET.setError("Format Error");
            noErrors = false;
        }
        try {
            salary = Double.parseDouble(salaryET.getText().toString());
        } catch (NumberFormatException e) {
            salaryET.setError("Format Error");
            noErrors = false;
        }
        try {
            signingBonus = Double.parseDouble(signingBonusET.getText().toString());
        } catch (NumberFormatException e) {
            signingBonusET.setError("Format Error");
            noErrors = false;
        }
        try {
            yearlyBonus = Double.parseDouble(yearlyBonusET.getText().toString());
        } catch (NumberFormatException e) {
            yearlyBonusET.setError("Format Error");
            noErrors = false;
        }
        try {
            retirementBenefits = Double.parseDouble(retirementBenefitsET.getText().toString());
        } catch (NumberFormatException e) {
            retirementBenefitsET.setError("Format Error");
            noErrors = false;
        }
        try {
            leaveTime = Integer.parseInt(leaveTimeET.getText().toString());
        } catch (NumberFormatException e) {
            leaveTimeET.setError("Format Error");
            noErrors = false;
        }

        if (noErrors) {
            JobDetails job = new JobDetails();

            if (isCurrentJob) {
                job = dbAppWrapper.getCurrentJob();
                if (job == null) job = new JobDetails();
            }

            job.setTitle(title);
            job.setCompany(company);
            job.setState(state);
            job.setCity(city);
            job.setCostOfLiving(col);
            job.setYearlySalary(salary);
            job.setSigningBonus(signingBonus);
            job.setYearlyBonus(yearlyBonus);
            job.setRetirementBenefits(retirementBenefits);
            job.setLeaveTime(leaveTime);
            job.setIsCurrentJob(isCurrentJob);

            Log.d("DYU",
                    String.format("Adding job details: id %d isCurrentJob %b", job.getId(), job.getIsCurrentJob()));
                    dbAppWrapper.persistOfferDetail(job);
            // if (isCurrentJob) {
            // cm.persistOfferDetail(job);
            // } else {
            // cm.persistOfferDetail(job);
            // }

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    public void clickCancel(View view) {
        // Return to main page
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
