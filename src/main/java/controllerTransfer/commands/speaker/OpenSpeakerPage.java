package controllerTransfer.commands.speaker;

import controllerTransfer.commands.Command;
import util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenSpeakerPage implements Command {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(Pages.SPEAKER_PAGE).forward(request, response);
  }
}
