

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class T31 {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "cp866");
    System.out.println("Введите данные через пробел в формате: 1.Фамилия 2.Имя 3.Отчество 4.Дата рождения 5.Номер телефона 6. Пол : ");
    String input = scanner.nextLine();

    String[] Data = input.split("\\s+");

    if (Data.length != 6) {
        if (Data.length < 6) {
            System.out.println("Вы ввели меньше данных, чем требуется.");
        }
        else{
            System.out.println("Вы ввели больше данных, чем требуется.");
        }
    System.exit(1);
    }

    String Famil = Data[0];
    String Name = Data[1];
    String Othestvo = Data[2];
    String birthDate = Data[3];
    String phoneNumber = Data[4];
    String gender = Data[5];
    scanner.close();

    try {
        LocalDate.parse(birthDate, UserDataFormatter.BIRTH_DATE_FORMAT);
        Long.parseLong(phoneNumber);
    if (!gender.equals("f") && !gender.equals("m")) {
        throw new InvalidGenderException("Неверный пол. Допустимые значения: 'f-female' или 'm-male'");
        }
    }

    catch (DateTimeParseException e) {
        System.out.println("Неверный формат даты рождения. Ожидается формат: 'dd.mm.yyyy'.");
        System.exit(1);
    }

    catch (NumberFormatException e) {
        System.out.println("Неверный формат номера телефона. Ожидается целое беззнаковое число.");
        System.exit(1);
    }

    catch (InvalidGenderException e) {
        System.out.println(e.getMessage());
        System.exit(1);
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(Famil + ".txt", true))) {
        writer.write(Famil + " " + Name + " " + Othestvo + " " + birthDate + " " + phoneNumber + " " + gender);
        writer.newLine();
    }
    
    catch (IOException e) {
        System.out.println("Ошибка при чтении/записи файла:");
        e.printStackTrace();
        }
    }

    private static class InvalidGenderException extends Exception {
    public InvalidGenderException(String message) {
        super(message);
        }
    }

    private static class UserDataFormatter {
        public static final String BIRTH_DATE_PATTERN = "dd.MM.yyyy";
        public static final DateTimeFormatter BIRTH_DATE_FORMAT = DateTimeFormatter.ofPattern(BIRTH_DATE_PATTERN);
    }
}