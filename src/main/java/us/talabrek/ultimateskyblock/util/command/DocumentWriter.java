package us.talabrek.ultimateskyblock.util.command;

import java.io.PrintStream;

/**
 * Common interface for the DocumentCommand-visitors
 */
public interface DocumentWriter extends CommandVisitor {
    void writeTo(PrintStream ps);
}
