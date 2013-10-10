package com.vyazelenko.blog.copyobject.primitives.reflection;

import com.vyazelenko.blog.copyobject.CopyUtils;
import com.vyazelenko.blog.copyobject.primitives.BaseClass;

public class FieldsCopy extends BaseClass {
    public static final FieldsCopy INSTANCE;

    static {
        INSTANCE = new FieldsCopy();
        INSTANCE.init();
    }

    @Override
    public FieldsCopy copy() {
        FieldsCopy copy = new FieldsCopy();
        CopyUtils.copyFieldByField(this, copy);
        return copy;
    }
}
