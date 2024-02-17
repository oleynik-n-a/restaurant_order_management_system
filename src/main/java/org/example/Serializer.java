package org.example;

import java.io.*;

public class Serializer<T> {
    private final String path;

    public Serializer(String path) {
        this.path = path;
    }

    public void Serialize(T obj) throws IOException, ClassNotFoundException {
        final ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        ObjectOutputStream.writeObject(obj);
    }

    public T Deserialize() throws IOException, ClassNotFoundException {
        final ObjectInputStream ObjectInputStream = new ObjectInputStream(new FileInputStream(path));
        return (T) ObjectInputStream.readObject();
    }
}
