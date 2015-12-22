# ViewWrapperEditText
A View wrapper for EditTexts in Android. Add Views inside/outside EditTexts using this library

![alt text](https://raw.githubusercontent.com/aditya2705/ViewWrapperEditText/master/art/demo.gif)

##Setup
```groovy
repositories {
    maven {
        url  "http://dl.bintray.com/aditya2705/maven"
    }
}
dependencies {
    compile 'com.adityarathi.viewwrapperedittext:view-wrapper-edittext:1.0'
}
```

###Usage
####Method 1: Auto generate Edit Texts
You may add Views withing EditTexts using **addViewWithinText(View v)**

Similarly delete Views using **deleteViewWithinText(View v)**

In sample case, **View v** taken is a ImageView
```java
imageTextWrapperLayout = (ViewTextWrapperLayout) findViewById(R.id.imageTextWrapperLayout);
Button button = (Button)findViewById(R.id.add_image);
button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.drawable.sample);
                imageView.setAdjustViewBounds(true);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageTextWrapperLayout.deleteViewWithinText(v);
                    }
                });
                imageTextWrapperLayout.addViewWithinText(imageView);
            }
});
        
```
####Method 2: Add self generating Edit Texts
You may now add Views with your own customized EditTexts using **addViewWithinText(View v, CustomEditText editText)**

The class **CustomEditText** extends the **EditText** class.

You can do this --

a. Either programmatically:
```java
CustomEditText customEditText = new CustomEditText(MainActivity.this);
customEditText.setTextColor(//set Color);
customEditText.setText(//set Text);
.........
........
.....
imageTextWrapperLayout.addViewWithinText(imageView,customEditText);
        
```
b. Or by defining it in an **XML layout** and **Inflating**
```xml
<?xml version="1.0" encoding="utf-8"?>
<com.adityarathi.viewwrapperedittext.CustomEditText 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:textColor="#000"
    android:textColorHighlight="#f0f"
/>
```
Do not use it other than as the parent root when inflating. Should not reside as a child.
```java
CustomEditText customEditText = (CustomEditText) View.inflate(MainActivity.this,R.layout.custom_edit_text,null);
imageTextWrapperLayout.addViewWithinText(imageView,customEditText);
```

c. You can also make your own **EditText** class by extending the **CustomEditText** class.


**Will be updating with newer features and functionalities soon. Please do report issues if you come across any.** 

**Cheers!**

###Developed By
Aditya Rathi

Company: [Alpha Labz](https://www.alphalabz.com)

###License

```
Copyright 2015 Aditya Rathi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
