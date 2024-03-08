package com.example.crm04.utils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class DateUtils {

    public boolean isValidDate(String dateStr) {
        // Biểu thức chính quy để kiểm tra định dạng ngày tháng
        String regex = "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/(19|20)\\d{2}$";

        // Tạo mẫu biểu thức chính quy
        Pattern pattern = Pattern.compile(regex);

        // Tạo đối tượng matcher cho chuỗi đầu vào
        Matcher matcher = pattern.matcher(dateStr);

        // Kiểm tra xem chuỗi có khớp với biểu thức chính quy không
        return matcher.matches();
    }

    public String changeDate(String dateString){
        DateFormat dateFormatInput = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormatOutput = new SimpleDateFormat("yyyy-MM-dd");

        // Chuyển đổi chuỗi thành đối tượng Date
        Date date = null;
        try {
            date = dateFormatInput.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String newDateString = dateFormatOutput.format(date);


        return newDateString;
    }

    public boolean compareDate(String date1, String date2){
        boolean flag = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate1 = LocalDate.parse(date1, formatter);
        LocalDate localDate2 = LocalDate.parse(date2, formatter);

        if (localDate1.isBefore(localDate2) || localDate1.isEqual(localDate2)) {
            flag = true;
            System.out.println("Ngày 1 lớn hơn ngày 2");
        } else if (localDate1.isAfter(localDate2)) {
            System.out.println("Ngày 1 nhỏ hơn ngày 2");
        }
        return flag;
    }

    public String convertDateFormat(String inputDateStr) {
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yy");
        DateFormat outputFormat = new SimpleDateFormat("yy/MM/dd");

        try {
            Date inputDate = inputFormat.parse(inputDateStr);
            return outputFormat.format(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
