package br.com.cleonildo.infra;

import br.com.cleonildo.command.BaseCommand;
import br.com.cleonildo.command.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
