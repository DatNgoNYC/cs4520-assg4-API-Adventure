Notes:
You can test the "no product" ui by uninstalling the app, rerunning/reinstalling it and using the app on airplane mode. The database is not loaded so there's nothing in Room to display.

To test for database usage, you can login to the app and load the product list. Then exit out the app and kill the app on the emulator. Turn of lte/wifi and logging into the app again should show the product list.

To Do:
- backward navigation to LoginPage from prodctlist page doesnt work on nav page (investigate viewmodel, isSuccess property might stay at "true")

________________________________________________________________________________________________________________________________________________

In this assignment, continue building on assignment 2- Amazing Products. Before we start, we will make sure to amend our assignment 2 as per TA feedback.

In this assignment, Instead of the static dataset, we need to fetch the data from a given api. 

Api details : 

1. Base URL : https://kgtttq6tg9.execute-api.us-east-2.amazonaws.com/

2. End Point with optional query : page number:  /prod  &  /?page=3

3. Sample Json Response :  HereLinks to an external site. 


Api will work as follows : 

1. It may return a list of products based on page number. If no page number is specified, it will return the first 30 products. Page number is optional. 

2. It will be successful but return no products. 


Points to remember :

Use Recyclerview, MVVM, livedata & constraint layouts wherever applicable.  
Data should be loaded on when the user navigates to the Product list fragment. Load data using retrofit and coroutines. 
For Loading data : 
While the data is loading, the user should see an infinite progress bar. 
On success, present it to the UI and save it to the database. 
If api returns 0-zero results, display a message on screen “No products available”.
Json can have repeated products.This should not be shown on UI.
Products in json can have empty or missing data. This should not be shown on UI.
When offline, data should be fetched from the database if available and displayed on UI.
 

Bonus points (2 points) : Implement pagination using optional page parameters. This can be applied to any assignment.


Starter Project

Please copy the dependencies from the starter projectLinks to an external site. into your assignment 2 gradle file. 

Submission Guidelines :

Add a readme file to your project :
Provide a link to your github repo. 
Any other information needed to run project. 
Zip your android application and upload it to Canvas.
