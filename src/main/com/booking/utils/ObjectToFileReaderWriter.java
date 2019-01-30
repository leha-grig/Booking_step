package com.booking.utils;

import com.booking.Exceptions.FileReadingException;

import java.io.*;

public class ObjectToFileReaderWriter <T>{

    public T readObjectFromFile(String path, T object) throws FileReadingException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(path)));
            object = (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new FileReadingException("The source file was not read properly!");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    public void writeObjectToFile(String path,  T object) {
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(path);
            ObjectOutputStream data = new ObjectOutputStream(file);
            data.writeObject(object);
            data.flush();
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
