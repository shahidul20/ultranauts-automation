## Ultranauts-automation
Selenium Maven automated test project for ultranauts-automation-homework

##Overview

Created Maven Selenium WebDriver Java test project using JUnit testing framework.

This project is a Page Object Design pattern. Test data such as login credentials and URL are derived from the environment properties. There are two runtime environment variables: the environment where tests should run and the driver type.

Test project is mainly separated into three packages and one directory:

- helpers:

  - CommonHelper: includes common static helpers used in both page objects and tests
  - PageHelper: all helper methods for pages to inherit.
  - TestHelper: includes test helpers such as test driver loads, data setups, etc.

-  pages: All UI pages should be page objects.
   
-  tests: All test classes.

- resources: All test credential are included.


## Test Suite:
The test suite is maintained by a maven profile. All test classes that are part of the regression suite are under profile [regression-ui].

How To Run The Test

  Three Approaches:
   - Simply right-click on the tests package to run all tests under the package, test class name to run all tests under a test class, or on a test method name to simply run only that test.
   - From the Maven menu (Eclipse/IntelliJ) -> Select profile -> Select 'Test'
   - Maven Command: mvn clean test -Pregression-ui

How To Add New Test

 -  Add test method in 'tests' package with new test class or on in existing test class
 -  New test class should inherit TestHelper.
 -  Test class should not have any page elements.
 -  All page elements locator and steps such as set field, click/submit, get status text, etc should be in page object for the UI.
 -  Add all test step(s) in page object and test should instantiate the page object and call the steps from the page object.
 -  Page object should inherit PageHelper
 -  Any helper methods that interact with a UI page should be in the PageHelper class.