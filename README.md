
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

1. In `AndroidManifest.xml`

```xml
<meta-data android:name="uz_live_views_url" android:value="@string/uz_live_views_url" />
```

2. Init `UZApi`

     ```java
     public class App extends MultiDexApplication {
            @Override
            public void onCreate() {
                super.onCreate();
                UZApi.init(this, <AppName>, <AppVersion>);
            }
     }
3. Call api

```java

    UZApi.getLiveViewers(<app_id>, <entity_id>, liveCounter -> {
    		Timber.e("Views: %d", liveCounter.getViews());
    	}, Timber:e);

```

