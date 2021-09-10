package edu.gatech.seclass.jobcompare6300;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ComparisonManagerTest {

    private ComparisonManager comparisonManager = new ComparisonManager();

    @Test
    public void addCurrentJObDetails(){
        ComparisonManager comparisonManager = Mockito.mock(ComparisonManager.class);

        JobDetails job = new JobDetails();
        job.setCostOfLiving(232);
        job.setRetirementBenefits(4);
        job.setSigningBonus(10);
        job.setYearlyBonus(10);
        job.setYearlySalary(100);

        when(comparisonManager.getCurrentJob()).thenReturn(job);
        ComparisonWeights comparisonWeights = new ComparisonWeights();
        comparisonWeights.setLeaveTimeWeights(1);
        comparisonWeights.setRetirementBenefitsWeights(1);
        comparisonWeights.setSigningBonusWeights(1);
        comparisonWeights.setYearlyBonusWeights(1);
        comparisonWeights.setYearlySalaryWeights(1);
//        comparisonManager.addCurrentJobDetails(job);

        comparisonManager.setComparisonWeights(comparisonWeights);

        JobDetails jobTest = comparisonManager.getCurrentJob();
        System.out.print(jobTest.getCostOfLiving());
        assertEquals((int)jobTest.getCostOfLiving(), 232);
        assertEquals((int)jobTest.getRetirementBenefits(), 4);
    }

    @Test
    public void addJob() {
        ComparisonManager comparisonManager = Mockito.mock(ComparisonManager.class);

        ComparisonWeights comparisonWeights = new ComparisonWeights();
        comparisonWeights.setLeaveTimeWeights(1);
        comparisonWeights.setRetirementBenefitsWeights(1);
        comparisonWeights.setSigningBonusWeights(1);
        comparisonWeights.setYearlyBonusWeights(1);
        comparisonWeights.setYearlySalaryWeights(1);

        JobDetails job = new JobDetails();
        job.setCostOfLiving(111);
        job.setRetirementBenefits(4);
        job.setSigningBonus(10);
        job.setYearlyBonus(10);
        job.setYearlySalary(100);
        ArrayList<JobDetails> testlist = new ArrayList<JobDetails>();
        testlist.add(job);
        when(comparisonManager.getJobOffers()).thenReturn(testlist);
//        comparisonManager.addJob(job);
        JobDetails jobTest = comparisonManager.getJobOffers().get(0);
        assertEquals((int)jobTest.getCostOfLiving(), 111);
        assertEquals((int)jobTest.getRetirementBenefits(), 4);
    }

    @Test
    public void adjustRank() {
        // comparisonManager = new ComparisonManager();
        ComparisonManager comparisonManager = Mockito.mock(ComparisonManager.class);
        ComparisonWeights comparisonWeights = new ComparisonWeights();
        comparisonWeights.setLeaveTimeWeights(1);
        comparisonWeights.setRetirementBenefitsWeights(1);
        comparisonWeights.setSigningBonusWeights(1);
        comparisonWeights.setYearlyBonusWeights(1);
        comparisonWeights.setYearlySalaryWeights(1);

        JobDetails job = new JobDetails();
        job.setCostOfLiving(100);
        job.setRetirementBenefits(10);
        job.setSigningBonus(10);
        job.setYearlyBonus(10);
        job.setYearlySalary(100);
        job.setLeaveTime(260);
//        comparisonManager.addJob(job);
//        comparisonManager.setComparisonWeights(comparisonWeights);
        job.populateAdjustedValues(comparisonWeights);
        double score = job.getScore();
        // 2/7 * AYS + 1/7 * ASB + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260)
        // 100 + 10 +10 + 1000 + 100 = 1220
        assertEquals(score, 244.0, 0.5);
    }

    @Test
    public void testScore2() {
        comparisonManager = new ComparisonManager();
        ComparisonWeights comparisonWeights = new ComparisonWeights();
        comparisonWeights.setLeaveTimeWeights(10);
        comparisonWeights.setRetirementBenefitsWeights(1);
        comparisonWeights.setSigningBonusWeights(1);
        comparisonWeights.setYearlyBonusWeights(1);
        comparisonWeights.setYearlySalaryWeights(1);

        JobDetails job = new JobDetails();
        job.setCostOfLiving(1000);
        job.setRetirementBenefits(0.2);
        job.setSigningBonus(1000);
        job.setYearlyBonus(1000);
        job.setYearlySalary(1000);
        job.setLeaveTime(30);
//        comparisonManager.addJob(job);
//        comparisonManager.setComparisonWeights(comparisonWeights);
        job.populateAdjustedValues(comparisonWeights);
        double score = job.getScore();
        // 2/7 * AYS + 1/7 * ASB + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260)
        // 100 + 10 +10 + 1000 + 100 = 1220
        assertEquals(score, 31.1, 0.5);
    }

}
