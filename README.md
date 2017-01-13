# FindView
Auto create view instance in activity and fragment, with the annotation @FindView.

## ViewNamingRule
First of all, you must comply with the ViewNamingRule.

### layout
The layout file name of XxxActivity must be activity_xxx.xml.
Likewise, XxxXxxxActivity -> activity_xxx_xxxx.xml, XxxFragment -> fragment_xxx.xml.

### view
View ids in layout must comply with the ViewNamingRule equally.

#### abbrev
You need to create a xml file(view_naming_rule.xml) in directory assets.
Some content of this file:
```xml
  <containers>
		<container name="Activity" />
		<container name="Fragment" />
	</containers>
	<views>
		<view name="TextView" abbrev="tv" />
		<view name="Button" abbrev="btn" />
		<view name="EditText" abbrev="edt" />
  </views>
```

Well, view id in layout must be named in format abbrev_pagename_logicname.

#### e.g.
There is a Button(text:'login') in LoginActivity, its id in layout should be 'btn_login_login', and its variable name should be loginButton.
A TextView(used for showing username) in MainActivity, its id in layout should be 'tv_main_username', and its variable name should be usernameTextView.
More examples in the demo.

## @FindView
The annotaion that annotates view fields in an activity or a fragment. 

### e.g.
```java
public class MainActivity extends BaseActivity {  
    @FindView  
    private TextView textView;  
}  
```
With the annotation @FindView, you don't need to use `findViewById` at all.

### Note
To use @FindView correctly, you should use the annotation @SetLayout first.

## @SetLayout
The annotaion that annotates activities and fragments.

### e.g.
```java
@SetLayout
public abstract class BaseActivity extends AppCompatActivity {
}
```
With the annotaton @SetLayout, you don't need to use `setContentView` at all.
And, if you annotate @SetLayout to an activity(e.g. BaseActivity), all the activity that extends BaseActivity would be annotated by @SetLayout automatically.
Also, if you don't want some activities to be annotated automatically, you can do it this way:
```java
@SetLayout(false)
public class SomeActivity extends BaseActivity {
}
```

## Initialization
You should initialize ViewNamingRule in method `onCreate()` in your Application(extends android.app.Application).
### e.g.
```java
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ViewNamingRuleInit.getInstance().loadViewNamingRule(this);
    }
}
```

<br/>
<br/>
<br/>
Email: naveryyy@vip.com
