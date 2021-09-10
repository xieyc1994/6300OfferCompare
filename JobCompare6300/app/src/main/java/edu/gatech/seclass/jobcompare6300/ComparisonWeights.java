package edu.gatech.seclass.jobcompare6300;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ComparisonWeights {

    @Id(autoincrement = true)
    private Long id;
    private int yearlySalaryWeights = 1;
    private int signingBonusWeights = 1;
    private int yearlyBonusWeights = 1;
    private int retirementBenefitsWeights = 1;
    private int leaveTimeWeights = 1;

    @Generated(hash = 1247813242)
    public ComparisonWeights(Long id, int yearlySalaryWeights,
            int signingBonusWeights, int yearlyBonusWeights,
            int retirementBenefitsWeights, int leaveTimeWeights) {
        this.id = id;
        this.yearlySalaryWeights = yearlySalaryWeights;
        this.signingBonusWeights = signingBonusWeights;
        this.yearlyBonusWeights = yearlyBonusWeights;
        this.retirementBenefitsWeights = retirementBenefitsWeights;
        this.leaveTimeWeights = leaveTimeWeights;
    }

    @Generated(hash = 384507929)
    public ComparisonWeights() {
    }

    public int getYearlySalaryWeights() {
        return yearlySalaryWeights;
    }

    public void setYearlySalaryWeights(int yearlySalaryWeights) {
        if (yearlySalaryWeights < 1) yearlySalaryWeights = 1;
        this.yearlySalaryWeights = yearlySalaryWeights;
    }

    public int getSigningBonusWeights() {
        return signingBonusWeights;
    }

    public void setSigningBonusWeights(int signingBonusWeights) {
        if (signingBonusWeights < 1) signingBonusWeights = 1;
        this.signingBonusWeights = signingBonusWeights;
    }

    public int getYearlyBonusWeights() {
        return yearlyBonusWeights;
    }

    public void setYearlyBonusWeights(int yearlyBonusWeights) {
        if (yearlyBonusWeights < 1) yearlyBonusWeights = 1;
        this.yearlyBonusWeights = yearlyBonusWeights;
    }

    public int getRetirementBenefitsWeights() {
        return retirementBenefitsWeights;
    }

    public void setRetirementBenefitsWeights(int retirementBenefitsWeights) {
        if (retirementBenefitsWeights < 1) retirementBenefitsWeights = 1;
        this.retirementBenefitsWeights = retirementBenefitsWeights;
    }

    public int getLeaveTimeWeights() {
        return leaveTimeWeights;
    }

    public void setLeaveTimeWeights(int leaveTimeWeights) {
        if (leaveTimeWeights < 1) leaveTimeWeights = 1;
        this.leaveTimeWeights = leaveTimeWeights;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}