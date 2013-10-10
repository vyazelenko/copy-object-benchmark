package com.vyazelenko.blog.copyobject.wrappers;

import com.vyazelenko.blog.copyobject.Copyable;
import com.vyazelenko.blog.copyobject.HashUtils;

import java.util.Objects;

abstract class Root implements Copyable {
    private Integer field1;
    private Character field2;
    public Boolean field6;
    Byte abc;
    public Long min;
    public Long max;
    private Double maxExponent;

    public Root() {
    }

    public void init() {
        field1 = 100;
        field2 = '\t';
        field6 = false;
        abc = -100;
        min = Long.MIN_VALUE;
        max = Long.MAX_VALUE;
        maxExponent = Double.MAX_VALUE;
    }

    public Root(Root copyFrom) {
        field1 = copyFrom.field1;
        field2 = copyFrom.field2;
        field6 = copyFrom.field6;
        abc = copyFrom.abc;
        min = copyFrom.min;
        max = copyFrom.max;
        maxExponent = copyFrom.maxExponent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Root)) {
            return false;
        } else {
            Root tmp = (Root) obj;
            return Objects.equals(field1, tmp.field1) && Objects.equals(field2, tmp.field2) && Objects.equals(field6, tmp.field6)
                    && Objects.equals(abc, tmp.abc) && Objects.equals(min, tmp.min) && Objects.equals(max, tmp.max)
                    && Objects.equals(maxExponent, tmp.maxExponent);
        }
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash += 31 * hash + HashUtils.objectHash(field1);
        hash += 31 * hash + HashUtils.objectHash(field2);
        hash += 31 * hash + HashUtils.objectHash(field6);
        hash += 31 * hash + HashUtils.objectHash(abc);
        hash += 31 * hash + HashUtils.objectHash(min);
        hash += 31 * hash + HashUtils.objectHash(max);
        hash += 31 * hash + HashUtils.objectHash(maxExponent);
        return hash;
    }
}

public abstract class BaseClass extends Root {
    private Double anotherField;
    private Integer field1;
    protected Long youCanSeeMe;
    private Integer m1;
    public Integer m2;
    public Integer m3;
    public Integer m4;
    Integer m5;
    public Integer m6;
    public Integer m7;
    protected Integer m8;
    public Integer m9;
    public Integer m10;
    private char x;

    public BaseClass() {
        super();
    }

    @Override
    public void init() {
        super.init();
        anotherField = 10.5;
        field1 = Integer.MIN_VALUE;
        youCanSeeMe = -1L;
        m1 = 10;
        m2 = 20;
        m3 = 30;
        m4 = 40;
        m5 = 50;
        m6 = 60;
        m7 = 70;
        m8 = 80;
        m9 = 90;
        m10 = 100;
        x = '\n';
    }

    public BaseClass(BaseClass copyFrom) {
        super(copyFrom);
        anotherField = copyFrom.anotherField;
        field1 = copyFrom.field1;
        youCanSeeMe = copyFrom.youCanSeeMe;
        m1 = copyFrom.m1;
        m2 = copyFrom.m2;
        m3 = copyFrom.m3;
        m4 = copyFrom.m4;
        m5 = copyFrom.m5;
        m6 = copyFrom.m6;
        m7 = copyFrom.m7;
        m8 = copyFrom.m8;
        m9 = copyFrom.m9;
        m10 = copyFrom.m10;
        x = copyFrom.x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof BaseClass)) {
            return false;
        } else {
            BaseClass tmp = (BaseClass) obj;
            return super.equals(tmp) && Objects.equals(anotherField, tmp.anotherField)
                    && Objects.equals(field1, tmp.field1) && Objects.equals(youCanSeeMe, tmp.youCanSeeMe)
                    && Objects.equals(m1, tmp.m1) && Objects.equals(m2, tmp.m2) && Objects.equals(m3, tmp.m3)
                    && Objects.equals(m4, tmp.m4) && Objects.equals(m5, tmp.m5) && Objects.equals(m6, tmp.m6)
                    && Objects.equals(m7, tmp.m7) && Objects.equals(m8, tmp.m8) && Objects.equals(m9, tmp.m9)
                    && Objects.equals(m10, tmp.m10) && Objects.equals(x, tmp.x);
        }
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 31 * hash + HashUtils.objectHash(anotherField);
        hash = 31 * hash + HashUtils.objectHash(field1);
        hash = 31 * hash + HashUtils.objectHash(youCanSeeMe);
        hash = 31 * hash + HashUtils.objectHash(m1);
        hash = 31 * hash + HashUtils.objectHash(m2);
        hash = 31 * hash + HashUtils.objectHash(m3);
        hash = 31 * hash + HashUtils.objectHash(m4);
        hash = 31 * hash + HashUtils.objectHash(m5);
        hash = 31 * hash + HashUtils.objectHash(m6);
        hash = 31 * hash + HashUtils.objectHash(m7);
        hash = 31 * hash + HashUtils.objectHash(m8);
        hash = 31 * hash + HashUtils.objectHash(m9);
        hash = 31 * hash + HashUtils.objectHash(m10);
        hash = 31 * hash + HashUtils.objectHash(x);
        return hash;
    }
}
