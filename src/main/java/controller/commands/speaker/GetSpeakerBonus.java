package controller.commands.speaker;

import controller.commands.Command;
import model.Speaker;
import services.SpeakerService;
import util.Finals;
import util.Request;
import util.RequestParams;

public class GetSpeakerBonus implements Command {
    private SpeakerService speakerService;

    public GetSpeakerBonus(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }


    @Override
    public void execute(Request request) {
        Speaker speaker = (Speaker) request.getParameteres().get(RequestParams.SPEAKER);

        if (speaker == null) {
            System.out.println("Only speakers can get speaker bonus");
        } else {
            int bonus = speakerService.getSpeakerBonus(speaker);
            System.out.println(speaker.getLogin() + " has bonus " + bonus);
        }
    }
}
