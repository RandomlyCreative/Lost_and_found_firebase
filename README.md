# LOST_AND_FOUND APP INFO AND SCREENSHOTS<br>

### INFO:

In this application we are bringing the vast database of the lost&found department of our college to our handheld device which makes it easier to find the lost items and claim them online. In case of multiple claims of a single item, every user who has laid a claim has to explain in the section “How do we know it’s yours”. Also the staff at warden office will verify it. This reduces the work of both staff and students by organizing things in the rightful way.

In the main tab you can see all the lost items which are been entered in the database by the warden office staff. All you’ve to do is click on it and claim the item if its yours. In the requests tab you will get the information at what time you’ve to report to the warden office for further process for all the things that you’ve claimed. User can also view/ update their profile in case of change of room or some other case.

Adding of database makes it very efficient and useful as it becomes real time so you can view your requests’ response and also view the newly added items the minute it is added.

●	Login and register activities
	
At the start of the App, if the user is not registered, the register page opens asking for the email and password. If the user is already registered and logged out, the user can go to login activity via textview and enter the same details.
These activities use firebase authentication for user details.
After registering the user is forwarded to the userinfo page.
After login the as the user info is already filled, the user is directed to the primary tabbed activity.


●	Userinfo page

Here the user enters personal details such as name, sapid and roomno. for future reference. These details are stored into the database and used as already collected info in request and report pages. User profile pic is not used as it has no requirement in a lost and found app. UserId card image will be implemented in the future for better verification.


●	Tabbed primary activity

This tabbed activity is the primary activity  which has the 2 recyclerviews in 2 tabs- Items and Requests. “Items” tab contains the list of lost items taken from an already filled database. “Requests” is the recyclerview list for claims and lost reports which the user adds dynamically from the request and report page. It shows item, location and the response of the admin.
The items in the ITEMS tab have an OnItemClickListener on them which opens a custom dialog box which directs the user to request claim on selected item.
It has a report lost item button which takes the user to report page. The page has menu items userinfo and logout. Userinfo directs the user to userinfo page to edit details in database. Logout uses MAuth.signout() to sign out. 


●	Report and Request Activities

These activities have edittexts for itemname, username, item location, userroom, user sapid and also an identification of the item by the user is required to proceed.
The user info is taken via database in both the activities. Itemname and itemlocation is taken via intent bundle in request activity. Report activity does not require item details as they are entered by the user.
The edittexts of the already filled locations are disabled.

●	4.4 FireBase Database and Implementation:

This document covers the basics of reading and writing Firebase data.Firebase data is written to a FirebaseDatabase reference and retrieved by attaching an asynchronous listener to the reference. The listener is triggered once for the initial state of the data and again anytime the data changes.
Firebase Authentication provides backend services, easy-to-use SDKs, and ready-made UI libraries to authenticate users to your app. It supports authentication using passwords, phone numbers, popular federated identity providers like Google, Facebook and Twitter, and more.
Firebase Authentication integrates tightly with other Firebase services, and it leverages industry standards like OAuth 2.0 and OpenID Connect, so it can be easily integrated with your custom backend.
Firebase has been used in this project for implemenation of database and user authentication.
Signin and registration of user is done via FirebaseAuth.
List of items, item requests/reports, userinfo are stored into database using FirebaseDatabase.
The views are refreshed OnDataChange in the database using list.clear();

●4.5 Dialog Box

A dialog is a small window that prompts the user to make a decision or enter additional information. A dialog does not fill the screen and is normally used for modal events that require users to take an action before they can proceed.Custom dialogbox has been used in this application to ask whether or not to request claim on an item.

●	4.6 INTENT

An intent is an abstract description of an operation to be performed. It can be used with startActivity to launch an Activity, send data bundles to different activities. An activity cannot recieve an empty bundle. It will lead to a nulllpointerexception and the acrivity will crash.
	Intent has been used to take itemname and itemlocation from activity “TAB1” and send it to requestpage activity.


●	4.7 BUTTONS

