package models;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void setDate() {
        Point point = new Point();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(" dd.MM.yyyy ");
        Date dateNow = new Date();
        String date = formatForDateNow.format(dateNow);
        point.setDate(date);
        String expected = formatForDateNow.format(dateNow);
        assertEquals(expected,point.getDate());
    }

    @Test
    void setDescription() {
        Point point = new Point();
        String description = "description";
        point.setDescription(description);
        String expected = "description";
        assertEquals(expected,point.getDescription());
    }

    @Test
    void setFinalDate() {
        Point point = new Point();
        String finalDate = "11.02.2020";
        point.setDescription(finalDate);
        String expected = "11.02.2020";
        assertEquals(expected,point.getFinalDate());
    }


    @Test
    void setStatus() {
        Point point = new Point();
        String status = "in progress";
        point.setStatus(status);
        String expected = "in progress";
        assertEquals(expected,point.getStatus());
    }
}