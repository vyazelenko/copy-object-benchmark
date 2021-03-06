package com.vyazelenko.blog.copyobject.primitives.mh;

import com.vyazelenko.blog.copyobject.CopyUtils;
import com.vyazelenko.blog.copyobject.primitives.BaseClass;

public class MethodHandleInvokeExactCopy extends BaseClass {
    public static final MethodHandleInvokeExactCopy INSTANCE;

    static {
        INSTANCE = new MethodHandleInvokeExactCopy();
        INSTANCE.init();
    }


    @Override
    public MethodHandleInvokeExactCopy copy() {
        MethodHandleInvokeExactCopy copy = new MethodHandleInvokeExactCopy();
        CopyUtils.copyViaMethodHandles(this, copy, CopyUtils.MHKind.INVOKE_EXACT);
        return copy;
    }
}



