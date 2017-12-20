ChangeLog
=========

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