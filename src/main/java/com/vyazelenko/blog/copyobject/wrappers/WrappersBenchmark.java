package com.vyazelenko.blog.copyobject.wrappers;

import com.vyazelenko.blog.copyobject.wrappers.clone.CloneCopy;
import com.vyazelenko.blog.copyobject.wrappers.constructor.ConstructorCopy;
import com.vyazelenko.blog.copyobject.wrappers.mh.MethodHandleInvokeCopy;
import com.vyazelenko.blog.copyobject.wrappers.mh.MethodHandleInvokeExactCopy;
import com.vyazelenko.blog.copyobject.wrappers.mh.MethodHandleInvokeWithArgumentsCopy;
import com.vyazelenko.blog.copyobject.wrappers.reflection.CachedFieldsCopy;
import com.vyazelenko.blog.copyobject.wrappers.reflection.FieldsCopy;
import com.vyazelenko.blog.copyobject.wrappers.serialization.DefaultSeralizationCopy;
import com.vyazelenko.blog.copyobject.wrappers.serialization.custom.CustomSeralizationCopy;
import com.vyazelenko.blog.copyobject.wrappers.serialization.externalizable.ExternalizableCopy;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;

public class WrappersBenchmark {

    @GenerateMicroBenchmark
    public Object copyFieldByFieldGetFieldsEveryTime() {
        return FieldsCopy.INSTANCE.copy();
    }

    @GenerateMicroBenchmark
    public Object copyFieldByFieldUseCacheFields() {
        return CachedFieldsCopy.INSTANCE.copy();
    }

    @GenerateMicroBenchmark
    public Object copyByClone() {
        return CloneCopy.INSTANCE.copy();
    }

    @GenerateMicroBenchmark
    public Object copyConstructor() {
        return ConstructorCopy.INSTANCE.copy();
    }

    @GenerateMicroBenchmark
    public Object copyByDefaultSerialization() {
        return DefaultSeralizationCopy.INSTANCE.copy();
    }

    @GenerateMicroBenchmark
    public Object copyByCustomSerialization() {
        return CustomSeralizationCopy.INSTANCE.copy();
    }

    @GenerateMicroBenchmark
    public Object copyByExternalizable() {
        return ExternalizableCopy.INSTANCE.copy();
    }

    @GenerateMicroBenchmark
    public Object copyViaMethodHandlesUsingInvoke() {
        return MethodHandleInvokeCopy.INSTANCE.copy();
    }

    @GenerateMicroBenchmark
    public Object copyViaMethodHandlesUsingInvokeWithArguments() {
        return MethodHandleInvokeWithArgumentsCopy.INSTANCE.copy();
    }

    @GenerateMicroBenchmark
    public Object copyViaMethodHandlesUsingInvokeExact() {
        return MethodHandleInvokeExactCopy.INSTANCE.copy();
    }
}
