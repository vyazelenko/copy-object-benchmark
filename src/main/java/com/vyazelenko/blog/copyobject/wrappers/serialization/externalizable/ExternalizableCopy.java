package com.vyazelenko.blog.copyobject.wrappers.serialization.externalizable;

import com.vyazelenko.blog.copyobject.CopyUtils;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ExternalizableCopy extends ExternalizableCopyBase implements Externalizable {
    public static final ExternalizableCopy INSTANCE;

    static {
        INSTANCE = new ExternalizableCopy();
        INSTANCE.init();
    }

    @Override
    public ExternalizableCopy copy() {
        return CopyUtils.copyViaSerialization(this, 1200);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
    }
}
