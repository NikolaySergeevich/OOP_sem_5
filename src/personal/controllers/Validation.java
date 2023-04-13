package personal.controllers;

import personal.model.User;

import java.util.regex.Pattern;

public class Validation {
    /**
     * Проврка что бы user не был пустой
     * @param user
     */
     public void Validate(User user){
         if(!pattern.matcher(user.getFirstName()).find()){
            throw new RuntimeException("Имя поьзователя не должно быть пустым");
         }
         if (!pattern.matcher(user.getLastName()).find()){
             throw new RuntimeException("Фамилия поьзователя не должно быть пустым");
         }
         if(!patternDigit.matcher(user.getPhone()).find()){
             throw new RuntimeException("Номер поьзователя должен содержать только цифры");
         }
     }
     final  String regex = "^\\S+$";//означает, что не допустит пустой строки и пробелов
     final String reDigit = "^\\d+$";//означает, что должны быть только цифры
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);//поле
    final Pattern patternDigit = Pattern.compile(reDigit);
}
