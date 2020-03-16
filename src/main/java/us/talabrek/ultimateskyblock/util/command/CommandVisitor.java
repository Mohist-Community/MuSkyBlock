package us.talabrek.ultimateskyblock.util.command;

/**
 * Simple visitor for the USBCommands
 */
public interface CommandVisitor {
    void visit(Command cmd);
}
