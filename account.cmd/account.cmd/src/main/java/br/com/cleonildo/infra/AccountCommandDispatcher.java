package br.com.cleonildo.infra;

import br.com.cleonildo.command.BaseCommand;
import br.com.cleonildo.command.CommandHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for dispatching commands to the appropriate handlers.
 * It uses a map to store the handlers for each command type, and it looks up the handlers
 * based on the command type. It ensures that only one handler is invoked for a given command.
 */
@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    /**
     * A map that stores the handlers for each command type. The key is the command type, and the value
     * is a list of handlers for that command type.
     */
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    /**
     * Registers a handler for a given command type. If a handler for the given command type already exists,
     * it is added to the list of handlers for that command type.
     *
     * @param type the command type
     * @param handler the handler
     */
    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        List<CommandHandlerMethod> handlers = this.routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    /**
     * Dispatches a command to the appropriate handler. It looks up the handlers for the given command type,
     * and ensures that only one handler is invoked. If no handlers are registered for the given command type,
     * or if multiple handlers are registered, an exception is thrown.
     *
     * @param command the command to dispatch
     */
    @Override
    public void send(BaseCommand command) {
        List<CommandHandlerMethod> handlers = this.routes.get(command.getClass());

        if (handlers == null || handlers.isEmpty()) {
            throw new RuntimeException("No command handler was registered");
        }

        if (handlers.size() > 1) {
            throw new RuntimeException("Cannot send command to more than one handler");
        }

        handlers.get(0).handle(command);
    }
}
