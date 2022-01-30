
## Table of Contents

1.  [Introduction](#introduction)
2.  [Naming Conversion](#naming-conventions)
3. [Variable Naming](#variables)
4. [Functions](#functions)
5. [Error Handling](#error-handling)
6. [Comments](#comments)
7. [Objects and Data Structures](#objects-and-data-structures)


## conventions
    
## Introduction

![Keep Your Code Clean](https://programming-memes.com/wp-content/uploads/2020/09/4fz8sc.jpg)

**"So if you want to go fast, if you want to get done quickly, if you want your code to be easy to write, make it easy to read."- Robert C. Martin**

There are countless examples of bad codes bringing progress down or making a disaster of good product. The quality of code is directly correlated to the maintainability of the product.

As features are added and changes are made, time passes and the original developers move on or forget some of the project details, if the quality of the code is not good, changes become increasingly risky and more complex. Programmers are really authors, and your target audience is not the computer it is other programmers. The ratio of time spent by a programmer reading code to writing code is normally 10:1. You are constantly reading old code in order to write new code. Writing clean code makes the code easier to understand going forward and is essential for creating successful maintainable product.



## **Naming Conventions**
### Packages
Package names are all `lower-case`, multiple words concatenated together, without hyphens or underscores:


**Bad:**
`com.galaxy.shop_demo`

**Prefer:**
`com.galaxy.shopdemo`

### Properties
Must be written in lowerCamelCase with descriptive names:

**Bad:**
`private var temp: Int = -1`
`private var a: Boolean = false`

**Prefer:**
`private var temporary: Int = -1`
`private var isValid: Boolean = false`

Constants must be written in uppercase:

**Bad:**
`const val extra_value = 42`

**Prefer:**
`const val EXTRA_VALUE = 42`

Acronyms should be treated as words:
**Bad:**
`XMLHTTPRequest`
`IOStream`
`URL: String`
`findPostByID`

**Prefer:**
`XmlHttpRequest`
`IoStream`
`url: String`
`findPostById`

**Example:**
`class ExampleClass {`
    `val publicFinalProperty: Int`
    `var publicProperty: Int = 0`
    `protected val protectedProperty: Int`
    `private val url: String`

    private fun findRateById(): Int {
        // Your code goes here
    }
`}`

`const val SOME_CONSTANT = 42`


**Classes and Interfaces**
`Written in UpperCamelCase.`


**Bad:**
`BaseACTIVITY`

**Prefer:**
`BaseActivity`


**Class Member Ordering**

Class members should be ordered in the following manner:
1. private val/var
2. overridden val with backing property
3. overridden method

private methods should be placed close to the call sites. For example:

class SomeClass : BaseClass() {

    private val form = BehaviorSubject.createDefault(Form())
    private var id = -1

    private val _enableButton = BehaviorSubject.createDefault(false)
    override val enableButton: Observable<Boolean>
        get() = _enableButton

    override fun firstName(firstName: String) {
        validateName(firstName)
    }

    private fun validateName(name: String) {
        // Your code
    }

    override fun someOtherMethod(something: String) {
        // Your code
    }
}




**Methods**
Written in lowerCamelCase with descriptive names.


**Bad:**
`fun setvalue(value: String)`
`private fun check()`

**Prefer:**
`fun setValue(value: String)`
`private fun checkStyle() // or private fun validateStyle()`

**Declaration**
Non-public visibility modifiers should be explicitly defined for classes, methods, and properties:

**Bad:**
`var isPrivateProperty: Boolean = false`
`public fun publicMethod() {`
    // Nope
`}`

**Prefer:**
private var isPrivateProperty: Boolean = false
`fun publicMethod() {`
    // Your code
`}`

**Classes** should be one class per source file, although inner classes are still encouraged in some situations.

**Constants** should be declared at the top-level scope and placed at the bottom of the file.

`class SomeClass {  `
`    // Your code`
`}`

`const val SOME_CONSTANT = 42  `
`private const val SOME_OTHER_CONSTANT = "thisIsAConstant"`

**Bad:**


**Prefer:**




## **Variables**
### Use meaningful and pronounceable variable names

**Bad:**
```kotlin
obj : String = "redirect:/app/HospitalAdmission/update";
```

**Prefer:**
```kotlin
String hospitalAdmissionRedirectURL = "redirect:/app/HospitalAdmission/update";
```
**[⬆ back to top](#table-of-contents)**

### Use the same vocabulary for the same type of variable

**Bad:**
```kotlin
getUserById();
getById();
```

**Prefer:**
```kotlin
getUserById();
```
**[⬆ back to top](#table-of-contents)**

### Class Names

Classes and objects should have noun or noun phrase. A class should not be a verb

**Bad:**
```Kotlin
public class Uproflie{
//
}
```

**Prefer:**
```java
public class UserProfile{
//
}
```



## **Functions**
### Function arguments (2 or fewer ideally)

The number of arguments for a function are zero(niladic), one(monadic), two(dyadic), 
three(triadic) should be avoided where possible and more than three(polyadic) 
requires very special justification(shouldn't be used anyway).

Flag arguments are ugly. In that case we should split the function into two: 
`functionForTrue()` and `functionForFalse()`

**Argument Objects**

When a function seems to need more than two or three arguments, then we wrapped some 
of those arguments into a class of their own.

**Bad:**
```Kotlin
fun register(var username : String,var password:String,var email:String
,var phoneNumber:Int )
```

**Prefer:**
```Kotlin
fun register(request : Request){
// call api service
}
data class Request(
    var username: String,
    var password: String,
    var email: String, 
    var phoneNumber: Int
)
```
**[⬆ back to top](#table-of-contents)**

### Function should be small

`Rule 1`: Functions should be small!
`Rule 2`: Functions should be smaller than that!”
`Clean Code by Robert C. Martin`

**Blocks and Indenting**

This implies that the blocks within `if` statements, `else` statements, `while` statements, and 
so on should be one line long. Probably that line should be a function call.

**Bad:**
```Kotlin
fun parseProduct(Response response) : Product{
   if (response == null){
       throw  ClientException("Response is null");
   }
   int code = response.code();
   if (code == 200 || code == 201){
       return mapToDTO(response.body());
   }
   if (code >= 400 && code <= 499){
       throw = ClientException("Sent an invalid request");
   }
   if (code >= 500 && code <= 599){
       throw ClientException("Server error");
   }
   throw  ClientException("Error. Code " + code);
}
```

**Good:**
```Kotlin
fun parseProduct(response: Response?) = when (response?.code()){
   null -> throw ClientException("Response is null")
   200, 201 -> mapToDTO(response.body())
   in 400..499 -> throw ClientException("Sent an invalid request")
   in 500..599 -> throw ClientException("Server error")
   else -> throw ClientException("Error. Code ${response.code()}")
}
```
**[⬆ TABLE OF CONTENTS]#table-of-contents)**

### Do One Thing

**FUNCTIONS SHOULD DO ONE THING. THEY SHOULD DO IT WELL. THEY SHOULD DO IT ONLY.**
If a function does only those steps that are one level below the stated name of the function, then
the function is doing one thing. After all, the reason we write functions is to decompose a larger
concept into a set of steps at the next level of abstraction.

**Bad:**
```Kotlin
fun fetchProfile() : Response{
        val response = mViewModel.fetchProfile()
        val url = response.url
        val image = "https://www.google.com/" + url
        response.image = image
        return  response
    }
```

**Prefer:**
```Kotlin
    const val BASE_IMAGE_URL = "https://www.google.com/"
    fun fetchProfile() : Response{
        return mViewModel.fetchProfile()
    }
    fun getImageUrl(url : String):String{
        return  BASE_IMAGE_URL + url
    }
```
**[⬆ back to top](#table-of-contents)**


## **Error Handling**


## **Comments**
### Comments Do Not Make Up for Bad Code

The proper use of comments is to compensate for failure in the code.

**Bad:**
```kotlin
//Multiplication function
fun project1(int a, int b) : Int{
    return a*b;
}
```

**Good:**
```kotlin
fun multiply(int number1, int number2) : Int{
    return number1 * number2;
}
```
**[⬆ back to top](#table-of-contents)**

### Don't leave commented out code in your codebase

Use version control instead of comment the old code.

**Bad:**
```kotlin
operator.getName();
//operator.getActivename();
```

**Good:**
```kotlin
operator.getName();
```
**[⬆ back to top](#table-of-contents)**

### Noise comments are intolerable

Sometimes you see comments that are nothing but noise. They restate the obvious and provide no new information or even worst.

**Bad:**
```kotlin
/*
 * asdf
 */
fun getDayOfMonth() : Int {
 return dayOfMonth;
}
```

**Good:**
```kotlin
fun getDayOfMonth() : Int{
 return dayOfMonth;
}
```
**[⬆ back to top](#table-of-contents)**

### Don't use journal comments

You can use git log to get the history. Don't write comment as history on top of the code.

**Bad:**
```kotlin
// Edited 11/13/2018 : Change function name from project1 to multiply
// Edited 11/15/2018 : Change variable name from a1 to number1 and a2 to number2
fun multiply(int number1, int number2) : Int{
    return number1 * number2;
}
```

**Good:**
```kotlin
 fun multiply(int number1, int number2) : Int{
    return number1 * number2;
}
```
**[⬆ back to top](#table-of-contents)**

### Avoid positional markers

They usually just add noise. Let the functions and variable names along with the proper indentation and formatting give the visual structure to your code.

**Bad:**
```kotlin
////////////////////////////////////////////////////////////////////////////////
// Region Start
////////////////////////////////////////////////////////////////////////////////
fun multiply(int number1, int number2) : Int{
    return number1 * number2;
}
////////////////////////////////////////////////////////////////////////////////
// Region End
////////////////////////////////////////////////////////////////////////////////
```

**Good:**
```kotlin
 fun multiply : Int(int number1, int number2){
    return number1 * number2;
}
```
**[⬆ back to top](#table-of-contents)**

## **Objects and Data Structures**



