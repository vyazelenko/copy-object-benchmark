copy-object-benchmark
=====================

Copy object benchmarks for Java

This project contains performance benchmarks for several different ways to perform a shallow copy of an object in Java.
Methods benchmarked are:
* Clone
* Copy constructor
* Serialization:
** Default
** Custom serialization
** Using Externalizable
* Copying via reflection:
** Calling getFields() every time
** Caching getFields() result upon first call
* Using MethodHandles:
** MethodHandle#invoke()
** MethodHandle#invokeWithArguments()
** MethodHandle#invokeExact()

Performance tests are based on the JMH framework from OpenJDK (http://openjdk.java.net/projects/code-tools/jmh).

Tests were conducted using JDK 7u40 and JDK 8 build 109. Results are available in the project as java7_results.txt and java8_results.txt respectively.

=====
Building instructions
