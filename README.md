# InfoTextView

 ![](https://i.imgur.com/96xYaSe.png)

Android library that provides a better looking TextView to display details in a condensed way with an icon.

## Add yo your project

1. Add JitPack repo to your `build.gradle`: 

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2. Add the dependency

```groovy
dependencies {
    implementation 'com.github.ropjov:InfoTextView:0.1'
}
```

## How to use it?

This view has two modes: with title and without title (only one line)

### With title

1. Replace the TextView tag from your .xml with InfoTextView:

```xml
<TextView
  ...
/>
```

for

```xml
<com.ropjov.infotextview.InfoTextView
  app:icon="..."
  app:text="..."
  app:title="..."
  ...
/>
```

2. Replace your `TextView` Java/Kotlin objects with `InfoTextView` objects:

```kotlin
private lateinit var text: TextView
```

```kotlin
private lateinit var text: InfoTextView
```

### Without title

The same as the previous example, but delete the `app:title` attribute

_For a complete example check the app module of this project_


## Result

This is how the component will look in your project
 
### With title
 
 ![](https://i.imgur.com/pTvf3us.png)

### Without title

![](https://i.imgur.com/JUIT6Kp.png)

