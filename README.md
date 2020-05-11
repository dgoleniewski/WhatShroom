# WhatShroom
> Android machine learning application, based on Tensorflow to recognize selected species of Polish mushrooms.

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info
WhatShroom is a student project for Android. It has machine learning back-end based on Python's Tensorflow. For creating our model
we gathered almost 5000 picures of 12 selected species of Polish mushrooms. The model is classic Convolutional Neural Network for
classyfing images. The model has been serialized with Tensorflow Lite and saved to a file. Then we read the model file with Tensorflow
Lite interpreter in our Android application. Whole process of creating the model You can find in /machine_learning folder (without
gathered images due to willingness to avoid licensing problems). The app uses rear camera to take a picture and then prepare it as
a valid input data for our model and classify a picture to recognized mushroom specie with given probability. 

## Screenshots

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

## Features
* Recognizing and classifying 12 species of mushrooms based picture with effectiveness at 68-75%
* Taking a picture with Android application classifying it with the model
* Marking a finding on the map to save it for later

## Status
Project has its main functionalities, but it's still in progress, especially front-end needs to be better.

## Contact
Creators:
* [Damian Goleniewski](https://github.com/dgoleniewski) - Android application
* [Blazej Tempski](https://github.com/jaheyy) - Machine learning and its implementation on Android
* [Arkadiusz Wawrzyniak](https://github.com/ArekadiuszBy) - Concept and representation
