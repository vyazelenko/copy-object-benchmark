package com.vyazelenko.blog.copyobject.wrappers.constructor;

import com.vyazelenko.blog.copyobject.wrappers.BaseClass;

public class ConstructorCopy extends BaseClass implements Cloneable {
    public static final ConstructorCopy INSTANCE;

    static {
        INSTANCE = new ConstructorCopy();
        INSTANCE.init();
    }

    public ConstructorCopy() {
        super();
    }

    public ConstructorCopy(ConstructorCopy copyTo) {
        super(copyTo);
    }

    @Override
    public ConstructorCopy copy() {
        return new ConstructorCopy(this);
    }
}
