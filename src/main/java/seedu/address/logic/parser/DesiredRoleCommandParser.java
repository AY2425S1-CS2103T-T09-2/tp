package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESIREDROLE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DesiredRoleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DesiredRole;

/**
 * Parses input arguments and creates a new {@code DesiredRoleCommand} object
 */
public class DesiredRoleCommandParser implements Parser<DesiredRoleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DesiredRoleCommand}
     * and returns a {@code DesiredRoleCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DesiredRoleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESIREDROLE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DesiredRoleCommand.MESSAGE_USAGE), ive);
        }

        String desiredRole = argMultimap.getValue(PREFIX_DESIREDROLE).orElse("");

        return new DesiredRoleCommand(index, new DesiredRole(desiredRole));
    }
}
