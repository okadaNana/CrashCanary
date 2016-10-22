[English](./README_EN.md)

# CrashCanary

一个错误日志捕获工具

## 为什么需要这么一个工具

在App运行的过程中，如果遇到异常且没有捕或改异常的情况，App就会崩溃。如果在开发环境遇到这种情况，需要把异常以一种友好界面志展示出来，这样测试人员和开发人员才好着手处理问题。如果在生产环境中遇到这种情况，就需要把异常记录到文件中去，并把文件上报到服务器。

那么，要怎么实现这样一套机制呢？又需要用到什么原理？

## 原理

如果线程因为一个无法捕获的异常而终止，可以使用一个实现了接口 `Thread.UncaughtExceptionHandler` 的类来进行着手处理。

在异常发生的时候，异常的有关信息会作为参数传入 `Thread.UncaughtExceptionHandler`  的 `public void uncaughtException(Thread t, Throwable ex)`  方法中去。我们只需要在这个方法中捕获异常日志，把日志保存到文件或者显示到界面上。

## 实现

新建一个类 `CrashCanary`，实现 `Thread.UncaughtExceptionHandler` 接口的方法

```java
public class CrashCanary implements Thread.UncaughtExceptionHandler {
	@Override
    public void uncaughtException(Thread t, Throwable ex) {
    }
}
```

在这三个方法中，要做的事情有

* 获取异常日志
* 存储异常日志到文件中去
* 显示异常日志到屏幕上
* 退出崩溃的App

具体的看代码就好了

## 感谢

* [CrashWoodpecker](https://github.com/drakeet/CrashWoodpecker)
