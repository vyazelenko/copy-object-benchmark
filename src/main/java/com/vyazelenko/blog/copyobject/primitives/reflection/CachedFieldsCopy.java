package com.vyazelenko.blog.copyobject.primitives.reflection;

import com.vyazelenko.blog.copyobject.CopyUtils;
import com.vyazelenko.blog.copyobject.primitives.BaseClass;

public class CachedFieldsCopy extends BaseClass {
    public static final CachedFieldsCopy INSTANCE;

    static {
        INSTANCE = new CachedFieldsCopy();
        INSTANCE.init();
    }

    @Override
    public CachedFieldsCopy copy() {
        CachedFieldsCopy copy = new CachedFieldsCopy();
        CopyUtils.copyFieldByFieldUsingCachedFields(this, copy);
        return copy;
    }
}
