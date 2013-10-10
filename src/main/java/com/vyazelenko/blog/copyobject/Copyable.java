package com.vyazelenko.blog.copyobject;

public interface Copyable {
    /**
     * Create full shallow copy of this object.
     *
     * @return copy of this object
     */
    Copyable copy();

    /**
     * Initialize object with test values.
     */
    void init();
}
