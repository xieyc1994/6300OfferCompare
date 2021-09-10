__New in V3__

Compared to V1, we added initial (partial) test results in V2, including unit testing and manual UI testing.

Compared to V2, we added final test results in V3, including unit testing and manual UI testing.

# Test Plan

*This is the test plan for the job offer compare application.*

**Author**: Team 35

## 1 Testing Strategy

### 1.1 Overall strategy

We are applying unit testing, and an end to end system testing to ensure that the system works as expected.

### 1.2 Test Selection

* Unit test: We are doing unite tests for critical functional modules (except basic getters and setters)

* System/integration test: On behalf of that we are also creating end to end system test cases ensure that the full system function as expected


### 1.3 Adequacy Criterion

For unit tests we will use JUnit and JaCoCo to write test cases and verify each function produces the desired result given various forms of valid and/or invalid inputs, overall we want to have more than 90% of the code coverage for the critical functionalities (except basic getters and setters). We will also use Espresso to write UI tests.

For system level tests we will primarily use manual tests methods to simulate a day to day real use case of our system, we will cover all basic and advanced use cases of the system.

 
### 1.4 Bug Tracking

Each developer will be responsible for writing their unit test cases for their own module and resolve any bugs they found during the development process.

Our QA person will be writing the business requirements and scenarios files and perform system level tests, he/she will also be responsible for running the tests and create bug tickets for developers to track and ensuring the system functions as desired.


### 1.5 Technology

JUnit, JaCoCo, Espresso, Manual

## 2 Test Cases

| Purpose | Steps | Expected result | Actual result | Pass/fail | Additional info |
|-|-|-|-|-|-|
|Test main menu functions|Run the application|User is presented with the main menu, which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet)| Functions exist  | All requirements passed  | Use Espresso automated testing tested the main interface and manually tested the UI  |
|Test enter current job details   |Click enter current job details button   |Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.| Functions exist  | All requirements passed  | Use Espresso and JUnit to create unit tests to test entering jobs -  passed; manually tested saving and editing jobs - passed; manually tested canceling saving and returning to main menu - passed   |
|Test enter job offers   |Click enter job offers button   |Be shown a user interface to enter all of the details of the offer, be able to either save the job offer details or cancel. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details.| Functions exist  | All requirements passed except for 'entering another offer'; but users can enter another offer from the main menu | Unit tested entering jobs -  passed; manually tested saving jobs - passed; manually tested canceling saving and returning to main menu - passed|
|Test adjust comparison settings   |Click adjust comparison settings button  |The user can assign integer weights to Yearly salary, Signing bonus, Yearly bonus, Retirement benefits, Leave time.   | Functions exist  | All requirements passed  |  Manually tested saving comparison weights - passed; manually tested canceling saving and returning to main menu - passed |   |
|Test compare job offers    |Click Test compare job offers button   |Be shown a list of job offers, displayed as Title and Company, ranked from best to worst, and including the current job (if present), clearly indicated. Be able to select two jobs to compare and trigger the comparison. Be shown a table comparing the two jobs, displaying, for each job. Be offered to perform another comparison or go back to the main menu.| Functions exist  | All requirements passed except for 'being offered to perform another comparison'; but users can perform another comparison by navigating back to the comparison interface  | Unit tested calculating the ranking score and manually tested the other functionalities |

