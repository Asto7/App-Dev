

**RVS SATYANAND**

**CSE-A**

**106118083**

**LAB-1**

\*The first one is for an Addition App, The Second One is the Basic Calculator.

Add two numbers App is on page -> 1.

Basic Calculator is on page-> 9.

Add Two Numbers Apk:

<https://drive.google.com/file/d/1zenIQm9zcsK7Yejc95LVnf5xfzK02Z5C/view?usp=sharing>

Basic Calculator:

<https://drive.google.com/file/d/1IVzGzA2hM41nq9XjzDFRz5Hb8zzKdMjK/view?usp=sharing>

**Mobile Application Development Laboratory – 1, App - 1**

**Experiment Name:** Addition of Numbers

**Date:** 08-02-2021

**Aim:** The Aim is to add the two Decimal numbers given by the user.

**Description of App:**

The app contains a simplistic layout of a TextView for the result, two edit text for input, and a

button to start computing the result. The xml file has the parent layout as Linear Layout( In

vertical orientation).

For coding, I have used the Java language. The code contains a function onCreate and a helper

function **submitAdd** to add the input.

Also, the onClickListener to the button is attached in **submitAdd**. Also, **I am using try-catch to**

**catch the NumberFormatException for the case when input is empty (to prevent crash)**.

Lastly in the helper function, the input is extracted and parsed into Double and Added. After

adding the result is shown.

**Device Specifications:**





Both the app run on min SDK version of 16 (so anything above API 16 - Android 4.1 - Jelly Bean

would run this app which is 99.8% of devices). Currently, I have run it on Pixel API 30 for

outputs. Only default libraries were used for making any app and nothing additional.

Name: Pixel\_3

Resolution: 1080 X 2220

API: 30

Target: Android 11.0

hw.lcd.height: 2220

hw.accelerometer: yes

hw.device.manufacturer: Google

hw.lcd.width: 1080

hw.lcd.density: 440

hw.cpu.ncore: 6

hw.sensors.proximity: yes

hw.sensors.orientation: yes

hw.gpu.enabled: yes

**Technical Concepts Learnt:**

● **Ui perspective:** I learned Working with LinearLayout, ScrollView, TextView, Button,

EditText(where inputType is numberSigned|numberDecimalfor dealing with taking input

from user)

**● Appln Perspective:**

\1. I learned how we can trigger an event based on user click.

\2. I implemented onClick for Button inorder to get the sum of two numbers, apart from that i

learnt working with **try, catch block** to handle error gracefully (because the user might

not have entered the both the inputs in that situation I showed the user an error using

another TextView)

\3. Learned to work with id’s inorder to make changes to particular view in the layout.

**Source Code:**

***activity\_main.xml***

<?xml version="1.0" encoding="utf-8"?>





<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"

xmlns:app="http://schemas.android.com/apk/res-auto"

xmlns:tools="http://schemas.android.com/tools"

android:layout\_width="match\_parent"

android:layout\_height="match\_parent"

android:orientation="vertical"

android:padding="10dp"

tools:context=".MainActivity">

<LinearLayout

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:orientation="horizontal"

android:gravity="center\_horizontal"

android:textAlignment="center">

<TextView

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:text="Number-1" />

<EditText

android:id="@+id/editTextNumber"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:ems="10"

android:inputType="numberSigned|numberDecimal"

android:padding="20dp" />

</LinearLayout>

<LinearLayout

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:orientation="horizontal"

android:gravity="center\_horizontal"

android:textAlignment="center">

<TextView

android:layout\_width="wrap\_content"





android:layout\_height="wrap\_content"

android:text="Number-2" />

<EditText

android:id="@+id/editTextNumber2"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:ems="10"

android:inputType="numberSigned|numberDecimal"

android:padding="20dp" />

</LinearLayout>

<LinearLayout

android:layout\_width="match\_parent"

android:layout\_height="wrap\_content"

android:gravity="center\_horizontal"

android:orientation="vertical"

android:textAlignment="center">

<Button

android:id="@+id/button"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:onClick="submitAdd"

android:padding="10dp"

android:text="Add"

android:textSize="24sp" />

<TextView

android:id="@+id/success"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:padding="10dp"

android:text=""

android:textColor="@android:color/holo\_green\_dark"

android:textSize="30sp" />

<TextView

android:id="@+id/warn"





android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:padding="10dp"

android:text=""

android:textColor="@android:color/holo\_red\_dark"

android:textSize="30sp" />

</LinearLayout>

</LinearLayout>

**MainActivity.java**

**package com.example.lab\_1\_helloworld;**

**import androidx.appcompat.app.AppCompatActivity;**

**import android.os.Bundle;**

**import android.util.Log;**

**import android.view.View;**

**import android.widget.EditText;**

**import android.widget.TextView;**

