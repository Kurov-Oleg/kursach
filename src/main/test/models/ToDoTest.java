package models;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void readDo() throws IOException {
        ToDo todo = new ToDo();
        HashMap<String,Point> map = new HashMap<>();
        Point point = new Point();
        point.setStatus("in progress");
        point.setDescription("need");
        point.setDate("11.22.11");
        point.setFinalDate("40.512.3463");
        map.put("topic",point);
        Files.write(Paths.get("test.txt"),
                map.entrySet().stream().map(k->k.getKey() + "\n" +
                        k.getValue().getDate() + "\n" +
                        k.getValue().getFinalDate() + "\n" +
                        k.getValue().getStatus() + "\n" +
                        k.getValue().getDescription()).collect(Collectors.toList()),
                StandardCharsets.UTF_8);
        for (Map.Entry<String,Point> entry : map.entrySet()) {
            Point point2 = todo.readDo("test").get(entry.getKey());
            assertEquals(entry.getValue().getDate(), point2.getDate());
            assertEquals(entry.getValue().getFinalDate(), point2.getFinalDate());
            assertEquals(entry.getValue().getDescription(), point2.getDescription());
            assertEquals(entry.getValue().getStatus(), point2.getStatus());
        }
    }

    @Test
    void addDo() throws IOException {
        ToDo todo = new ToDo();
        File f = new File("test1.txt");
        f.createNewFile();
        todo.addDo("test1","40.512.3463","need to do","topic17","in progress","a");
        HashMap<String,Point> map = new HashMap<>();
        Point point = new Point();
        point.setStatus("in progress");
        point.setDescription("need to do");
        point.setFinalDate("40.512.3463");
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        Date dateNow = new Date();
        String date = formatForDateNow.format(dateNow);
        point.setDate(date);
        map.put("topic17",point);
        for (Map.Entry<String,Point> entry : map.entrySet()) {
            Point point2 = todo.readDo("test1").get(entry.getKey());
            assertEquals(entry.getValue().getDate(), point2.getDate());
            assertEquals(entry.getValue().getFinalDate(), point2.getFinalDate());
            assertEquals(entry.getValue().getDescription(), point2.getDescription());
            assertEquals(entry.getValue().getStatus(), point2.getStatus());
        }
    }

    @Test
    void checkIn() throws IOException {
        StringBuilder str = new StringBuilder();
        ToDo todo = new ToDo();
        File f = new File("test3.txt");
        f.createNewFile();
        todo.addDo("test3","40.512.3463","need to do","topic17","in progress","a");
        HashMap<String,Point> map = new HashMap<>();
        Point point = new Point();
        point.setStatus("in progress");
        point.setDescription("need to do");
        point.setFinalDate("40.512.3463");
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        Date dateNow = new Date();
        String date = formatForDateNow.format(dateNow);
        point.setDate(date);
        map.put("topic17",point);
        for (Map.Entry<String,Point> entry : map.entrySet()) {
            str.append("<a>" + entry.getKey() + "</a><br>");
            str.append("<a>" + entry.getValue().getDate() + "</a>");
            str.append("<a>" + entry.getValue().getStatus() + "</a><br>");
            str.append("<a>" + entry.getValue().getFinalDate() + "</a><br>");
            str.append("<a>" + entry.getValue().getDescription() + "</a><br>");
            str.append("<br>");
            str.append("<br>");
        }
        assertEquals(str.toString(),todo.checkIn("test3"));
    }

    @Test
    void setAs() throws IOException {
        ToDo todo = new ToDo();
        File f = new File("test2.txt");
        f.createNewFile();
        todo.addDo("test2","40.512.3463","need to do","topic17","in progress","a");
        todo.setAs("test2","topic17","done");
        HashMap<String,Point> map = new HashMap<>();
        Point point = new Point();
        point.setStatus("done");
        point.setDescription("need to do");
        point.setFinalDate("40.512.3463");
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        Date dateNow = new Date();
        String date = formatForDateNow.format(dateNow);
        point.setDate(date);
        map.put("topic17",point);
        for (Map.Entry<String,Point> entry : map.entrySet()) {
            Point point2 = todo.readDo("test2").get(entry.getKey());
            assertEquals(entry.getValue().getDate(), point2.getDate());
            assertEquals(entry.getValue().getFinalDate(), point2.getFinalDate());
            assertEquals(entry.getValue().getDescription(), point2.getDescription());
            assertEquals(entry.getValue().getStatus(), point2.getStatus());
        }

    }

    @Test
    void checkDone() throws IOException {
        StringBuilder str = new StringBuilder();
        ToDo todo = new ToDo();
        File f = new File("test4.txt");
        f.createNewFile();
        todo.addDo("test4","40.512.3463","need to do","topic17","done","a");
        HashMap<String,Point> map = new HashMap<>();
        Point point = new Point();
        point.setStatus("done");
        point.setDescription("need to do");
        point.setFinalDate("40.512.3463");
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        Date dateNow = new Date();
        String date = formatForDateNow.format(dateNow);
        point.setDate(date);
        map.put("topic17",point);
        for (Map.Entry<String,Point> entry : map.entrySet()) {
            str.append("<a>" + entry.getKey() + "</a><br>");
            str.append("<a>" + entry.getValue().getDate() + "</a>");
            str.append("<a>" + entry.getValue().getStatus() + "</a><br>");
            str.append("<a>" + entry.getValue().getFinalDate() + "</a><br>");
            str.append("<a>" + entry.getValue().getDescription() + "</a><br>");
            str.append("<br>");
            str.append("<br>");
        }
        assertEquals(str.toString(),todo.checkDone("test4"));
    }
}