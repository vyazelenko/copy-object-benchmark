package com.vyazelenko.blog.copyobject;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class SanityTestsBase {
    protected void testCopy(Copyable instance) {
        ensureInstanceBelongsToRightPackage(instance);

        Copyable copy = instance.copy();
        assertThat(copy, is(instanceOf(instance.getClass())));
        assertThat(copy, is(not(sameInstance(instance))));
        assertThat(copy, equalTo(instance));
    }

    private void ensureInstanceBelongsToRightPackage(Copyable instance) {
        String expected = getClass().getPackage().getName();
        String actual = instance.getClass().getPackage().getName();
        assertThat("Wrong package name", actual, startsWith(expected));
    }
}
