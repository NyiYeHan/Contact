# clean_code

## Table of Contents

1. [Introduction](#introduction)
2. [Variable Naming](#variables)
3. [Functions](#functions)
4. [Error Handling](#error-handling)
5. [Comments](#comments)
6. [Objects and Data Structures](#objects-and-data-structures)

    
## Introduction

![Keep Your Code Clean](https://cdn-images-1.medium.com/max/1600/1*i0r5PedoJQOdLM-Slkwxfg.png)

**"Any fool can write code that a computer can understand. Good programmers write code that humans can understand."- Martin Fowler**

There are countless examples of bad codes bringing progress down or making a disaster of good product. The quality of code is directly correlated to the maintainability of the product.

As features are added and changes are made, time passes and the original developers move on or forget some of the project details, if the quality of the code is not good, changes become increasingly risky and more complex. Programmers are really authors, and your target audience is not the computer it is other programmers. The ratio of time spent by a programmer reading code to writing code is normally 10:1. You are constantly reading old code in order to write new code. Writing clean code makes the code easier to understand going forward and is essential for creating successful maintainable product.


## **Variables**
### Use meaningful and pronounceable variable names

**Bad:**
```java
String obj = "redirect:/app/HospitalAdmission/update";
```

**Prefer:**
```java
String hospitalAdmissionRedirectURL = "redirect:/app/HospitalAdmission/update";
```
**[⬆ back to top](#table-of-contents)**

### Use the same vocabulary for the same type of variable

**Bad:**
```java
getRiskFactorById();
getById();
```

**Prefer:**
```java
getRiskFactorById();
```
**[⬆ back to top](#table-of-contents)**

### Class Names

Classes and objects should have noun or noun phrase. A class should not be a verb

**Bad:**
```java
public class Opsurgery{
//
}
```

**Prefer:**
```java
public class Operationsurgery{
//
}
```
**[⬆ back to top](#table-of-contents)**


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
```java
public ModelAndView showForm(@RequestParam(required = false) Integer page,@RequestParam(required = false) String firstname,
@RequestParam(required = false) String familyname,@RequestParam(required = false) String medicareNo,
@RequestParam(required = false) String dateofBirth,@RequestParam(required = false) String townId,ModelMap model){
}
```

**Prefer:**
```java
public ModelAndView showPatientList(@ModelAttribute(value = "patientlistinfo") PatientListInfo patientListInfo, ModelMap model){
}
PatientListInfo({
	page : 1,
	firstname : 'Kyaw Kyaw',
	familyname: '',
	medicareNo: '8976',
	dateofBirth: '1993-12-26',
	townId: 18,
});
```
**[⬆ back to top](#table-of-contents)**

### Function should be small

The first rule of functions is that they should be small. The second rule of functions is that 
they should be smaller than that. Every function in the programwas just two, or three, or four 
lines long.

**Blocks and Indenting**

This implies that the blocks within `if` statements, `else` statements, `while` statements, and 
so on should be one line long. Probably that line should be a function call.

**Bad:**
```java
public ModelAndView mhedit(@RequestParam int id){
	List<Operations> operation = oService.getListById(id);
	if(operation.size() > 1){	
		for(int i=0;i<operation.size();i++){
			if(i == operation.size() - 1){
				op = operation.get(i);
				pod2 = podService.getByOpId(op.getId());
			}
		}
	}else if(operation.size() == 1){
		op = operation.get(0);
		pod2 = podService.getByOpId(op.getId());
	}
}
```

**Good:**
```java
public ModelAndView postOperativeOrOperation(@RequestParam int id){
	List<Operations> operation = operationService.getListById(id);
	if(operation.size() > 1){	
		postOperativeAndOperation = operationListGreaterthanOne(operation);
	}else if(operation.size() == 1){
		postOperativeAndOperation = operationListEqualOne(operation);
	}
}
```
**[⬆ back to top](#table-of-contents)**

### Do One Thing

**FUNCTIONS SHOULD DO ONE THING. THEY SHOULD DO IT WELL. THEY SHOULD DO IT ONLY.**
If a function does only those steps that are one level below the stated name of the function, then
the function is doing one thing. After all, the reason we write functions is to decompose a larger
concept into a set of steps at the next level of abstraction.

**Bad:**
```java
public String redirectOperation(HttpServletRequest request){
	Operationsurgery opsurgeryfirst = new Operationsurgery();
	OperativeStatus operativeStatus = (OperativeStatus) request.getSession().getAttribute("operativeStatus");
	List<Operationsurgery> os = operationsurgeryService.getByOpid(operativeStatus.getOpid());
						
	if(os.size() < 1){
		priority ++;
		opsurgeryelse.setPriority(priority);
		opsurgeryelse.setOpid(operativeStatus.getOpid());
		opsurgeryelse.setSurgeryname(surgeryList[i]);
		operationsurgeryService.saveopsurgery(opsurgeryelse);
		if(opsurgeryelse.getPriority() == 1){
			Operationsurgery sg = new Operationsurgery();
			sg.setOpid(opsurgeryelse.getOpid());
			sg.setPriority(priority);
			opsurgeryfirst = operationsurgeryService.getByBoth(sg);
		
			
		}
	}
}
```

**Prefer:**
```java
public String redirectOperation(HttpServletRequest request){
	Operationsurgery opsurgeryfirst = new Operationsurgery();
	OperativeStatus operativeStatus = (OperativeStatus) request.getSession().getAttribute("operativeStatus");
	List<Operationsurgery> operativeStatusList = operationsurgeryService.getByOpid(operativeStatus.getOpid());
						
	if(operativeStatusList.size() < 1){
		opsurgeryfirst = operationSurgerySizeLessThanOne(priority, operativeStatus, surgeryList[i]);
	}
}
```
**[⬆ back to top](#table-of-contents)**


## **Error Handling**


## **Comments**
### Comments Do Not Make Up for Bad Code

The proper use of comments is to compensate for failure in the code.

**Bad:**
```java
//Multiplication function
public int project1(int a, int b){
    return a*b;
}
```

**Good:**
```java
public int multiply(int number1, int number2){
    return number1 * number2;
}
```
**[⬆ back to top](#table-of-contents)**

### Don't leave commented out code in your codebase

Use version control instead of comment the old code.

**Bad:**
```java
operator.getName();
//operator.getActivename();
```

**Good:**
```java
operator.getName();
```
**[⬆ back to top](#table-of-contents)**

### Noise comments are intolerable

Sometimes you see comments that are nothing but noise. They restate the obvious and provide no new information or even worst.

**Bad:**
```java
/*
 * asdf
 */
public int getDayOfMonth() {
 return dayOfMonth;
}
```

**Good:**
```java
public int getDayOfMonth() {
 return dayOfMonth;
}
```
**[⬆ back to top](#table-of-contents)**

### Don't use journal comments

You can use git log to get the history. Don't write comment as history on top of the code.

**Bad:**
```java
// Edited 11/13/2018 : Change function name from project1 to multiply
// Edited 11/15/2018 : Change variable name from a1 to number1 and a2 to number2
public int multiply(int number1, int number2){
    return number1 * number2;
}
```

**Good:**
```java
public int multiply(int number1, int number2){
    return number1 * number2;
}
```
**[⬆ back to top](#table-of-contents)**

### Avoid positional markers

They usually just add noise. Let the functions and variable names along with the proper indentation and formatting give the visual structure to your code.

**Bad:**
```java
////////////////////////////////////////////////////////////////////////////////
// Region Start
////////////////////////////////////////////////////////////////////////////////
public int multiply(int number1, int number2){
    return number1 * number2;
}
////////////////////////////////////////////////////////////////////////////////
// Region End
////////////////////////////////////////////////////////////////////////////////
```

**Good:**
```java
public int multiply(int number1, int number2){
    return number1 * number2;
}
```
**[⬆ back to top](#table-of-contents)**

## **Objects and Data Structures**



