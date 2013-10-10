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
import org.junit.Test;

public class WrappersSanityTests extends com.vyazelenko.blog.copyobject.SanityTestsBase {
    @Test
    public void copyViaClone() {
        testCopy(CloneCopy.INSTANCE);
    }

    @Test
    public void copyViaConstructor() {
        testCopy(ConstructorCopy.INSTANCE);
    }

    @Test
    public void copyViaMethodHandleInvoke() {
        testCopy(MethodHandleInvokeCopy.INSTANCE);
    }

    @Test
    public void copyViaMethodHandleInvokeWithArgs() {
        testCopy(MethodHandleInvokeWithArgumentsCopy.INSTANCE);
    }

    @Test
    public void copyViaMethodHandleInvokeExact() {
        testCopy(MethodHandleInvokeExactCopy.INSTANCE);
    }

    @Test
    public void copyViaFields() {
        testCopy(FieldsCopy.INSTANCE);
    }

    @Test
    public void copyViaCachedFields() {
        testCopy(CachedFieldsCopy.INSTANCE);
    }

    @Test
    public void copyViaSerialization() {
        testCopy(DefaultSeralizationCopy.INSTANCE);
    }

    @Test
    public void copyViaCustomSerialization() {
        testCopy(CustomSeralizationCopy.INSTANCE);
    }

    @Test
    public void copyViaExternalizable() {
        testCopy(ExternalizableCopy.INSTANCE);
    }

}
