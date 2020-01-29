# INTRODUCTION:
Application is a simple testing framework dedicated to ryanair.com page. It contains one test that checks buying flight
tickets process using invalid credit card. Test contains three runs, each for one of the supported web browsers.

# REQUIREMENTS:
* java 1.8
* maven
* Chrome, Firefox or Internet Explorer web browser
* no web drivers needed. They are attached in the solution

# RUN:
* download a repository
* to run tests use:
```bash
mvn clean test
```
* to create report use:
```bash
mvn site
```
* allure report will be generated in '...\target\site\allure-maven-plugin\' directory

# SHORT DESCRIPTION:
Application is a Maven project based on Cucumber and Junit frameworks. As a reporting tool it has been implemented
Allure plugin. Solution uses 'page object pattern' and few test automation design patterns as 'driver in singleton
convention', 'browser factory' etc...

There are few elements that have caused difficulties during automation because of their 'unusuality' and they are
worth attention:
* departure/destination airport TextFields
* flight day setting mechanism
* setting passengers details
* dropBoxes

Final assertion is implemented unusually - it compares existing error message to two potential error messages.
Due to the repetition of test data, sometimes an error monit displays 'already booked flight' message instead of a
payment error message. Without documentation and Analyst opinion I interpret this scenario also as a positive result.

The biggest problem in this solution was waiting for loaded pages. The problem was almost completely solved by the
'waitUntil' methods in actions related to buttons and fields, but sometimes pages seams to reload and tests crash...
Because of a time pressure I implemented the 'Thread.sleep' method as a temporary solution :/
As a final solution I would try to implement 'waitAsLongAsVisible' method on page's spinners.