**public class MainActivity extends AppCompatActivity {**

**@Override**

**protected void onCreate(Bundle savedInstanceState) {**

**super.onCreate(savedInstanceState);**

**setContentView(R.layout.activity\_main);**

**}**

**public void submitAdd(View view) {**

**try{**

**TextView warn = (TextView) findViewById(R.id.warn);**

**TextView success = (TextView) findViewById(R.id.success);**

**warn.setText("");**

**success.setText("");**





**EditText num1 = (EditText) findViewById(R.id.editTextNumber);**

**EditText num2 = (EditText) findViewById(R.id.editTextNumber2);**

**double a = Double.parseDouble(num1.getText().toString());**

**double b = Double.parseDouble((num2.getText().toString()));**

**Log.d("kira", String.valueOf(a) );**

**success.setText(String.valueOf(a + b));**

**}**

**catch(Exception e){**

**TextView warn = (TextView) findViewById(R.id.warn);**

**warn.setText("\*Please Enter both values to get the sum");**

**}**

**}**

**}**

**Screenshots:**





\1. **If one of the input is missing then it raises an error which is then catched by catch**

**block and based on the error it ask user to make the correct changes**:





\2. **If both the numbers are correctly given then it produces the result**:

**Outcomes:** We accomplished the task to get the sum of two numbers without any

bugs/crashes.





**Mobile Application Development Laboratory – 1, App - 2**

**Experiment Name:** Basic Calculator

**Date:** 08-02-2021

**Aim:** The Aim is to make a Basic Calculator application which can Add, Subtract, Multiply,

Divide the numbers given by the user.

**Description of App:**

There is an additional ScrollView added to the layout of the previous application, apart from that

I added RadioGroup and RadioButtons in order to select the operation to perform.

For coding, again java language. The code contains onCreate where all the widgets are

instantiated and listeners are attached. Extra helper functions like submitAdd is used for

performing the selected operation on numbers, onClick listener is added in RadioButton to listen

to changes in radiogroup and change the operation type to selected radio as well as **I showed**

**toastMessage for a small duration of time saying “Multiplication operation is selected” or**

**add, etc..**

**Device Specifications:**

Both the app run on min SDK version of 16 (so anything above API 16 - Android 4.1 - Jelly Bean

would run this app which is 99.8% of devices). Currently, I have run it on Pixel API 30 for

outputs. Only default libraries were used for making any app and nothing additional.

Name: Pixel\_3

Resolution: 1080 X 2220

API: 30

Target: Android 11.0

hw.lcd.height: 2220

hw.accelerometer: yes

hw.device.manufacturer: Google

hw.lcd.width: 1080

hw.lcd.density: 440

hw.cpu.ncore: 6

hw.sensors.proximity: yes

hw.sensors.orientation: yes

hw.gpu.enabled: yes

**Technical Concepts Learnt:**

**Ui perspective:** I learned Working with LinearLayout, ScrollView, TextView, Button,





EditText(where inputType is **numberSigned|numberDecimal**for dealing with taking input from

user), RadioGroup, RadioButton, ToastMessage

**Appln Perspective:**

\1. I learned how we can trigger an event based on user click.

\2. I implemented a different onClick listener for RadioButton (onClickAdd,

onClickSub,,onClickSub,, onClickDiv) to change the operations to perform on the given

two numbers.

\3. I implemented onClick for Button inorder to get the sum of two numbers, apart from that I

learnt working with **try, catch block** to handle error gracefully and avoid app crashing

**(Because the user might not have entered the both the inputs in that situation I**

**showed the user an error using another TextView, or there might be divide by zero**

**case during division**)

**Source Code:**

***activity\_main.xml***

<?xml version="1.0" encoding="utf-8"?>

<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"

xmlns:app="http://schemas.android.com/apk/res-auto"

xmlns:tools="http://schemas.android.com/tools"

android:id="@+id/scroll"

android:layout\_width="match\_parent"

android:layout\_height="match\_parent">

<LinearLayout

android:layout\_width="match\_parent"

android:layout\_height="match\_parent"

android:orientation="vertical"

android:padding="20dp"

tools:context=".MainActivity">

<LinearLayout

android:layout\_width="match\_parent"

android:layout\_height="wrap\_content"





android:gravity="center\_horizontal"

android:orientation="horizontal"

android:textAlignment="center">

<TextView

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:text="Number-1: " />

<EditText

android:id="@+id/editTextNumber"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:ems="10"

android:gravity="center\_horizontal"

android:inputType="numberSigned|numberDecimal"

android:textAlignment="center" />

</LinearLayout>

<LinearLayout

android:layout\_width="match\_parent"

android:layout\_height="wrap\_content"

android:gravity="center\_horizontal"

android:orientation="horizontal"

android:textAlignment="center">

<TextView

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:text="Number-2: " />

<EditText

android:id="@+id/editTextNumber2"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:ems="10"

android:gravity="center\_horizontal"