Represents a push-button widget. Push-buttons can be pressed, or clicked, by the user to perform an action.
This class represents the basic building block for user interface components. Buttons have been used at various places in this application, such as : registerbutton, loginbutton, reportlostitem button, requestbutton. Butons have been used to store data into the firebase database and start new activities.		


●	4.8 RECYCLER VIEW


The RecyclerView is a new ViewGroup that is prepared to render any adapter-based view in a similar way. It is supposed to be the successor of ListView and GridView, and it can be found in the latest support-v7 version. One of the reasons is that RecyclerView has a more extensible framework, especially since it provides the ability to implement both horizontal and vertical layouts. Use the RecyclerView widget when you have data collections whose elements change at runtime based on user action or network events.
If you want to use a RecyclerView, you will need to work with the following:
●	RecyclerView.Adapter - To handle the data collection and bind it to the view

●	LayoutManager - Helps in positioning the items

●	ItemAnimator - Helps with animating the items for common operations such as Addition or Removal of item
 

●	Compared to ListView
RecyclerView differs from its predecessor ListView primarily because of the following features:

●	Required ViewHolder in Adapters - ListView adapters do not require the use of the ViewHolder pattern to improve performance. In contrast, implementing an adapter for RecyclerView requires the use of the ViewHolder pattern for which it uses RecyclerView.Viewholder.

●	Customizable Item Layouts - ListView can only layout items in a vertical linear arrangement and this cannot be customized. In contrast, the RecyclerView has a RecyclerView.LayoutManager that allows any item layouts including horizontal lists or staggered grids.

●	Manual Data Source - ListView had adapters for different sources such as ArrayAdapter and CursorAdapter for arrays and database results respectively. In contrast, the RecyclerView.Adapter requires a custom implementation to supply the data to the adapter.

●	ManualClickDetection - ListView has a AdapterView.OnItemClickListener interface for binding to the click events for individual items in the list. In contrast, RecyclerView only has support for RecyclerView.OnItemTouchListener which manages individual touch events but has no built-in click handling.

Components of a RecyclerView

●	LayoutManagers

A RecyclerView needs to have a layout manager and an adapter to be instantiated. A layout manager positions item views inside a RecyclerView and determines when to reuse item views that are no longer visible to the user.
RecyclerView provides these built-in layout managers:

●	LinearLayoutManager shows items in a vertical or horizontal scrolling list.

●	GridLayoutManager shows items in a grid.

●	StaggeredGridLayoutManager shows items in a staggered grid.


●	RecyclerView.Adapter

RecyclerView includes a new kind of adapter. It’s a similar approach to the ones you already used, but with some peculiarities, such as a required ViewHolder. You will have to override two main methods: one to inflate the view and its view holder, and another one to bind data to the view. The good thing about this is that first method is called only when we really need to create a new view. No need to check if it’s being recycled.


-Recyclerview has been used in both the tab activities.
Tab1=> to show the list of lost items.
Tab2=> to show the list of requests and reports.
Data from the database is shown using linearlayouts which are iterated using an iterator.
The views are refreshed OnDataChange in the database using list.clear();

●Advantages

The following application simplifies the connectivity to students through mobile devices. The system is deployed on every student’s mobile and the individual can utilize the application for their lost items which are stored in the mobile database. The software focuses on the easy way getting information on what all items are lost with the use of mobile phones.

1. Organized: 
This application is helpful to make the lost items organized and easy to be found by the user. The application is having database connectivity which make it real-time and useful for on the go updates of lost items.

2. Easy to use interface: 
This app uses simple language to make user easily understand the concepts instead of using much more complex language which makes it more difficult for the user to understand. 

3. Implementation of Database: 
This application uses Firebase as its database although in the application’s early stages we implemented Google’s spreadsheets as the database which makes it easy to use and Realtime.

4. Reporting lost items: 
The user can report for the lost item in the Section provided under the main activity. There are different fields which are to be filled to report the lost item.

5. Information on the go: 
The user gets the latest information on their handheld device without having to go to the warden office to inquire about the lost items.

### ScreenShots: 
