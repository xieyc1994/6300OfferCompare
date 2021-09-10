package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterWeights extends AppCompatActivity {
    ComparisonManager cm;
    ComparisonManager dbAppWrapper;

    Button saveButton;
    private View.OnClickListener sListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickSave(view);
        }
    };

    Button cancelButton;
    private View.OnClickListener cListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickCancel(view);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_weights);

        cm = ComparisonManager.getInstance(); 
        dbAppWrapper = (ComparisonManager) getApplication();

        saveButton = findViewById(R.id.button_save_comparison_weights);
        saveButton.setOnClickListener(sListener);

        cancelButton = findViewById(R.id.button_cancel_comparison_weights);
        cancelButton.setOnClickListener(cListener);

        ComparisonWeights cw = dbAppWrapper.getComparisonWeights();
        if (cw.getYearlySalaryWeights() > 0)
        {
            EditText etYearlySalary = findViewById(R.id.editText_yearly_salary_weight);
            etYearlySalary.setText(Integer.toString(cw.getYearlySalaryWeights()));
        }
        if (cw.getSigningBonusWeights() > 0)
        {
            EditText etSigningBonus = findViewById(R.id.editText_signing_bonus_weight);
            etSigningBonus.setText(Integer.toString(cw.getSigningBonusWeights()));
        }
        if (cw.getYearlyBonusWeights() > 0)
        {
            EditText etYearlyBonus = findViewById(R.id.editText_yearly_bonus_weight);
            etYearlyBonus.setText(Integer.toString(cw.getSigningBonusWeights()));
        }
        if (cw.getRetirementBenefitsWeights() > 0)
        {
            EditText etRetirementBonus = findViewById(R.id.editText_retirement_benefits_weight);
            etRetirementBonus.setText(Integer.toString(cw.getRetirementBenefitsWeights()));
        }
        if (cw.getLeaveTimeWeights() > 0)
        {
            EditText etLeaveTime = findViewById(R.id.editText_leave_time_weight);
            etLeaveTime.setText(Integer.toString(cw.getLeaveTimeWeights()));
        }
    }

    public void clickSave(View view) {
        ComparisonWeights cw = new ComparisonWeights();

        EditText etYearlySalary = findViewById(R.id.editText_yearly_salary_weight);
        int yearlySalaryWeight = Integer.parseInt(etYearlySalary.getText().toString());
        cw.setYearlySalaryWeights(yearlySalaryWeight);

        EditText etSigningBonus = findViewById(R.id.editText_signing_bonus_weight);
        int signingBonusWeight = Integer.parseInt(etSigningBonus.getText().toString());
        cw.setSigningBonusWeights(signingBonusWeight);

        EditText etYearlyBonus = findViewById(R.id.editText_yearly_bonus_weight);
        int yearlyBonusWeight = Integer.parseInt(etYearlyBonus.getText().toString());
        cw.setYearlyBonusWeights(yearlyBonusWeight);

        EditText etRetirementBonus = findViewById(R.id.editText_retirement_benefits_weight);
        int retirementBonusWeight = Integer.parseInt(etRetirementBonus.getText().toString());
        cw.setRetirementBenefitsWeights(retirementBonusWeight);

        EditText etLeaveTime = findViewById(R.id.editText_leave_time_weight);
        int leaveTimeWeight = Integer.parseInt(etLeaveTime.getText().toString());
        cw.setLeaveTimeWeights(leaveTimeWeight);

        dbAppWrapper.setComparisonWeights(cw);

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void clickCancel(View view) {

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}
