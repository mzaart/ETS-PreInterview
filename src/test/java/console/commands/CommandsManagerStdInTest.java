package console.commands;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;


public class CommandsManagerStdInTest {

    @Test
    public void nextCommand() {
        CommandsManager.Command[] cmds = CommandsManager.Command.values();

        // simulate user input
        StringBuilder mockInput = new StringBuilder();
        for (CommandsManager.Command c : cmds) {
            mockInput.append(c.name())
                    .append('\n').append(c.name().toLowerCase()).append('\n');
        }
        System.setIn(new ByteArrayInputStream(mockInput.toString().getBytes()));

        CommandsManagerStdIn stdCmds = new CommandsManagerStdIn();
        for (CommandsManager.Command c : cmds) {
            assertEquals(c, stdCmds.nextCommand()); // upper case input
            assertEquals(c, stdCmds.nextCommand()); // lower case input
        }
    }

    @Test
    public void invalidCommands() {
        int numCmds = 4;
        String mockInput = " \nthis is invalid\n\nc";
        System.setIn(new ByteArrayInputStream(mockInput.getBytes()));

        CommandsManagerStdIn stdCmds = new CommandsManagerStdIn();
        for (int i = 1; i <= numCmds; i++) {
            assertEquals(CommandsManager.Command.INVALID, stdCmds.nextCommand());
        }
    }

}