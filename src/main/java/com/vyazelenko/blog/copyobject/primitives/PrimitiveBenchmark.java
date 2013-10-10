package com.vyazelenko.blog.copyobject.primitives;

import com.vyazelenko.blog.copyobject.primitives.clone.CloneCopy;
import com.vyazelenko.blog.copyobject.primitives.constructor.ConstructorCopy;
import com.vyazelenko.blog.copyobject.primitives.mh.MethodHandleInvokeCopy;
import com.vyazelenko.blog.copyobject.primitives.mh.MethodHandleInvokeExactCopy;
import com.vyazelenko.blog.copyobject.primitives.mh.MethodHandleInvokeWithArgumentsCopy;
import com.vyazelenko.blog.copyobject.primitives.reflection.CachedFieldsCopy;
import com.vyazelenko.blog.copyobject.primitives.reflection.FieldsCopy;
import com.vyazelenko.blog.copyobject.primitives.serialization.DefaultSeralizationCopy;
import com.vyazelenko.blog.copyobject.primitives.serialization.custom.CustomSeralizationCopy;
import com.vyazelenko.blog.copyobject.primitives.serialization.externalizable.ExternalizableCopy;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;

public class PrimitiveBenchmark {
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
