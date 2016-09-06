package com.andframe.caches;

import android.content.Context;

import com.andframe.feature.AfJsoner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * AfJsonCache
 *
 * @author 树朾
 *         包装 AfSharedPreference 主要实现
 *         1.对任意对象 Object 的支持
 *         public void put(String key, Object value)
 *         public void put(String key, Object value,Class<?> clazz)
 *         2.对任意 List 的支持
 *         putList(String key, List<T> values,Class<?> clazz)
 *         public<T> List<T> getList(String key,Class<T> clazz)
 */
@SuppressWarnings("unused")
public class AfJsonCache {

    private AfSharedPreference mShared = null;

    public AfJsonCache(AfSharedPreference preference) {
        mShared = preference;
    }

    public AfJsonCache(Context context, String name) {
        mShared = new AfSharedPreference(context, name);
    }

    public AfJsonCache(Context context, String name, int mode) {
        mShared = new AfSharedPreference(context, name, mode);
    }

    public AfJsonCache(Context context, String path, String name) throws Exception {
        mShared = new AfSharedPreference(context, path, name);
    }

    public AfSharedPreference getShared() {
        return mShared;
    }

    public void put(String key, Object value) {
        mShared.putString(key, AfJsoner.toJson(value));
    }

    /**
     * 保存列表数据
     * （新的列表会覆盖原来的所有内容）
     */
    public void putList(String key, List<?> values) {
        List<String> set = new ArrayList<>();
        for (Object value : values) {
            set.add(AfJsoner.toJson(value));
        }
        mShared.putStringList(key, set);
    }

    /**
     * 保存列表数据
     * （新的列表会覆盖原来的所有内容）
     */
    public void putList(String key, Object[] values) {
        List<String> set = new ArrayList<>();
        for (Object value : values) {
            set.add(AfJsoner.toJson(value));
        }
        mShared.putStringList(key, set);
    }

    /**
     * 追加列表数据
     * （新的内容会和老的内容一起保存）
     */
    public void pushList(String key, List values) {
        List<String> set = new ArrayList<>(mShared.getStringList(key, new ArrayList<>()));
        for (Object value : values) {
            set.add(AfJsoner.toJson(value));
        }
        mShared.putStringList(key, set);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, null, clazz);
    }

    public <T> T get(String key, T defaul, Class<T> clazz) {
        T value = null;
        try {
            String svalue = mShared.getString(key, "");
            value = AfJsoner.fromJson(svalue, clazz);
        } catch (Throwable ignored) {
        }
        return value == null ? defaul : value;
    }

    /**
     * 获取列表缓存
     *
     * @return 即使缓存不存在 也不会返回null 而是空列表
     */
    public <T> List<T> getList(String key, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            List<String> set = mShared.getStringList(key, new ArrayList<>());
            for (String string : set) {
                list.add(AfJsoner.fromJson(string, clazz));
            }
        } catch (Throwable ignored) {
        }
        return list;
    }

    public void clear() {
        mShared.clear();
    }

    public boolean isEmpty(String key) {
        try {
            return mShared.getString(key, "").equals("") &&
                    mShared.getStringSet(key, new HashSet<>()).isEmpty();
        } catch (Throwable e) {
            return true;
        }
    }

    public boolean getBoolean(String key, boolean value) {
        return get(key, value, Boolean.class);
    }

    public String getString(String key, String value) {
        return get(key, value, String.class);
    }

    public float getFloat(String key, float value) {
        return get(key, value, Float.class);
    }

    public int getInt(String key, int value) {
        return get(key, value, Integer.class);
    }

    public long getLong(String key, long value) {
        return get(key, value, Long.class);
    }

    public Date getDate(String key, Date value) {
        return get(key, value, Date.class);
    }
}
