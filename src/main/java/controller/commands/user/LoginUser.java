package controller.commands.user;

import controller.commands.Command;
import model.Speaker;
import model.User;
import services.UserService;
import util.Request;
import util.RequestParams;

public class LoginUser implements Command {
    private UserService userService;

    public LoginUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(Request request) {
        User user = new User();
         user = userService.loginUser(request.getParameteres().get(RequestParams.LOGIN).toString(), request.getParameteres().get(RequestParams.PASSWORD).toString());
         if (user!=null){
             request.getParameteres().put(RequestParams.USER,user);
         }
         else {
             Speaker speaker = new Speaker();
             speaker = userService.loginSpeaker(request.getParameteres().get(RequestParams.LOGIN).toString(), request.getParameteres().get(RequestParams.PASSWORD).toString());
             if (speaker != null) {
                 request.getParameteres().put(RequestParams.SPEAKER, speaker);
                 System.out.println(speaker.getRating());
             }
         }
    }
}
