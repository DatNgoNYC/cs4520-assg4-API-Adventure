Notes:
You can test the "no product" ui by uninstalling the app, rerunning/reinstalling it and using the app on airplane mode. The database is not loaded so there's nothing in Room to display.

To test for database usage, you can login to the app and load the product list. Then exit out the app and kill the app on the emulator. Turn of lte/wifi and logging into the app again should show the product list.



To Do:
- backward navigation to LoginPage from prodctlist page doesnt work on nav page (investigate viewmodel, isSuccess property might stay at "true")

