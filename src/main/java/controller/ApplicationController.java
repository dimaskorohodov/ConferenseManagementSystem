package controller;

import model.Report;
import persiatance.dao.sql.RatingSqlDao;
import persiatance.dao.sql.ReportSqlDao;
import persiatance.dao.sql.ReportStatisticSqlDao;
import persiatance.dao.sql.UserSqlDao;
import persiatance.dbcp.ConnectionManager;
import services.*;
import util.Request;
import util.RequestParams;
import view.ApplicationView;

import java.sql.Date;

public class ApplicationController {
    private Request request;
    private CommandManager commandManager;


    public ApplicationController() {
        request = new Request();

        ConnectionManager connectionManager = new ConnectionManager();
        RatingSqlDao ratingSqlDao = new RatingSqlDao(connectionManager);
        UserSqlDao userSqlDao = new UserSqlDao(connectionManager, ratingSqlDao);
        ReportStatisticSqlDao reportStatisticSqlDao = new ReportStatisticSqlDao(connectionManager, userSqlDao);
        ReportSqlDao reportSqlDao = new ReportSqlDao(connectionManager, userSqlDao, reportStatisticSqlDao);

        UserService userService = new UserService(userSqlDao, reportSqlDao, reportStatisticSqlDao);
        ReportService reportService = new ReportService(reportSqlDao);
        ModeratorService moderatorService = new ModeratorService(reportSqlDao);
        SpeakerService speakerService = new SpeakerService();
        commandManager = new CommandManager(userService, reportService, moderatorService,speakerService);
    }


    public void menu() {
        while (true) {
            ApplicationView.printMenu();
            String choise = UserInputService.getUserInput();

            switch (choise) {
                case "1": {
                    System.out.println("Enter login:");
                    String login = UserInputService.getUserInput();
                    System.out.println("Enter password:");
                    String password = UserInputService.getUserInput();
                    request.setName(RequestParams.LOGIN_USER);
                    request.getParameteres().put(RequestParams.LOGIN, login);
                    request.getParameteres().put(RequestParams.PASSWORD, password);
                    commandManager.perform(request);
                    break;
                }
                case "2": {
                    request.setName(RequestParams.SEE_INFO);
                    commandManager.perform(request);
                    break;
                }
                case "3": {
                    System.out.println("Enter report theme to change this report");
                    String oldTheme = UserInputService.getUserInput();
                    System.out.println("Enter new report theme:");
                    String theme = UserInputService.getUserInput();
                    System.out.println("Enter new report place:");
                    String place = UserInputService.getUserInput();
                    System.out.println("Enter new report date:");
                    Date date = Date.valueOf(UserInputService.getUserInput());
                    Report report = new Report(theme, place, date, null, null, null);

                    request.setName(RequestParams.CHANGE_REPORT);
                    request.getParameteres().put(RequestParams.REPORT, report);
                    request.getParameteres().put(RequestParams.THEME, oldTheme);
                    commandManager.perform(request);
                    break;
                }
                case "4": {
                    System.out.println("Enter report theme to registrate!");
                    String theme = UserInputService.getUserInput();
                    request.setName(RequestParams.REGISTRATE);
                    request.getParameteres().put(RequestParams.THEME, theme);
                    commandManager.perform(request);
                    break;
                }
                case "5": {
                    System.out.println("Enter report theme to visit");
                    String theme = UserInputService.getUserInput();
                    request.setName(RequestParams.VISIT);
                    request.getParameteres().put(RequestParams.THEME, theme);
                    commandManager.perform(request);
                    break;
                }
                case "6": {
                    request.setName(RequestParams.GET_BONUS);
                    commandManager.perform(request);
                    break;
                }
                case "7": {
                    if (request.getParameteres().containsKey(RequestParams.USER)) {
                        request.getParameteres().remove(RequestParams.USER);
                    }
                    if (request.getParameteres().containsKey(RequestParams.SPEAKER)) {
                        request.getParameteres().remove(RequestParams.SPEAKER);
                    }
                    break;
                }
            }
        }
    }
}
