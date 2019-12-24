package controller.commands;

import util.Request;

public interface Command {

    void execute(Request request);
}
