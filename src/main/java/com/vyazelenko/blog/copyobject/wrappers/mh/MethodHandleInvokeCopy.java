package com.vyazelenko.blog.copyobject.wrappers.mh;

import com.vyazelenko.blog.copyobject.CopyUtils;
import com.vyazelenko.blog.copyobject.wrappers.BaseClass;

public class MethodHandleInvokeCopy extends BaseClass {
    public static final MethodHandleInvokeCopy INSTANCE;

    static {
        INSTANCE = new MethodHandleInvokeCopy();
        INSTANCE.init();
    }

    @Override
    public MethodHandleInvokeCopy copy() {
        MethodHandleInvokeCopy copy = new MethodHandleInvokeCopy();
        CopyUtils.copyViaMethodHandles(this, copy, CopyUtils.MHKind.INVOKE);
        return copy;
    }
}



