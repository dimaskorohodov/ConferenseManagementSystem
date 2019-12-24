package services;

import java.util.Scanner;

public class UserInputService {

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
