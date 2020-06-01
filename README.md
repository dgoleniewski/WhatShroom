# WhatShroom
> Android machine learning application, based on Tensorflow to recognize selected species of Polish mushrooms.

## Table of contents
* [General info](#general-info)
* [Authors](#authors)
* [License](#license)
* [Software requirements specification](#software-requirements-specification)
* [Technologies](#technologies)
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
* **N7** Application should work on Android API 21 or newer
* **N8** Bottom Navbar in app


## Technologies
You can find our techstack [here](https://stackshare.io/dgoleniewski/whatshroom)

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
