package models;

import models.Point;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ToDo {
    public void ToDo() throws FileNotFoundException {
    }

    public HashMap<String, Point> readDo(String name) throws FileNotFoundException {
        File f = new File(name + ".txt");
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/" + name + ".txt"));
            HashMap<String, Point> list = new HashMap<String, Point>();
            while (scanner.hasNext()) {
                String str;
                str = scanner.nextLine();
                Point point = new Point();
                String topic = str.trim();
                str = scanner.nextLine();
                String b = str.trim();
                point.setDate(b);
                str = scanner.nextLine();
                b = str.trim();
                point.setFinalDate(b);
                str = scanner.nextLine();
                b = str.trim();
                point.setStatus(b);
                str = scanner.nextLine();
                b = str.trim();
                point.setTag(b);
                str = scanner.nextLine();
                b = str.trim();
                point.setDescription(b);
                list.put(topic, point);
            }

            return list;
    }

    public void addDo(String name,String finalDate,String task,String topic,String status, String tag) throws IOException {
        HashMap<String,Point> list = readDo(name);
        Point point = new Point();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        Date dateNow = new Date();
        String date = formatForDateNow.format(dateNow);
        point.setFinalDate(finalDate);
        point.setDate(date);
        point.setDescription(task);
        point.setStatus(status);
        point.setTag(tag);
        topic = topic.trim();
        list.put(topic,point);
        Files.write(Paths.get(System.getProperty("user.dir") + "/" + name + ".txt"),
                list.entrySet().stream().map(k->k.getKey() + "\n" +
                        k.getValue().getDate() + "\n" +
                        k.getValue().getFinalDate() + "\n" +
                        k.getValue().getStatus() + "\n" +
                        k.getValue().getTag()+"\n"+
                        k.getValue().getDescription()).collect(Collectors.toList()),
                StandardCharsets.UTF_8);
    }

    public String checkIn(String name) throws FileNotFoundException {
        HashMap<String,Point> list = readDo(name);
        HashMap<String, Integer> tags = new HashMap<String, Integer>();
        StringBuilder str = new StringBuilder();
        Integer i = 0;
        for (Map.Entry<String,Point> entry : list.entrySet()) {
            tags.put(entry.getValue().getTag(), i);
            i++;
        }
        str.append("<div class=\"tab\">\n");
        for (Map.Entry<String,Integer> entry : tags.entrySet()) {
            str.append("<button class=\"tablinks\" id=\"button1"+entry.getValue()+"\" onclick=\"open()\">"+entry.getKey()+"</button>\n");
            str.append("<script > button1"+entry.getValue()+".onclick=function() {" +
                        "document.getElementById(\""+entry.getKey()+"\").style.display = \"block\";");
            for (Map.Entry<String,Integer> entry2 : tags.entrySet()) {
                if(!(entry.getKey().equals(entry2.getKey()))) {
                    str.append("document.getElementById(\""+entry2.getKey()+"\").style.display = \"none\";");
                }
            }
            str.append("};</script>");
        }
        str.append("</div>");
        str.append("\n");
        str.append("\n");
        for(Map.Entry<String,Integer> entry : tags.entrySet()){
            str.append("<div id=\""+entry.getKey()+"\" class=\"tabcontent1\">");
            for (Map.Entry<String,Point> entry17 : list.entrySet()) {
                if ( entry17.getValue().getStatus().equals("in progress")) {
                    if (entry17.getValue().getTag().equals(entry.getKey())) {
                        str.append("<p>" + entry17.getKey() + "<br>");
                        str.append(entry17.getValue().getDate() +"<br>");
                        str.append(entry17.getValue().getStatus() +"<br>");
                        str.append(entry17.getValue().getFinalDate() +"<br>");
                        str.append(entry17.getValue().getDescription() + "</p><br>");
                        str.append("<br>");
                        str.append("<br>");
                    }
                }
            }
            str.append("</div>");
        }

        return str.toString();
    }

    public void setAs(String name, String topic, String status) throws IOException {
        HashMap<String,Point> list = readDo(name);
        Point point = new Point();
        topic = topic.trim();
        for (Map.Entry<String,Point> entry : list.entrySet()) {
            if ( entry.getKey().equals(topic)) {
                point= entry.getValue();
                point.setStatus(status);
                break;
            }
        }
        list.put(topic,point);
        Files.write(Paths.get(System.getProperty("user.dir") + "/" + name + ".txt"),
                list.entrySet().stream().map(k->k.getKey()+"\n"+
                        k.getValue().getDate()+"\n"+
                        k.getValue().getFinalDate()+"\n"+
                        k.getValue().getStatus()+"\n"+
                        k.getValue().getDescription()).collect(Collectors.toList()),
                StandardCharsets.UTF_8);
    }

    public String checkDone(String name) throws FileNotFoundException {
        HashMap<String,Point> list = readDo(name);
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String,Point> entry : list.entrySet()) {
            if ( entry.getValue().getStatus().equals("done")) {
                str.append("<p>" + entry.getKey() + "<br>");
                str.append(entry.getValue().getDate() +"<br>");
                str.append(entry.getValue().getStatus() +"<br>");
                str.append(entry.getValue().getFinalDate() +"<br>");
                str.append(entry.getValue().getDescription() + "</p><br>");
                str.append("<br>");
                str.append("<br>");
            }
        }
        return str.toString();
    }
}
