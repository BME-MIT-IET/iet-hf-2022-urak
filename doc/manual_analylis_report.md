# Manual and static analysis report

* At first I've checked the project by hand.
  * For some reason, Android Studio mentioned a lot of unresolved references, so I've fixed them in "Fixed Unresolved References".
  * The issue was a missing dependency from the app/build.gradle file. It was strange, because the project build was successful even with the unresolved references...  
  * Then I've found a todo for the jUnit test runner, which used deprecated stuff. I also found some commented out, broken tests. I removed the bad tests and replaced the deprecated code.
* Then I used the built in analyzer tool for Android Studio
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
