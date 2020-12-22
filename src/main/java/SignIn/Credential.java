package SignIn;

import java.text.SimpleDateFormat;
import java.util.*;

public class Credential {
    private static HashMap<String,String> book = new HashMap<String,String>();
    private static boolean first;

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

    public synchronized String getDesk() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String,String> nm :book.entrySet()) {
            str.append("<p>");
            str.append(nm.getKey());
            str.append("<br>");
            str.append(nm.getValue());
            str.append("</p>");
        }
        return str.toString();
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
    public boolean checkAdmin(String key) {
        if(key.equals("admin"))
            return true;
        return false;
    }
    public boolean checkPassAdmin(String key,String password) {
        if(book.get(key).equals(password))
            return true;
        return false;
    }
    public void delete(String name) {
        book.remove(name);
    }
    public synchronized void reset() {
        book.clear();
    }
}
