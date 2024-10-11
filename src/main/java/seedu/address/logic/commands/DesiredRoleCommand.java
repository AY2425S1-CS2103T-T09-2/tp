package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.DesiredRole;
import seedu.address.model.person.Person;

/**
 * Changes the desired role of an existing person in the address book.
 */
public class DesiredRoleCommand extends Command {

    public static final String COMMAND_WORD = "role";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the desired role of the person identified "
        + "by the index number used in the last person listing. "
        + "Parameters: INDEX (must be a positive integer) "
        + "Desired Role\n"
        + "Example: " + COMMAND_WORD + " 1 Product Manager";

    public static final String MESSAGE_ADD_ROLE_SUCCESS = "Added desired role to Person: %1$s";
    public static final String MESSAGE_ADD_DESIRED_ROLE_SUCCESS = "Added desired role to Person: %1$s";
    public static final String MESSAGE_DELETE_DESIRED_ROLE_SUCCESS = "Removed desired role from Person: %1$s";


    private final Index index;
    private final DesiredRole desiredRole;

    /**
     * Constructs a {@code DesiredRoleCommand} with the specified index and desired role.
     *
     * @param index The index of the person in the filtered person list to edit.
     * @param desiredRole The new desired role to set for the person.
     * @throws NullPointerException If {@code index} or {@code desiredRole} is null.
     */
    public DesiredRoleCommand(Index index, DesiredRole desiredRole) {
        requireAllNonNull(index, desiredRole);

        this.index = index;
        this.desiredRole = desiredRole != null ? desiredRole : new DesiredRole(""); // Handle null case
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException("The person index provided is invalid");
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
            personToEdit.getAddress(), desiredRole, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        // Determine which success message to show
        String successMessage = desiredRole.value.isEmpty()
            ? String.format(MESSAGE_DELETE_DESIRED_ROLE_SUCCESS, editedPerson)
            : String.format(MESSAGE_ADD_DESIRED_ROLE_SUCCESS, editedPerson);

        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DesiredRoleCommand // instanceof handles nulls
            && index.equals(((DesiredRoleCommand) other).index)
            && desiredRole.equals(((DesiredRoleCommand) other).desiredRole));
    }
}
