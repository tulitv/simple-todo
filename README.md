# Simple Todo Application

This is an Android application for creating and managing a simple todo list, as pre-work requirement for [CodePath Android bootcamp](http://www.codepath.com/).

Time spent: 12 hours spent in total

Completed user stories:

 * [x] Required: Add new item to the list  by using text field and a button.
 * [x] Required: Remove item from the list by long clicking on the the item.
 * [x] Required: Save item list on local folder on every change on the list.
 * [x] Required: Load the saved item list on application start and populate list.
 * [x] Required: Edit item in the list by short clicking on the item using a new activity with a textfield and a button.
 * [x] Suggested: Persist the todo items into SQLite instead of text file. Use ActiveAndroid wrapper. 
 * [x] Suggested: Improve style of the todo items in the list using a custom adapter.
 * [x] Suggested: Add support for completion due dates for todo items (and display in the listview item)
 * [x] Suggested: Use a DialogFragment instead of new Activity for editing items.
 * [x] Optional: Added header as a first item in the listview, not part of the db, non-editable and non-deletable.

Notes:
 1. The walk-through slides say use Minimum SDK API 16 during setup, but later after the walkthrough the website says API 14 should be used. The app currently uses API 16.
 2. FocusRequest for the textfield on the Edit Item activity does not bring up the keyboard, but it is in focus (meaning works fine in debugger when you use actual pc keyboard).
 3. The ActiveAndroid JAR file is very outdated, couldn’t make it running. It was rebuilt from the library files.
 4. Initially I had both TodoItem and TodoItemsAdapter classes as inner classes of MainActivity, but ActiveAndroid had issues with getConstructor, then doing the custom adapter class had issues using static inner member/classes, so I needed to separate them.
 5. Having multiple fields / item, the focus is set to item name after starting, adding and when edit activity starts.
 6. Some modifications for the xml-s (MainActivity, EditItemActivity and todo_items) to create a better/more logical view.
 7. Due Data TextField is set to Date type, but no validity check is made on any TextFields.
 
Walkthrough of all user stories:

![Video Walkthrough](simple_todo.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

