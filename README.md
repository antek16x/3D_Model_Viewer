# 3D_Model_Viewer

The purpose of this application for Android devices is to use AR Scene Viewer to view 3D objects that are loaded into the application from the SQLite database.

<img align="center" src="https://github.com/antek16x/3D_Model_Viewer/blob/master/3D_Model_Viewer_1.png" width="220"> <img align="center" src="https://github.com/antek16x/3D_Model_Viewer/blob/master/3D_Model_Viewer_2.png" width="220"> <img align="center" src="https://github.com/antek16x/3D_Model_Viewer/blob/master/3D_Model_Viewer_3.png" width="220">

_____

<img align="right" src="https://github.com/antek16x/3D_Model_Viewer/blob/master/Demo.gif" width="220">

## Usage:

The application is quite easy to use: on the first page we see a menu in which we have a choice: loading models or exiting the application. After selecting the loading option, we will be transferred to the next activity where we will see a `ListView` which contains a list of models and their thumbnails (generated from a bait array), which are loaded from the SQLite database called `3DModels`. This database is in the `assets` folder. The [DatabaseHelper](https://github.com/antek16x/3D_Model_Viewer/blob/master/app/src/main/java/com/example/a3dmodelviewer/DatabaseHelper.java) class is responsible for the operation on the database.

After selecting and clicking on the model selected from the list, we will be redirected to SceneViewer, to which the URL of the selected model will be sent from the database.

## Setup:

### Downloading to Android Studio

You can fork this project on GitHub, download it, and then open it as a project in Android Studio. Once you have done so, it can be run on your Android device.

### Database

The structure of the database I created looks as follows:

<img align="center" src="https://github.com/antek16x/3D_Model_Viewer/blob/master/3D_Model_Viewer_Database_1.png" width="220">

