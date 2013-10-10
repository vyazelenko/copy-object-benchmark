package com.vyazelenko.blog.copyobject.primitives.serialization;

import com.vyazelenko.blog.copyobject.CopyUtils;

import java.io.Serializable;

public class DefaultSeralizationCopy extends DefaultSerializationCopyBase implements Serializable {
    public static final transient DefaultSeralizationCopy INSTANCE;

    static {
        INSTANCE = new DefaultSeralizationCopy();
        INSTANCE.init();
    }

    @Override
    public DefaultSeralizationCopy copy() {
        return CopyUtils.copyViaSerialization(this, 600);
    }

}
