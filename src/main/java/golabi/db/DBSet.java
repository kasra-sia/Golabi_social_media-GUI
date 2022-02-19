package golabi.db;


import golabi.model.account.User;

import java.util.LinkedList;

public interface DBSet<T> {
    T get(int id);
    LinkedList<T> all();
    void add(T t);
    void remove(T t);
    void update(T t);
    String getPhoto(int id);
    void updatePhoto(T t,String path);
    void removePhoto(T t);
    void report(T t, User user);
}