android:inputType="numberSigned|numberDecimal"

android:textAlignment="center" />





</LinearLayout>

<RadioGroup

android:id="@+id/radioGroup"

android:layout\_width="match\_parent"

android:layout\_height="match\_parent"

android:checkedButton="@id/add"

android:gravity="center\_horizontal"

android:inputType="textMultiLine"

android:isScrollContainer="false"

android:maxLines="2"

android:overScrollMode="ifContentScrolls"

android:textAlignment="center">

<RadioButton

android:id="@+id/mul"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:onClick="onClickMul"

android:text="Multiply" />

<RadioButton

android:id="@+id/add"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:onClick="onClickAdd"

android:text="Addition" />

<RadioButton

android:id="@+id/sub"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:onClick="onClickSub"

android:text="Subtraction" />

<RadioButton

android:id="@+id/div"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"





android:onClick="onClickDiv"

android:text="Division" />

</RadioGroup>

<LinearLayout

android:layout\_width="match\_parent"

android:layout\_height="wrap\_content"

android:gravity="center\_horizontal"

android:orientation="vertical"

android:textAlignment="center">

<Button

android:id="@+id/button"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:onClick="submitAdd"

android:padding="10dp"

android:text="Process"

android:textSize="18sp" />

<TextView

android:id="@+id/success"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:padding="10dp"

android:text=""

android:textColor="@android:color/holo\_green\_dark"

android:textSize="18sp" />

<TextView

android:id="@+id/warn"

android:layout\_width="wrap\_content"

android:layout\_height="wrap\_content"

android:padding="10dp"

android:text=""

android:textColor="@android:color/holo\_red\_dark"

android:textSize="18sp" />

</LinearLayout>





</LinearLayout>

</ScrollView>

**MainActivity.java**

package com.example.lab1\_part2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.widget.EditText;

import android.widget.RadioButton;

import android.widget.TextView;

import android.widget.Toast;

import com.example.lab1\_part2.R;

public class MainActivity extends AppCompatActivity {

String Selected = "add";

@Override

protected void onCreate(Bundle savedInstanceState) {

super.onCreate(savedInstanceState);

setContentView(R.layout.activity\_main);

}

@Override

protected void onStart() {

super.onStart();

RadioButton add = (RadioButton) findViewById(R.id.add);

RadioButton mul = (RadioButton) findViewById(R.id.mul);

RadioButton div = (RadioButton) findViewById(R.id.div);

RadioButton sub = (RadioButton) findViewById(R.id.sub);

if (add.isChecked()) {





Selected = "add";

} else if (mul.isChecked()) {

Selected = "mul";

} else if (div.isChecked()) {

Selected = "div";

} else if (sub.isChecked()) {

Selected = "sub";

}

}

public void submitAdd(View view) {

try {

TextView warn = (TextView) findViewById(R.id.warn);

TextView success = (TextView) findViewById(R.id.success);

warn.setText("");

success.setText("");

EditText num1 = (EditText) findViewById(R.id.editTextNumber);

EditText num2 = (EditText) findViewById(R.id.editTextNumber2);

double a = Double.parseDouble(num1.getText().toString());

double b = Double.parseDouble((num2.getText().toString()));

if (Selected == "add") {

success.setText(String.valueOf(a + b));

} else if (Selected == "sub") {

success.setText(String.valueOf(a - b));

} else if (Selected == "div") {

if (b <= 0.0) {

warn.setText(" In Division Second number should be

non-zero!");

} else success.setText(String.valueOf(a / b));

} else if (Selected == "mul") {

success.setText(String.valueOf(a \* b));

}

} catch (Exception e) {





TextView warn = (TextView) findViewById(R.id.warn);

warn.setText("\*Please Enter both values to get the sum");

}

}

public void onClickAdd(View view) {

Selected = "add";

Toast.makeText(this, "Addtion operation is selected!",

Toast.LENGTH\_SHORT).show();

}

public void onClickSub(View view) {

Selected = "sub";

Toast.makeText(this, "Subtraction operation is selected!",

Toast.LENGTH\_SHORT).show();

}

public void onClickDiv(View view) {

Selected = "div";

Toast.makeText(this, "Division operation is selected!",

Toast.LENGTH\_SHORT).show();

}

public void onClickMul(View view) {

Selected = "mul";

Toast.makeText(this, "Multiplication operation is selected!",

Toast.LENGTH\_SHORT).show();

}

}

**Screenshots:**

\2. **If one of the input is missing then it raises an error which is then catched by catch**

**block and based on the error it ask user to make the correct changes**:









\3. Showing Divide by zero error to user to avoid crash.

\4.

**d**





**5. Showing toast message when user changes radio button**





\6. **If both the numbers are correctly given then it produces the result**:





\7.

















**Outcomes:** We accomplished the task to make a Basic calculator application without any

bugs/crashes.

