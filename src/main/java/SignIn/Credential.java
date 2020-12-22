package SignIn;

import java.text.SimpleDateFormat;
import java.util.*;

public class Credential {
    private static HashMap<String,String> book = new HashMap<String,String>();

    public Credential() {
    }

    public synchronized void add(String name,String pass) {
        book.put(name,pass);
    }

    public boolean isFirst() {
        if(book.isEmpty())
            return true;
        return false;
    }

    public HashMap<String,String> getBook() {
        return this.book;
    }

    public boolean check(String key) {
        if(book.containsKey(key))
            return true;
        return false;
    }

    public boolean checkPass(String key,String password) {
        if(book.get(key).equals(password))
            return true;
        return false;
    }

    public void delete(String name) {
        book.remove(name);
    }

}
