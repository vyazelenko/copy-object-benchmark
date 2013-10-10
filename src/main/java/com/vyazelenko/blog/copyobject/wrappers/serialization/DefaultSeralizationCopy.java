package com.vyazelenko.blog.copyobject.wrappers.serialization;

import com.vyazelenko.blog.copyobject.CopyUtils;

import java.io.Serializable;

public class DefaultSeralizationCopy extends DefaultSerializationCopyBase implements Serializable {
    public static final DefaultSeralizationCopy INSTANCE;

    static {
        INSTANCE = new DefaultSeralizationCopy();
        INSTANCE.init();
    }

    @Override
    public DefaultSeralizationCopy copy() {
        return CopyUtils.copyViaSerialization(this, 1200);
    }
}
