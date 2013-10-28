copy-object-benchmark
=====================

####Copy object in Java (performance comparison)

This project was written for blog post http://vyazelenko.com/2013/10/29/copy-object-in-java-performance-comparison/. It contains performance benchmarks for several different ways to perform a shallow copy of an object in Java.
Methods benchmarked include the following:
* Clone
* Copy constructor
* Serialization:
  + Default
  + Custom serialization
  + Using Externalizable
* Copying via reflection:
  + Calling ```getFields()``` every time
  + Caching ```getFields()``` result upon first call
* Using MethodHandles:
  + ```MethodHandle#invoke()```
  + ```MethodHandle#invokeWithArguments()```
  + ```MethodHandle#invokeExact()```

Performance tests are based on the JMH framework from OpenJDK (http://openjdk.java.net/projects/code-tools/jmh).

Original tests were conducted using JDK 7u25, 7u45 and JDK 8 build 112. 

=====
####Build instructions
* Follow build instructions on JMH web site: http://openjdk.java.net/projects/code-tools/jmh/
* Run ```mvn clean package```
* Execute tests by calling ```run.sh <JDK_home_path>```, where ```JDK_home_path``` is absolute path to JDK installation directory
