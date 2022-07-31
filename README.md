# NewYork Times News

## About

It loads NyTimes all section news list 

##HLD: 
This has been implemented based on Clean architecture with MVVM (A SOLID combination). 
Entity: Having enterprise business rules, which contains with some data classes for holding server content
Data layer: Here in this layer consist of repository classes, which implemented in abstract way
Domain layer, it consists of Use cases, Taken care of flow of data from and to entities 
Controller/Presenter: Taking up the data and converting to convenient wa for UI 
Presentation Layer, it has UI classes and view model. Take care of showing the content to user

## LLD

- Retrofit is a type safe http client used to getting data from server, it calling to server by
  using java interface with many request methods and request params.

- Okhttp used for network call.
- Coroutine is another library added in this to get asynchronous call. This is help to perform
  network call in both manner of synchronous and asynchronous call.

## Platform

Android

## Language

Kotlin

## Other Tech Stack

Hilt Coroutine Glide AndroidX NavigatorController Retrofit OkHttp

## Test libs

Espresso JUnit Mockito

- Display the all news during launching the app , by default taking news from all section
- Display the thumbnail with curbed radius and Brief of news heading and snippet
- Loads details of news in a [WebView](https://developer.android.com/guide/webapps/webview)
- Added Unit test, Integration test & UI test

## Pre-requisites

Just clone the repository and build. Make sure your system has active internet connection to
download all the dependencies

All section news API
from [nytimes developer](https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=5763846de30d489aa867f0711e2b031c)
have been used.
