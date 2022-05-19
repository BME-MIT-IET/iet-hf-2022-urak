# Manual and static analysis + SonarCloud

Sike Ádám - E8Z277

## Manual and static analysis report

* I decided to go with the following check list:

  0. Getting familiar with the project structure

  1. Feature requirements

  2. Code readability

  3. Code style

  4. Proper names

  5. Duplicated code

  6. Tests

  7. Documentation

## Requriments (set by me and the team)

Quoting from the original readme:

>An android app that shows timeline of upcoming rocket launches. Showcases architecture of a real android application and usage of some libraries. Application loads data about rocket launches from the server and stores them to the database for off-line usage.

Summary of what we think should be the requirements based on this quote:

* View a timeline of upcoming rocket launches
  * OK
* Said timeline is updated from a server
  * Kinda OK, the news feed is a dummy one  
* When off-line you can still view the launches but without updates
  * I don't think this feature works like intended, because the dummy API is called even when off-line?

Additional requirements based on (half)-implemented features already in the app:

* Filter launches with the use of tags
  * OK
* Set up notifications for interesting launches
  * Does not seem to work
* View the videos of launches if available
  * Dummy video is opened in YouTube
* Change the settings of the app, like day-night mode and source of updates
  * Mostly OK, the display of libraries is broken

## Manual

* At first I've checked the project by hand.
  * For some reason, Android Studio mentioned a lot of unresolved references, so I've fixed them in "Fixed Unresolved References".
  * The issue was a missing dependency from the app/build.gradle file. It was strange, because the project build was successful even with the unresolved references...  
  * Then I've found a todo for the jUnit test runner, which used deprecated stuff. I also found some commented out, broken tests. I removed the bad tests and replaced the deprecated code.

## Static

* I used the built in analyzer tool for Android Studio
  * A broken dependency for Google Play Services was fixed
  * Some deprecated functions were replaced
  * Enforced some naming conventions
  * Fixed Java calls
  * Made some local vars private
  * Made some literals constant
  * Found another missing dependency
  * Fixed dpendency issues realated to Room (again)
  * Upgraded project to Gradle 7.1.3
  * Found some todos, I could only do a few:
    * Added Toast message for connection errors
    * Added on the fly loading of images from URL suing Coil
    * Changed how rockets without pictures appear
* After these I focused on the potential issues highlighted by SonarLint. At first there was quite a few: ![Screenshot](//doc//pictures//SonarLintIssues.png) Luckily most of these was minor, so I began fixing them.
  * There were some unused imports still, many of them actually false positives
  * Some empty 'else' branches in 'when' statements were given comments. I could not delete them, because of naming conventions.
  * There were many high-complexity methods that were in compose. However refactoring them was something I could not do... Maybe a TODO?
  * There was another couple cases of naming convention violations
* I managed to get rid of about 10 of the issues. The rest were false alarms or way beyond my current level of understanding to solve. Nonetheless, I believe it to be quite an advancement. ![Screenshot](//doc//pictures//SonarLintIssuesAfter.png)

## Sonar Cloud

* Next up was making SonarCloud work properly... Oh boy
  * We needed to set up a new job for the worklow
  * For this we also needed to create a new keystore and upload the secrets to GitHub
  * Configure gradle to work properly
  * Disable build stop on finding errors
    * Note: error logs sadly did not show what the error was, this was option B.
  * I had to repeat this process many times
  * Changed project SDK to API 21
  * Ran analysis on local machine
  * Added a plugin that fixes some issues with Gradle Caching

  * I spent many hours trying to fix the issue. It worked!
  ![Screenshot](//doc//pictures//Sonar.png)
  * However the GitHub action still fails...
    * As far as I can tell, some tests won't run due to some file access violations
  * I've set up the analysis an my local PC and it works fine, so...
  * Also, I was running it as a part of a different organisation, but I fixed it with the help of Csani
