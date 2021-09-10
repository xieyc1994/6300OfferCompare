****Requirements Justification****

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).

     _**This is taken care by the GetJobOffers function in ComparisonManager class, this function is responsible to load the current job and offer details. If this method returns an empty list we will not display any job details and will make the button compareOffer disabled .**_

2. When choosing to enter current job details, a user will: 
    * Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of: 
        * Title 
        * Company 
        * Location (entered as city and state) 
        * Overall cost of living in the location (expressed as an index) 
        * Yearly salary 
        * Signing bonus 
        * Yearly bonus 
        * Retirement benefits (as percentage matched) 
        * Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)

        _**To realize this requirement, we added 'title', 'company', 'city', 'state','costOfLiving', 'yearlySalary', 'signingBonus', 'yearlyBonus', 'retirementBenefits', and 'leaveTime' to the Job class as attributes. We also added 'id' as an additional attribute for easier reference later on. Data types are defined properly for each attribute.**_

    * Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu. 

        _**Since saving/canceling/exiting are GUI-specific, they are not represented in our design.**_


3.	When choosing to enter job offers, a user will: 
    * Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.

        _**In our design, we have a JobDetails class which contains all the attributes of the job offer. After user saved details of the job offer, an object of JobDetails is created and stores the details that user entered. The object is added into the jobOffers list in ComparisonManager. The user interface will be handled within the GUI implementation, so it is not included in our design.**_
    
    * Be able to either save the job offer details or cancel. 

        _**When user saved details of the job offer, an object of JobDetails is created and stores the details that user entered. The object is added into the jobOffers list in ComparisonManager. When user canceled, the job details will not be stored.**_
    
    * Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present). 

        _**(1) and (2) will be handled entirely within the GUI implementation, so it is not shown in our design. For (3), when user selected compare with the current job, getJobOffer() method will be triggered, and display the current job details and the offer details.**_


4.	When adjusting the comparison settings, the user can assign integer weights to: 
    * Yearly salary 
    * Signing bonus 
    * Yearly bonus 
    * Retirement benefits 
    * Leave time 

    If no weights are assigned, all factors are considered equal. 

    _**This is represented in the design as the ComparisonSettings class. It will hold integer values for weights, and each weight is associated with one of the attributes listed in the requirements.**_

5.	When choosing to compare job offers, a user will: 
    * Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated. 
    * Select two jobs to compare and trigger the comparison.
    * Be shown a table comparing the two jobs, displaying, for each job: 
        * Title 
        * Company 
        * Location 
        * Yearly salary adjusted for cost of living 
        * Signing bonus adjusted for cost of living 
        * Yearly bonus adjusted for cost of living 
        * Retirement benefits (as percentage matched) 
        * Leave time 
    * Be offered to perform another comparison or go back to the main menu. 

   _**GetRankedOffers method of ComparisonManager will be called to get all the offers detail sorted as per score and will display the Title and Company. When user will select two offers to compare then system will call GetJobOffers function in ComparisonManager class to get the complete details of the offers and will display to the UI**_

6.	When ranking jobs, a job’s score is computed as the weighted sum of: 

    AYS + ASB + AYB + (RBP * AYS) + (LT * AYS / 260) 
    
    where: 

    AYS = yearly salary adjusted for cost of living 

    ASB = signing bonus adjusted for cost of living 

    AYB = yearly bonus adjusted for cost of living 

    RBP = retirement benefits percentage 

    LT = leave time 

    For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:
    
    2/7 * AYS + 1/7 * ASB + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) 


    _**To realize this requirement, we added a 'CalculateScore(JobDetails o) :int' operation. This operation will implement the above logic for calculating scores of each job.  The operation uses the job attributes and the weights set in the ComparisonSettings.**_ 

7. The user interface must be intuitive and responsive. 

    _**This is not represented in the design since it is a non-functional requirement.**_

8. The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app. 

    _**This is not represented in the design since it is a non-functional requirement.**_

9. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

    _**This is not represented in the design since it is just a guideline for our design.**_
