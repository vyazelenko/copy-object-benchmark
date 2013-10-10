package com.vyazelenko.blog.copyobject.wrappers.mh;

import com.vyazelenko.blog.copyobject.CopyUtils;
import com.vyazelenko.blog.copyobject.wrappers.BaseClass;

public class MethodHandleInvokeWithArgumentsCopy extends BaseClass {
    public static final MethodHandleInvokeWithArgumentsCopy INSTANCE;

    static {
        INSTANCE = new MethodHandleInvokeWithArgumentsCopy();
        INSTANCE.init();
    }

    @Override
    public MethodHandleInvokeWithArgumentsCopy copy() {
        MethodHandleInvokeWithArgumentsCopy copy = new MethodHandleInvokeWithArgumentsCopy();
        CopyUtils.copyViaMethodHandles(this, copy, CopyUtils.MHKind.INVOKE_WITH_ARGUMENTS);
        return copy;
    }
}



