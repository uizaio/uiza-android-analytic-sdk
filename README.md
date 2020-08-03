
[![](https://jitpack.io/v/uizaio/uiza-android-api-sdk.svg)](https://jitpack.io/#uizaio/uiza-android-api-sdk)

## Welcome to UIZA Android API SDK
Read [CHANGELOG here](https://github.com/uizaio/uiza-android-api-sdk/blob/master/CHANGELOG.md).

## Importing the Library
**Step 1. Add the `JitPack` repository to your `build.gradle` file**

```xml
    allprojects {
          repositories {
             maven { url 'https://jitpack.io' }
          }
    }
```

**Step 2. Add the dependency**

```xml
    implementation 'com.github.uizaio:uiza-android-api-sdk:1.x.x'
```

## Init SDK


1. Init `UZApi`

     ```java
     public class App extends MultiDexApplication {
            @Override
            public void onCreate() {
                super.onCreate();
                UZApi.init(this, <SdkVersion>, UZEnvironment.DEVELOPMENT);
            }
     }
2. Call api

```java

    UZApi.getLiveViewers(<app_id>, <entity_id>, liveCounter -> {
    		Timber.e("Views: %d", liveCounter.getViews());
    	}, Timber:e);

```

or

```java

    UZApi.getLiveViewers(<link_play>, liveCounter -> {
    		Timber.e("Views: %d", liveCounter.getViews());
    	}, Timber:e);

```
