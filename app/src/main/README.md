# Assignment 5: Crafting with Compose

## by Brandon Hampstead

Github repo [link](https://github.com/bham11/cs4520-assg5)

The Compose functions can be found in the ui/login and ui/product_list folders.

The Main Activity now uses ComponentActivity and uses a AppNav Host found in Navigation.kt

My ```ProductListWorker``` can be found in data/worker/ and is used in my ProductRepository class to schedule and execute the job of getting all the products from the API in the background, every hour

The app runs from the Main Activity using the normal play button android studio flow