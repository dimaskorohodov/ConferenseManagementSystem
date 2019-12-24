package controller;

import controller.commands.Command;
import controller.commands.administrator.SeeReportsInfo;
import controller.commands.moderator.ChangeReport;
import controller.commands.speaker.GetSpeakerBonus;
import controller.commands.user.LoginUser;
import controller.commands.user.RegistrateOnReport;
import controller.commands.user.VisitReport;
import services.ModeratorService;
import services.ReportService;
import services.SpeakerService;
import services.UserService;
import util.Request;
import util.RequestParams;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {


    private Map<String, Command> commands = new HashMap<>();


    public CommandManager(UserService userService, ReportService reportService, ModeratorService moderatorService, SpeakerService speakerService) {
        LoginUser loginUser = new LoginUser(userService);
        SeeReportsInfo seeReportsInfo = new SeeReportsInfo(reportService);
        ChangeReport changeReport = new ChangeReport(moderatorService);
        GetSpeakerBonus getSpeakerBonus = new GetSpeakerBonus(speakerService);
        RegistrateOnReport registrateOnReport = new RegistrateOnReport(userService);
        VisitReport visitReport = new VisitReport(userService);

        commands.put(RequestParams.LOGIN_USER, loginUser);
        commands.put(RequestParams.SEE_INFO, seeReportsInfo);
        commands.put(RequestParams.CHANGE_REPORT, changeReport);
        commands.put(RequestParams.GET_BONUS, getSpeakerBonus);
        commands.put(RequestParams.REGISTRATE, registrateOnReport);
        commands.put(RequestParams.VISIT, visitReport);
    }


    public void perform(Request request) {
        Command command = commands.get(request.getName());
        command.execute(request);
    }
}
