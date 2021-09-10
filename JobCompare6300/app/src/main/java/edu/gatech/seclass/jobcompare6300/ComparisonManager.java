package edu.gatech.seclass.jobcompare6300;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;

public class ComparisonManager extends Application {
    private static final ComparisonManager singleton = new ComparisonManager();
    public static ComparisonManager getInstance() {return singleton;}

    private DaoSession daoSession;

    private JobDetailsDao jobDetailsDao;

    private ComparisonWeightsDao comparisonWeightsDao;

    // private ComparisonWeights comparisonWeights = new ComparisonWeights();

    @Override
    public void onCreate() {
        super.onCreate();

        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "offers-db");

        Database db = helper.getWritableDb();

        //        --- REMOVED IN PRODUCTION ---
//                DaoMaster.dropAllTables(helper.getWritableDb(), true);
                DaoMaster.createAllTables(helper.getWritableDb(), true);

        daoSession = new DaoMaster(db).newSession();
        jobDetailsDao = daoSession.getJobDetailsDao();
        comparisonWeightsDao = daoSession.getComparisonWeightsDao();
    }

//    JobDetails currentjob = null;
//    ArrayList<JobDetails> jobslist = new ArrayList<JobDetails>();



//    public void addCurrentJobDetails(JobDetails currjob){
//        this.currentjob = currjob;
//    }


//    public void addJob(JobDetails offer){
//        this.jobslist.add(offer) ;
//    }

    public  List<JobDetails> getRankedJobs(){
//        updateScore();

//        ArrayList<JobDetails> list = new ArrayList<JobDetails>(this.jobslist);
        List<JobDetails> list = getAllJobOffers();
//        list.add(getCurrentJob());
        Collections.sort(list, Collections.<JobDetails>reverseOrder());
        return list;
    }

//    public void updateScore() {
//        this.currentjob.populateAdjustedValues(comparisonWeights);
//        for (JobDetails job : this.jobslist) {
//            job.populateAdjustedValues(comparisonWeights);
//        }
//    }

//    public void adjustRank(){
//        //For example, Prague salary of 50,000 -> adj. salary = 50,000 * 100 / 100 = 50,000.
//        // Chicago salary 100,000 -> adj. salary  = 100,000 * 100 / 196 = 51,020.
//        // Since the cost of living in Chicago is estimated to be 96% higher than in Prague, double the salary is barely more than equal.
//        //AYS + ASB + AYB + (RBP * AYS) + (LT * AYS / 260)
//
//        int AYS_w = comparisonWeights.getYearlySalaryWeights();
//        int ASB_w = comparisonWeights.getSigningBonusWeights();
//        int AYB_w = comparisonWeights.getYearlyBonusWeights();
//        int RBP_w = comparisonWeights.getRetirementBenefitsWeights();
//        int LT_w = comparisonWeights.getLeaveTimeWeights();
//        int total_weight = AYB_w + ASB_w + AYB_w + RBP_w + LT_w;
//
//        for(int i=0; i< this.jobslist.size();i++) {
//
//            JobDetails jobDetail = jobslist.get(i);
//            int AYS = jobDetail.getYearlySalary() * 100 / jobDetail.getCostOfLiving();
//            int ASB = jobDetail.getSigningBonus() * 100 / jobDetail.getCostOfLiving();
//            int AYB = jobDetail.getYearlyBonus() * 100 / jobDetail.getCostOfLiving();
//            int RBP = jobDetail.getRetirementBenefits();
//            int LT = jobDetail.getLeaveTime();
//            // 2/7 * AYS + 1/7 * ASB + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260)
//            int score = AYS * AYS_w / total_weight
//                    + ASB * ASB_w / total_weight
//                    + AYB * AYB_w / total_weight
//                    + RBP * AYS * RBP_w / total_weight
//                    + LT_w / total_weight * (LT * AYS / 260);
//            jobslist.get(i).setScore(score);
//        }
//    }

    public void persistOfferDetail(String title, String company, String state, String city,
                                    double costOfLiving, double yearlySalary, double signingBonus,
                                    double yearlyBonus, double retirementBenefits, int leaveTime,
                                    boolean isCurrentJob) {
        JobDetails jobDetails = JobDetails.builder().title(title).company(company)
                .state(state).city(city).costOfLiving(costOfLiving).yearlySalary(yearlySalary)
                .signingBonus(signingBonus).yearlyBonus(yearlyBonus).retirementBenefits(retirementBenefits)
                .leaveTime(leaveTime).isCurrentJob(isCurrentJob).build();
        jobDetailsDao.insert(jobDetails);
        Log.d("DaoAccess", "Inserted new job offer, ID: " + jobDetails.getId() + jobDetails.getIsCurrentJob());
    }

    public void persistOfferDetail(JobDetails jobDetails) {
        if (jobDetails.getId() == null || jobDetails.getId() == 0) {
            jobDetailsDao.insert(jobDetails);
        } else {
            jobDetailsDao.update(jobDetails);
        }
    }

    public JobDetails getCurrentJob() {
//        return this.currentjob;
        Query<JobDetails> jobDetailsQuery = jobDetailsDao.queryBuilder().where(JobDetailsDao.Properties.IsCurrentJob.eq(true)).build();
        List<JobDetails> resultList = jobDetailsQuery.list();
        if (resultList.size() > 1) Log.e("DaoAccess", "More than one current job in db");
        JobDetails result = resultList.size() == 0 ? null : jobDetailsQuery.list().get(0);
        if (result != null) result.populateAdjustedValues(getComparisonWeights());
        return result;
    }

    public List<JobDetails> getJobOffers() {
//        return this.jobslist;
        Query<JobDetails> jobDetailsQuery = jobDetailsDao.queryBuilder().where(JobDetailsDao.Properties.IsCurrentJob.eq(false)).build();
        List<JobDetails> results =  jobDetailsQuery.list();
        for (JobDetails jd : results) jd.populateAdjustedValues(getComparisonWeights());
        return results;
    }

    public List<JobDetails> getAllJobOffers() {
        List<JobDetails> results =  jobDetailsDao.loadAll();
        for (JobDetails jd : results) jd.populateAdjustedValues(getComparisonWeights());
        return results;
    }


//    public void setComparisonWeights(ComparisonWeights newWeights){
//        this.comparisonWeights = newWeights;
//        persistWeights(newWeights);
//        updateScore();
//        adjustRank();
//    }

//    public ComparisonWeights getComparisonWeights() {
//        return comparisonWeights;
//    }

    public void setComparisonWeights(ComparisonWeights comparisonWeights) {
        comparisonWeights.setId(1l);

        if (comparisonWeightsDao.loadAll().size() >= 1) {
            comparisonWeightsDao.update(comparisonWeights);
        } else {
            comparisonWeightsDao.insert(comparisonWeights);
        }
    }

    public ComparisonWeights getComparisonWeights() {
        ComparisonWeights result = comparisonWeightsDao.load(1l);
        if (result == null) {
            result = new ComparisonWeights(0l,1,1,1,1,1);
            setComparisonWeights(result);
        }
        return result;
    }

//    public JobDetails getCurrentJob(){
//        return this.currentjob;
//    }

//    public  ArrayList<JobDetails> getAllJObs(){
//        return this.jobslist;
//    }

}
