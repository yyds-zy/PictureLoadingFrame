# PictureLoadingFrame

picture loading frame

### Usage
[三级缓存](https://www.jianshu.com/p/81272eb85d84)
----

#### Gradle

```groovy
allprojects {
   repositories {
   maven { url 'https://jitpack.io' }
   }
}

dependencies {
   implementation 'com.github.yyds-zy:PictureLoadingFrame:1.0.1'
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
      <artifactId>PictureLoadingFrame</artifactId>
      <version>1.0.1</version>
</dependency>

```

Use it in your own code:

```java
ImageLoaderUtil.getInstance().with(this).disPlay(imageView,"pic_url");
```	
