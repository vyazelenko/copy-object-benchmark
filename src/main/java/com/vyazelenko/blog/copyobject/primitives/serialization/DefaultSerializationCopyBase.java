package com.vyazelenko.blog.copyobject.primitives.serialization;

import com.vyazelenko.blog.copyobject.Copyable;
import com.vyazelenko.blog.copyobject.HashUtils;

import java.io.Serializable;

abstract class DefaultSerializationCopyRoot implements Serializable, Copyable {
    private int field1;
    private char field2;
    public boolean field6;
    byte abc;
    public long min;
    public long max;
    private double maxExponent;

    public DefaultSerializationCopyRoot() {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof DefaultSerializationCopyRoot)) {
            return false;
        } else {
            DefaultSerializationCopyRoot tmp = (DefaultSerializationCopyRoot) obj;
            return field1 == tmp.field1 && field2 == tmp.field2 && field6 == tmp.field6
                    && abc == tmp.abc && min == tmp.min && max == tmp.max
                    && Double.compare(maxExponent, tmp.maxExponent) == 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash += 31 * hash + field1;
        hash += 31 * hash + field2;
        hash += 31 * hash + (field6 ? 1 : 0);
        hash += 31 * hash + abc;
        hash += 31 * hash + HashUtils.longHash(min);
        hash += 31 * hash + HashUtils.longHash(max);
        hash += 31 * hash + HashUtils.doubleHash(maxExponent);
        return hash;
    }

}

abstract class DefaultSerializationCopyBase extends DefaultSerializationCopyRoot {
    private double anotherField;
    private int field1;
    protected long youCanSeeMe;
    private short m1;
    public short m2;
    public short m3;
    public short m4;
    short m5;
    public short m6;
    public short m7;
    protected short m8;
    public short m9;
    public short m10;
    private char x;

    public DefaultSerializationCopyBase() {
        super();
    }

    @Override
    public void init() {
        super.init();
        anotherField = 10.5;
        field1 = Integer.MIN_VALUE;
        youCanSeeMe = -1;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof DefaultSerializationCopyBase)) {
            return false;
        } else {
            DefaultSerializationCopyBase tmp = (DefaultSerializationCopyBase) obj;
            return super.equals(tmp) && Double.compare(anotherField, tmp.anotherField) == 0
                    && field1 == tmp.field1 && youCanSeeMe == tmp.youCanSeeMe
                    && m1 == tmp.m1 && m2 == tmp.m2 && m3 == tmp.m3 && m4 == tmp.m4
                    && m5 == tmp.m5 && m6 == tmp.m6 && m7 == tmp.m7 && m8 == tmp.m8
                    && m9 == tmp.m9 && m10 == tmp.m10 && x == tmp.x;
        }
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 31 * hash + HashUtils.doubleHash(anotherField);
        hash = 31 * hash + field1;
        hash = 31 * hash + HashUtils.longHash(youCanSeeMe);
        hash = 31 * hash + m1;
        hash = 31 * hash + m2;
        hash = 31 * hash + m3;
        hash = 31 * hash + m4;
        hash = 31 * hash + m5;
        hash = 31 * hash + m6;
        hash = 31 * hash + m7;
        hash = 31 * hash + m8;
        hash = 31 * hash + m9;
        hash = 31 * hash + m10;
        hash = 31 * hash + x;
        return hash;
    }
}
