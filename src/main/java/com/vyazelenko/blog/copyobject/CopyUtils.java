package com.vyazelenko.blog.copyobject;

import java.io.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class CopyUtils {
    private CopyUtils() {
    }

    /**
     * Copies contents of the <tt>src</tt> object into <tt>dest</tt> objet field by field acquiring fields upon every call.
     *
     * @param src  source object
     * @param dest destination object
     */
    public static void copyFieldByField(Object src, Object dest) {
        copyFields(src, dest, src.getClass());
    }

    private static void copyFields(Object src, Object dest, Class<?> klass) {
        Field[] fields = klass.getDeclaredFields();
        for (Field f : fields) {
            if (copyableField(f)) {
                f.setAccessible(true);
                copyFieldValue(src, dest, f);
            }
        }

        klass = klass.getSuperclass();
        if (klass != null) {
            copyFields(src, dest, klass);
        }
    }

    private static void copyFieldValue(Object src, Object dest, Field f) {
        try {
            Object value = f.get(src);
            f.set(dest, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ConcurrentMap<Class<?>, List<Field>> cachedFields = new ConcurrentHashMap<>();

    /**
     * Copy <tt>src</tt> object field by field into to <tt>dest</tt> object using cached fields.
     *
     * @param src  source object
     * @param dest destination object
     */
    public static void copyFieldByFieldUsingCachedFields(Object src, Object dest) {
        List<Field> fields = getFields(src);
        for (Field f : fields) {
            copyFieldValue(src, dest, f);
        }
    }

    private static List<Field> getFields(Object src) {
        Class<?> key = src.getClass();
        List<Field> fields = cachedFields.get(key);
        if (fields == null) {
            fields = loadFields(key);
            List<Field> tmp = cachedFields.putIfAbsent(key, fields);
            if (tmp != null) {
                fields = tmp;
            }
        }
        return fields;
    }

    private static List<Field> loadFields(Class<?> klass) {
        Field[] declaredFields = klass.getDeclaredFields();
        List<Field> result = new ArrayList<Field>(declaredFields.length);
        for (Field f : declaredFields) {
            if (copyableField(f)) {
                f.setAccessible(true);
                result.add(f);
            }
        }

        klass = klass.getSuperclass();
        if (klass != null) {
            result.addAll(loadFields(klass));
        }
        return result;
    }

    private static boolean copyableField(Field f) {
        return !Modifier.isStatic(f.getModifiers());
    }

    /**
     * Create copy of the object by serializing and deserializing it.
     *
     * @param <T>        type of the object
     * @param object     object to be serialized
     * @param bufferSize size of destination buffer to hold serialized object
     * @return deserialized copy of the object
     */
    public static <T extends Serializable> T copyViaSerialization(T object, int bufferSize) {
        byte[] bytes = serializeObject(object, bufferSize);
        return (T) deserializeObject(bytes);
    }

    private static byte[] serializeObject(Serializable object, int bufferSize) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(bufferSize);
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeUnshared(object);
            oos.flush();
            oos.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Serializable deserializeObject(byte[] bytes) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(bytes); ObjectInputStream ooi = new ObjectInputStream(in)) {
            return (Serializable) ooi.readUnshared();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static enum MHKind {
        INVOKE {
            @Override
            MethodHandlesPair create(MethodHandle getter, MethodHandle setter) {
                return new MethodHandlesPair(getter, setter) {
                    @Override
                    protected void doCopyField(Object src, Object dest) throws Throwable {
                        setter.invoke(dest, getter.invoke(src));
                    }
                };
            }
        },

        INVOKE_WITH_ARGUMENTS {
            @Override
            MethodHandlesPair create(MethodHandle getter, MethodHandle setter) {
                return new MethodHandlesPair(getter, setter) {
                    @Override
                    protected void doCopyField(Object src, Object dest) throws Throwable {
                        setter.invokeWithArguments(dest, getter.invokeWithArguments(src));
                    }
                };
            }
        },

        INVOKE_EXACT {
            @Override
            MethodHandlesPair create(MethodHandle getter, MethodHandle setter) {
                return new MethodHandlesPairWithAdjustedMethodTypes(getter, setter) {
                    @Override
                    protected void doCopyField(Object src, Object dest) throws Throwable {
                        setter.invokeExact(dest, getter.invokeExact(src));
                    }
                };
            }
        };

        abstract MethodHandlesPair create(MethodHandle getter, MethodHandle setter);
    }

    private static final ConcurrentMap<Class<?>, ConcurrentMap<MHKind, List<MethodHandlesPair>>> cachedMethodHandles = new ConcurrentHashMap<>();

    /**
     * Copy <tt>src</tt> object field by field into to <tt>dest</tt> using <tt>MethodHandles</tt> from Jave 7.
     *
     * @param src  source object
     * @param dest destination object
     * @param kind MethodHandle configuration to use
     */
    public static void copyViaMethodHandles(Object src, Object dest, MHKind kind) {
        List<MethodHandlesPair> handles = getMethodHandles(src, kind);
        for (MethodHandlesPair mh : handles) {
            mh.copyField(src, dest);
        }
    }

    private static List<MethodHandlesPair> getMethodHandles(Object src, MHKind kind) {
        ConcurrentMap<MHKind, List<MethodHandlesPair>> kindToHandles = getHandlesMap(src);
        List<MethodHandlesPair> handles = kindToHandles.get(kind);
        if (handles == null) {
            handles = resolveMethodHandles(src.getClass(), kind);
            List<MethodHandlesPair> tmp = kindToHandles.putIfAbsent(kind, handles);
            if (tmp != null) {
                handles = tmp;
            }
        }
        return handles;
    }

    private static ConcurrentMap<MHKind, List<MethodHandlesPair>> getHandlesMap(Object src) {
        Class<?> key = src.getClass();
        ConcurrentMap<MHKind, List<MethodHandlesPair>> kindToHandles = cachedMethodHandles.get(key);
        if (kindToHandles == null) {
            kindToHandles = new ConcurrentHashMap<>();
            ConcurrentMap<MHKind, List<MethodHandlesPair>> tmp = cachedMethodHandles.putIfAbsent(key, kindToHandles);
            if (tmp != null) {
                kindToHandles = tmp;
            }
        }
        return kindToHandles;
    }

    private static List<MethodHandlesPair> resolveMethodHandles(Class<?> klass, MHKind kind) {
        Field[] fields = klass.getDeclaredFields();
        List<MethodHandlesPair> handles = new ArrayList<>(fields.length);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        for (Field f : fields) {
            if (copyableField(f)) {
                try {
                    f.setAccessible(true);
                    handles.add(kind.create(lookup.unreflectGetter(f), lookup.unreflectSetter(f)));
                } catch (IllegalAccessException e) {
                    throw new Error(e);
                }
            }
        }

        Class<?> superClass = klass.getSuperclass();
        if (superClass != null) {
            handles.addAll(resolveMethodHandles(superClass, kind));
        }
        return handles;
    }

    private abstract static class MethodHandlesPair {
        final MethodHandle getter;
        final MethodHandle setter;

        public MethodHandlesPair(MethodHandle getter, MethodHandle setter) {
            this.getter = getter;
            this.setter = setter;
        }

        public void copyField(Object src, Object dest) {
            try {
                doCopyField(src, dest);
            } catch (Throwable t) {
                throw new Error(t);
            }
        }

        protected abstract void doCopyField(Object src, Object dest) throws Throwable;

    }

    private abstract static class MethodHandlesPairWithAdjustedMethodTypes extends MethodHandlesPair {

        public MethodHandlesPairWithAdjustedMethodTypes(MethodHandle getter, MethodHandle setter) {
            super(prepareGetter(getter), prepareSetter(setter));
        }

        private static MethodHandle prepareGetter(MethodHandle mh) {
            return mh.asType(mh.type().changeParameterType(0, Object.class).changeReturnType(Object.class));
        }

        private static MethodHandle prepareSetter(MethodHandle mh) {
            return mh.asType(mh.type().changeParameterType(0, Object.class).changeParameterType(1, Object.class));
        }
    }
}
