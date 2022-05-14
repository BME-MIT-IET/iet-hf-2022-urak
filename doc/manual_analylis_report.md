# Manual and static analysis report

* At first I've checked the project by hand. For some reason, Android Studio mentioned a lot of unresolved references, so I've fixed them in "Fixed Unresolved References".
  * The issue was a missing dependency from the app/build.gradle file. It was strange, because the project build was successful even with the unresolved references...  
 