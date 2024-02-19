package org.example;

import java.io.*;

public class Serializer<T> {
    private final String path;

    public Serializer(String path) {
        this.path = path;
    }

    public void serialize(T obj) throws IOException, ClassNotFoundException {
        final ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        ObjectOutputStream.writeObject(obj);
    }

    @SuppressWarnings("unchecked")
    public T deserialize() throws IOException, ClassNotFoundException {
        final ObjectInputStream ObjectInputStream = new ObjectInputStream(new FileInputStream(path));
        return (T)ObjectInputStream.readObject();
    }
}
