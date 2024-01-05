package com.hzw.learn.hibernate;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @ClassName DesensitizationInterceptor
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/15
 **/
public class DesensitizationInterceptor implements Interceptor {
    @Override
    public boolean onLoad(Object o, Serializable serializable, Object[] objects, String[] strings, Type[] types) throws CallbackException {

        return false;
    }

    @Override
    public boolean onFlushDirty(Object o, Serializable serializable, Object[] objects, Object[] objects1, String[] strings, Type[] types) throws CallbackException {
        return false;
    }

    @Override
    public boolean onSave(Object o, Serializable serializable, Object[] objects, String[] strings, Type[] types) throws CallbackException {
        return false;
    }

    @Override
    public void onDelete(Object o, Serializable serializable, Object[] objects, String[] strings, Type[] types) throws CallbackException {

    }

    @Override
    public void onCollectionRecreate(Object o, Serializable serializable) throws CallbackException {

    }

    @Override
    public void onCollectionRemove(Object o, Serializable serializable) throws CallbackException {

    }

    @Override
    public void onCollectionUpdate(Object o, Serializable serializable) throws CallbackException {

    }

    @Override
    public void preFlush(Iterator iterator) throws CallbackException {

    }

    @Override
    public void postFlush(Iterator iterator) throws CallbackException {

    }

    @Override
    public Boolean isTransient(Object o) {
        return null;
    }

    @Override
    public int[] findDirty(Object o, Serializable serializable, Object[] objects, Object[] objects1, String[] strings, Type[] types) {
        return new int[0];
    }

    @Override
    public Object instantiate(String s, EntityMode entityMode, Serializable serializable) throws CallbackException {
        return null;
    }

    @Override
    public String getEntityName(Object o) throws CallbackException {
        return null;
    }

    @Override
    public Object getEntity(String s, Serializable serializable) throws CallbackException {
        return null;
    }

    @Override
    public void afterTransactionBegin(Transaction transaction) {

    }

    @Override
    public void beforeTransactionCompletion(Transaction transaction) {

    }

    @Override
    public void afterTransactionCompletion(Transaction transaction) {

    }

    @Override
    public String onPrepareStatement(String s) {
        return null;
    }
}
