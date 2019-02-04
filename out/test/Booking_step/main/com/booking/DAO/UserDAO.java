package com.booking.DAO;

import com.booking.Exceptions.FileReadingException;
import com.booking.interfaces.Constants;
import com.booking.logger.BookingServiceLogger;
import com.booking.objects.User;
import com.booking.utils.ObjectToFileReaderWriter;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class UserDAO implements DAO<String, User> {
    private Map<String, User> users;
    private ObjectToFileReaderWriter<Map<String, User>> objectToFileReaderWriter = new ObjectToFileReaderWriter();

    public UserDAO() {
        if (isCollectionExist(Constants.usersPath)) {

            try {
                users = objectToFileReaderWriter.readObjectFromFile(Constants.usersPath, users);
            } catch (FileReadingException e) {
                System.out.println(e.getMessage());
                System.out.println("The source file will be renewed!");
                this.users = new HashMap<>();
                objectToFileReaderWriter.writeObjectToFile(Constants.usersPath, this.users);
            }

        } else {
            this.users = new HashMap<>();
            objectToFileReaderWriter.writeObjectToFile(Constants.usersPath, this.users);
        }
    }

    private boolean isCollectionExist(String path) {
        return new File(path).isFile();
    }
    @Override
    public void save(User user) {
        if (user != null) {
            users.put(user.id(), user);
            //logger.info("New user: "+ user.getLogin() + " was added to users database");
            objectToFileReaderWriter.writeObjectToFile(Constants.usersPath, users);
            //logger.info("Users database was rewritten to ./users.txt path");
        }
    }

    @Override
    public User getById(String id) {
        //logger.info("User was got by ID");
        return users.get(id);
    }

    @Override
    public Collection<User> getAll() {
        //logger.info("Get all information about Users");
        return users.values();
    }

    @Override
    public void remove(String id) {
        if (id == null) {
            //logger.error("Error information: user wasn't remove");
            return;
        }
        users.remove(id);
        //logger.info("User: "+ id + " was removed from users database");
        objectToFileReaderWriter.writeObjectToFile(Constants.usersPath, users);
        //logger.info("Information was update in ./users.txt path");
    }

}
