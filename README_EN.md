# CrashCanary

A uncaught exception handler library 

## Why you need this library?

When app is running, a situation that some exception have not catch by code may happen, if so, your app will crash. In develop evviroment, it will be good if we can show crash log in a good user interface to developer or tester. In productive enviroment, it will be good to save crash log in sd card, then you can upload these file to server.

## Principle of this library

If a class implements `Thread.UncaughtExceptionHandler`, then when some exception happen, this class can cautch these exception.

## Implementation

Define a class implements `Thread.UncaughtExceptionHandler`

```java
public class CrashCanary implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable ex) {
    }
}
```

In this function, you can do any thing you want, sunch as:

* get crash log
* save crash log to file
* show crash log to screen
* exit app that has crashed

## Thanks to

* [CrashWoodpecker](https://github.com/drakeet/CrashWoodpecker)

