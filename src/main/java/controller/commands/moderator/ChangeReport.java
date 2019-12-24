package controller.commands.moderator;

import controller.commands.Command;
import model.Report;
import model.User;
import model.enums.UserRole;
import services.ModeratorService;
import util.Request;
import util.RequestParams;

public class ChangeReport implements Command {

    private ModeratorService moderatorService;

    public ChangeReport(ModeratorService moderatorServiceж) {
        this.moderatorService = moderatorServiceж;
    }

    @Override
    public void execute(Request request) {
        User user = (User) request.getParameteres().get(RequestParams.USER);
        if (user.getUserRole().equals(UserRole.MODERATOR)) {
            moderatorService.changeReportInfo((Report) request.getParameteres().get(RequestParams.REPORT), (String) request.getParameteres().get(RequestParams.THEME));
        } else {
            System.out.println("Functionality only for moderators");
        }
    }
}
