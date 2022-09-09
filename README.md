# MultiVideoChatWindowUI
在多人音视频通话项目的背景下。我们需要会遇到房间人数增加或者减少的过程。在这个过程中通话窗口动态变化的过程可以使用此项目的MultiVideoChatLayout。

## [视频效果](https://user-images.githubusercontent.com/26237728/189320010-aed3ac97-827a-43a1-8ec8-1f7a99ce4e22.mp4)

### Usage
----

#### Gradle

```groovy
allprojects {
   repositories {
   maven { url 'https://jitpack.io' }
   }
}

dependencies {
   implementation 'com.github.yyds-zy:MultiVideoChatWindowUI:v1.0'
}
```

#### Maven 

```xml

<repositories>
    <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
    </repository>
</repositories>
  
<dependency>
      <groupId>com.github.yyds-zy</groupId>
      <artifactId>MultiVideoChatWindowUI</artifactId>
      <version>v1.0</version>
</dependency>

```

Use it in your own code:

```java
<com.yyds.multichatlayout.MultiVideoChatLayout
        android:id="@+id/mcl_layout"
        android:animateLayoutChanges="true"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```	

