# What is this project?

In this project you can find code challenges from Leetcode solved by me.

Implementation of benchmarking tools to compare different implementations for a given problem.

Implementation of some tests for Leetcode problems.

Individual implementation of some algorithms.

Implementation of some popular data structures.

Implementation of simple tests for those data structures.

Implementation of util classes which helps setting up Leetcode exercises easily.



# Disclaimer

The chances are I'm gonna share this repository with recruiters. However this project was not created with the intention to showcase, but rather to learn. This means that some practices used might be controversial. Like keeping different implementations for the same problem. This allows me to benchmark two solution quickly. And see how they behave from a time complexity point of view, side by side. 

Copy and pasting solutions is not encoraged and was not practiced, so all the code you find is original code, except the section specially created for other people's code, which is clearly identified.



# On Going Project

This is an on going project, with emphasis on problem solving. Which means that all the other parallel developments became subsidiary of this main goal. At the time of writing (July 2020) I've many packages that requires improvements. Hopefully I'll get there sooner than later.

As long as I keep track of the places that I need to refactor, fix bugs, develop from scratch or just delete, part of the job is already done.



## **Data structutes**

Some data structures have bugs, namely Hashmap, the rehashing is not working as expected in all the situations. Naming of the data structures are also poor, need some refactoring. In some cases I have more than one data structure implementation, I should keep just the best. Some data structures implemented do not behave as they should because I misunderstood them, example is LRUCache. However the correct implementation can be found in problem solving section. 

More testes are required in order this data structures can be entrusted.



## **Algorithms**

I need to clean and refactor this packages classes too	. I've implemented much more algorithms than those there, many of them are embedded in problem solving, others in the implementation of data structures, I might extract them from those places or reimplement them again.

More tests are required for the algorithms.



## **Performance/Benchmarking**

Benchmarking class is not completed, it supports functions where the input is an array of integers. But It is supposed to support other types of signatures in order to benchmark a broader set of problems.



## **Utils**

One of the most important classes in this package is LeetcodeUtils. This class allows me to copy and paste Leetcode inputs and deserialize them for the correct object, this is a great help if I need to debug my code for whatever reason and a massive produtivity tool. At the moment there are two deserializers. Int Array, which supports from 1D to 2D arrays. And Tree deserializer that works perfectly fine.



```java

//Deserialize String to 1D int array
int [] nums = stringToArray("[100, 4, 200, 1, 3, 2]");

//Deserialize String to 2D int array
int [][] matrix = stringToMatrix("[[7,24],[29,33],[45,57],[66,69],[94,99]]");

//Deserialize String to Tree
TreeNode n = stringToTreeNode("[5,2,3,4,5,null,null,null,null,6,3,null,null,8]");

```



Besides the will to improve the deserializer for int arrays to support NDimention array, there is the desire to create a String array deserializer. And later a char array deserializer. I eventually can do the 3 in one method.



## **Comments**

Not all the questions comments share the same layout. You will find a tiny portion of problems where the comments section has a different layout, probably less attributes, or less separators. This happens because I have been improving the comments layout and attributes along the way, and each time a new element is added I don't go back and update 160 files for the sake of consistency, it's very time consuming. Not very productive, and as stated earlier it is not the "core business". 

For this reason comment sections are updated from time to time. Just Like the documentation, the focus will always be to solve problems.



## **Package names**

The package names needs refactoring



# Problems Package

In this package you will find the majority of Leetcode problems that I've solved. All problems where solved independently, no copy pasting. I consulted other people's solutions only after developing my own. That way I was able to confirm that I was learning and not just memorizing solutions which can happen when you jump directly to the solutions without coding a single line. The defining factor to solve this kind of problems is to know how to think. From there all the technical knowledge is a plus. It will help you do the job quicker, cleaner and more efficiently.



## Problem Class

This is the class where I solve the problems.

### Naming

Each problem class has the name of the problem itself, forbiden characters are removed or replaced with underscore.

- ProblemName.java

When I do the same problem in different days I separate the class in takes. If I've done it in 3 different days you will find.

- ProblemName_Take1.java
- ProblemName_Take2.java
- ProblemName_Take3.java



### Comments

Comments are very important and I used them to describe How I solved the problems and relevant metadata. There are mainly two types of comments. Header and Solutions Comments which you can find described below.



#### Header

At the top of the class you will find the link of the problem, and the difficulty of the problem.



#### Solution Comments

This is the comments that you find at the top of the solution. Here you can find the following tags.



*@intuition*

- This is the gist behind my solution brefly explained

*@score*

- This is my solution's score compared with other submissions. Note that over time Leetcode performance tend to worsen because of system load. Solutions that in the past were 2ms, might take 4ms in the future. This score is more like a guide but its precision is dubious because it also depends on the current load.

*@time*

- Time complexity estimation

*@space*

- Space complexity estimations

*@fail*

- The reasons why I didn't get my solution pass in the first attempt

*@optimizations*

- Possible optimizations I could have performed, or performed

*@alternatives*

- Alternative solutions that I believe that would also work



### Others Solutions

Is a sections that indicates the end of my solutions and the start of other solutions created by other people. It can be official Leetcode solutions or it can be an unofficial solution. This is used mostly to compare with my solution and ocasionally benchmark with mine's.



## Problem Tests

In many situations I have created test cases in the main method instead of a separate test class. It is much faster and as Leetcode has its own test system, creating separated tests is indeed a redundant and unrewarding work. It results in better code display for sure but leetcode itself has a testcase console, so for the sake of produtivity I'll to keep myself away of creating segregated classes (despite initial effort) and test directly in the main method.