package edu.gatech.seclass.jobcompare6300;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Entity
public class JobDetails implements Comparable<JobDetails> {
    @Id(autoincrement = true)
    private Long id;
    private static final AtomicInteger count = new AtomicInteger(1);

    private String title;
    private String company;
    private String state;
    private String city;
    private double costOfLiving;
    private double yearlySalary;
    private double signingBonus;
    private double yearlyBonus;
    private double retirementBenefits;
    private int leaveTime;

    private Boolean isCurrentJob;

    @Transient
    private double AYS;
    @Transient
    private double ASB;
    @Transient
    private double AYB;
    @Transient
    private double RBP;
    @Transient
    private double score;

    @Generated(hash = 1178184221)
    public JobDetails() {
    }

//    public JobDetails() {
//        id = (long) count.incrementAndGet();
//    }

    @Generated(hash = 1113660619)
    public JobDetails(Long id, String title, String company, String state,
            String city, double costOfLiving, double yearlySalary,
            double signingBonus, double yearlyBonus, double retirementBenefits,
            int leaveTime, Boolean isCurrentJob) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.state = state;
        this.city = city;
        this.costOfLiving = costOfLiving;
        this.yearlySalary = yearlySalary;
        this.signingBonus = signingBonus;
        this.yearlyBonus = yearlyBonus;
        this.retirementBenefits = retirementBenefits;
        this.leaveTime = leaveTime;
        this.isCurrentJob = isCurrentJob;
    }

    @Override
    public int compareTo(JobDetails comp) {
        // return (int) ((this.score - comp.score) * 1000);

        if (this.score - comp.score > 0) return 1;
        else if (this.score - comp.score < 0) return -1;
        else return 0;
    }

    public void populateAdjustedValues (ComparisonWeights cw) {
        try {

            AYS = yearlySalary * 100 / costOfLiving;
            ASB = signingBonus * 100 / costOfLiving;
            AYB = yearlyBonus * 100 / costOfLiving;
            RBP = retirementBenefits;

            int AYS_w = cw.getYearlySalaryWeights();
            int ASB_w = cw.getSigningBonusWeights();
            int AYB_w = cw.getYearlyBonusWeights();
            int RBP_w = cw.getRetirementBenefitsWeights();
            int LT_w = cw.getLeaveTimeWeights();
            int total_weight = AYB_w + ASB_w + AYB_w + RBP_w + LT_w;

            score = AYS * AYS_w / total_weight
                    + ASB * ASB_w / total_weight
                    + AYB * AYB_w / total_weight
                    + RBP * AYS * RBP_w / total_weight
                    + (leaveTime * AYS / 260) * LT_w / total_weight;
        } catch (Exception e) {
            Log.e("JobDetails", "Some error happened:");
            e.printStackTrace();
            score = 0;
        }


    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getCostOfLiving() {
        return this.costOfLiving;
    }

    public void setCostOfLiving(double costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public double getYearlySalary() {
        return this.yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public double getSigningBonus() {
        return this.signingBonus;
    }

    public void setSigningBonus(double signingBonus) {
        this.signingBonus = signingBonus;
    }

    public double getYearlyBonus() {
        return this.yearlyBonus;
    }

    public void setYearlyBonus(double yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public double getRetirementBenefits() {
        return this.retirementBenefits;
    }

    public void setRetirementBenefits(double retirementBenefits) {
        this.retirementBenefits = retirementBenefits;
    }

    public int getLeaveTime() {
        return this.leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Boolean getIsCurrentJob() {
        return this.isCurrentJob;
    }

    public void setIsCurrentJob(Boolean isCurrentJob) {
        this.isCurrentJob = isCurrentJob;
    }

    public double getAYS() {
        return AYS;
    }

    public double getAYB() {
        return AYB;
    }

    public double getASB() {
        return ASB;
    }

    public double getRBP() {
        return RBP;
    }
}
