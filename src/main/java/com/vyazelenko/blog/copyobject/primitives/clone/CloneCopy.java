package com.vyazelenko.blog.copyobject.primitives.clone;

import com.vyazelenko.blog.copyobject.primitives.BaseClass;

public class CloneCopy extends BaseClass implements Cloneable {
    public static final CloneCopy INSTANCE;

    static {
        INSTANCE = new CloneCopy();
        INSTANCE.init();
    }

    @Override
    protected CloneCopy clone() {
        try {
            return (CloneCopy) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    @Override
    public CloneCopy copy() {
        return clone();
    }
}
