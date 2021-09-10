# User Manual
## Purpose
This tool allows a single user to compare job offers with benefits, in different locations, and other aspects as well as salary.
## Information
- App Name: JobCompare
- Developer: Team 35
- Compatibility: Android 9.0 (Pie)
- Language: English
- Written In: Java
## How to user it
1. In the Main Menu, click **Enter Current Job** or  **Enter Job Offer** to enter or edit a current job or a job offer.
2. In the Main Menu, click **Compare Jobs** to view a list of entered jobs (including current job if present and job offers). Check to select exactly two jobs to compare.
3. In the Main Menu, click **Set Comparison Weights** to give different weights to different aspects of the job descriptions.
4. In the view of job lists, jobs are ranked based on their scores in a descending order. Scores are computed as the ​weighted​ sum of:

    AYS + ASB + AYB + (RBP * AYS) + (LT * AYS / 260) 

    where:
    AYS = yearly salary adjusted for cost of living 

    ASB = signing bonus adjusted for cost of living 

    AYB = yearly bonus adjusted for cost of living RBP = retirement benefits percentage

    LT = leave time

    For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:
    2/7 * AYS + 1/7 * ASB + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260)


