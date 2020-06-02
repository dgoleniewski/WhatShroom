# WhatShroom
> Android machine learning application, based on Tensorflow to recognize selected species of Polish mushrooms.

## Table of contents
* [General info](#general-info)
* [Authors](#authors)
* [License](#license)
* [Software requirements specification](#software-requirements-specification)
* [Technologies](#technologies)
* [Architecture](#architecture)
* [Setup](#setup)
* [Testing](#testing)
* [Status](#status)
* [Screenshots](#screenshots)

## General info
WhatShroom is a student project for Android. It has machine learning back-end based on Python's Tensorflow. For creating our model
we gathered almost 5000 picures of 12 selected species of Polish mushrooms. The model is classic Convolutional Neural Network for
classyfing images. The model has been serialized with Tensorflow Lite and saved to a file. Then we read the model file with Tensorflow
Lite interpreter in our Android application. Whole process of creating the model You can find in /machine_learning folder (without
gathered images due to willingness to avoid licensing problems). The app uses rear camera to take a picture and then prepare it as
a valid input data for our model and classify a picture to recognized mushroom specie with given probability. 

## Authors
* [Damian Goleniewski](https://github.com/dgoleniewski) - Android application
* [Blazej Tempski](https://github.com/jaheyy) - Machine learning and its implementation on Android
* [Arkadiusz Wawrzyniak](https://github.com/ArekadiuszBy) - Concept and representation

## License
Copyright (c) 2020 Damian Goleniewski, Błażej Tempski, Arkadiusz Wawrzyniak  
This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.

## Software requirements specification
Functional
* **F1** Creation of convolitonal neural network, colorful small size image (as array of floats) as input and a mushroom specie (integer) and probability (float) as output
* **F2** Deploying a model as .tflite file on Android application and interpreting it via Tensorflow Lite Interpreter
* **F3** Android application based on Fragments, not Activities
* **F4** Taking a picture
* **F5** Delivering taken picture (as array of floats) and receiving an output
* **F6** Displaying result to end user (mushroom specie and probability of prediction)
* **F7** Saving markers on map in application, with short title, description and date. The data should be saved in application shared preferences and readed automatically on application start.

Nonfunctional
* **N1** Good readability
* **N2** Application in forest colors - is to be associated with nature 
* **N3** Model effectiveness over 70% on validation data.
* **N4** Ease of use
* **N5** Documentation kept to a minimum
* **N6** Main functionality - mushroom recognition without internet access
* **N7** Application should work on Android API 23 or newer
* **N8** Bottom Navbar in app


## Technologies
You can find our techstack [here](https://stackshare.io/dgoleniewski/whatshroom)

## Architecture
### System
The system consists of mobile application on Android platform and machine learning model trained with Tensorflow. They communicate with each other via Tensorflow Lite interpreter situated in Android application. The interpreter can read a serialized version of pre-trained model, which is now saved in .tflide format.
### Application module  
Application consists of 2 submodules.  
The first module allows end user to take a picture and then shows a desired output. It does so by converting an image to a bitmap and then to multidimentional array of floats. Each value in array represents a single color value (R, G or B) on a single pixel. An array is then transfered to the model, where it passes all layers and gives a desired output - an integer representing a mushroom label, and a probability of prediction. Based on the integer the mushroom name and image (static file), also the probablity of prediction are displayed to a user.  
The second module uses Google Maps for displaying a map to the user. A user has can add a markup with title, description and date to the via Floating Action Button. Saved markups can be viewed in the map and also in a RecyclerView android feature, where each item represents a markup.
### Machine Learning module  
Machine learning module includes a whole process of gathering data, creating validation sets, creating model structure, training, validating and serializing a model. The data, which are images in JPEG/JFIF format, have been download with help of a simple Python automation script from Bing Images. The script is our modification of [google-images-download] (https://github.com/hardikvasa/google-images-download) library created by [Hardik Vasa](https://github.com/hardikvasa).  
The data is randomly diveded into train set, test set and validation set. Model structure consists of an input layer, 15 hidden layers and an output layer. Hidden layers consists of 3 sets of 2D Convolution, Activation ReLU function and a 2D Max Pooling function. This is a classic convolutional neural network for image recognition. The next layers are combination of Flatten function, ReLu and Dense layers to easily reduce the number of neurons to 12 output neurons. Each of output neuron represents a different mushroom specie. The model is automatically trained with training data only, which is also automatically previously divided into multiple batches. Each batch consist of 32 images. The model is trained on each batch randomly 15 times (number of epochs).  
The model is then serialized to .tflite file. The creation of model is one time process and should be continued in the future when there will be more available data to make model better.

## Setup
To setup a project, you need to clone the repo or download its content and open up the main folder in Android Studio, version 3.6.3 or newer
with Android SDK 23 or newer installed.
If you want to open up a machine learning files, you need Python installed, Jupyter Notebook for .ipynb files and Tensorflow library.  
Installation of Jupyter and Tensorflow with pip packet manager:
```
pip install notebook
pip install tensorflow
```
To open up Jupyter Notebook change directory in terminal to the one with repository /machine_learning and type
```
jupyter notebook
```

## Testing
Test scenario [Download](https://github.com/dgoleniewski/WhatShroom/raw/master/test_scenario.xlsx)

## Status
Project has its main functionalities, but it's still in progress, especially front-end needs to be better.

## Screenshots
<img src="/screenshots/5.png" width="600"/>
<img src="/screenshots/1.png" width="600"/>
<img src="/screenshots/2.png" width="600"/>
<img src="/screenshots/3.png" width="600"/>
<img src="/screenshots/4.png" width="600"/>
