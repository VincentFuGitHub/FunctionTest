package com.unitfunction.funfrag.dbunitfragment;

/**
 * Created by Vincent on 2018/9/14.
 */

public interface DbFragCallBack {
    void createDb();
    void addDbItem();
    void deleteItem();
    void updateItem();
    void queryItem();
}
