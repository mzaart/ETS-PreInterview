package console.commands;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MockCommandsManager extends CommandsManager {

    private Queue<Command> commands;

    public MockCommandsManager(Command... commands) {
        this.commands = new LinkedList<>(Arrays.asList(commands));
    }

    @Override
    @NotNull
    public Command nextCommand() {
        if (commands.isEmpty()) {
            return Command.EXIT;
        }
        return commands.remove();
    }
    
    public void addCommand(@NotNull Command command) {
        commands.add(command);
    }
}
