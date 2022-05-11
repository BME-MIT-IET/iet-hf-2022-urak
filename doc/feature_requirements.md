# Feature Requirements / Assesments

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
