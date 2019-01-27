package com.booking;

import com.booking.Flights.Flight;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ObjectToFileReaderWriter <T>{

    public T readObjectFromFile(String path, T object) {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(path)));
            object = (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public void writeObjectToFile(String path,  T object) {
        FileOutputStream file;
        try {
            file = new FileOutputStream(path);
            ObjectOutputStream data = new ObjectOutputStream(file);
            data.writeObject(object);
            data.flush();
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
