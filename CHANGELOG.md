ChangeLog
=========

**Version 0.6.0**

+ Updated the update functions.
+ Removed some CSS. 
+ Fixed the remove function on the View Club page.
+ Created a new exception for failing to update.
+ Reworked the GUI to have a 'tutorial' on first run. Also started to integrate a help window.
+ Removed depreciated files.
+ Fixed ``WRITEABORTED`` exception.
+ Reworked Main Menu.
+ Added a ``Help`` menu.
  + Created a simple (currently) help window, accessible via ``Help -> Help``
  + Created an ``About`` window with contact and diagnostic information.
+ Added ability to send diagnostic email from within the program -- available from the help menu. <i>Debug mode only currently
using for testing purposes only. Not working properly due to SMTP Server issues.</i>
+ Added ability to update the jar file. <b><i>Still extremely buggy. Use with care, as it may erase the file
permanently. Entry in ``debug mode`` only.</i></b>

**Version 0.5.10**

+ Removed the extra GUIFunctions classes and put them into a single file inside ``nefra.db``

**Version 0.5.9**

+ Reverted previous version due to program breaking bugs. Currently back at Logging entry when in DebugMode.

**Version 0.5.8**

+ Started adding error checking and input checking when creating Referees, Clubs, Games and Divisions.

**Version 0.5.7**

+ Fixed bugs relating to file permission errors and file naming errors when building due to ``gradle build`` 
version number incrementation

**Version 0.5.6**

+ Implemented boolean functions to increment version numbers (Major, Minor, Micro, Build Number)
only when necessary by switching ``false`` to ``true`` and visa-versa. Will be more efficient in later
versions of program, not during initial development.

**Version 0.5.5**

+ Added ``version.properties`` to work with the gradle auto-increment and reworked the
``build.gradle`` file accordingly to make it viable. Allows editing if versioning gets out of hand.

**Version 0.5.4**

+ Added exit codes to identify errors easier, needs further implementing around various functions.

**Version 0.5.3**

+ Fixed a couple of bugs relating to the previous update

**Version 0.5.2**

+ Reworked the make functions in the GUIFunctions classes to make them return a boolean
to check for errors.

**Version 0.5.1**

+ Added popups for multiple data entry on creation interface
+ Created auto-increment script for ``gradle build`` using ``Date.now()``

**Version 0.5.0**

+ Major rewrite of how the program handles saving and loading data
moving to a fully file funded solution rather than embedded database.
+ Most GUI classes have been rewritten to accommodate this change.
+ 2 new classes have been added - `SysLoader` and `SysCreator` to handle
loading and saving.
+ Created a custom Logger which has it's own Logs folder in the data folder
to easily spot any errors and issues that are reported.
+ Fixed issue where Editing would not update the `ChoiceBox` to the new
display value (i.e. change of name).
+ Started preparing for the removal of the old database classes, not yet 
deleted as some components, at time of writing, still use it temporarily.
+ Created new exceptions for easy diagnosis of issues.
+ Put all file and path names into a separate static file for easier access to change
a single one to reflect across the board.

**Version 0.0.7**
+ Added new features, the ability to remove rom the GUI, 
Edit and View all components. Started work on a debug mode to find 
other erroneous errors.
+ Updated JavaDoc.
+ Removed excess Constructors from all classes.
+ Added Remove functions -- **Not** working though.
+ Added Edit (*Update*) functions. All working
+ Added Debug mode so normal running end-user mode is less cluttered.
    + Use `debug` as a CLI argument.
+ Added Equals and HashCode overrides for custom classes (Ref, Club, Div and Game)
+ Revamped the SettingsGUI and have it currently disabled in the end-user mode,
 due to some bugs which I am working on after the current rewrite.
+ Tidied up some if statements and converted some to other conditionals.
+ Set up tables to view entered data. Currently not working properly on 
some resolutions, possibly due to column constraints.
+ Added a couple of classes to hold the BorderPanes with titles, and also a
custom GridPane for the settings menu.
+ Added some CSS styling and custom fonts. 
    + Fonts currently not working.
