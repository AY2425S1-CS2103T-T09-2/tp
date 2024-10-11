package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DesiredRoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DesiredRole(null));
    }

    @Test
    public void isValidDesiredRole() {
        // null desired role
        assertThrows(NullPointerException.class, () -> DesiredRole.isValidDesiredRole(null));

        // blank desired role
        assertFalse(DesiredRole.isValidDesiredRole("")); // empty string
        assertFalse(DesiredRole.isValidDesiredRole(" ")); // spaces only

        // invalid desired role (e.g. too short, special characters, etc.)
        assertFalse(DesiredRole.isValidDesiredRole("12")); // too short
        assertFalse(DesiredRole.isValidDesiredRole("Software@Engineer")); // special character

        // valid desired role
        assertTrue(DesiredRole.isValidDesiredRole("Software Engineer"));
        assertTrue(DesiredRole.isValidDesiredRole("Data Scientist"));
        assertTrue(DesiredRole.isValidDesiredRole("Project Manager"));
        assertTrue(DesiredRole.isValidDesiredRole("Software Developer"));
    }

    @Test
    public void equals() {
        DesiredRole desiredRole = new DesiredRole("Software Engineer");

        // same values -> returns true
        assertTrue(desiredRole.equals(new DesiredRole("Software Engineer")));

        // same object -> returns true
        assertTrue(desiredRole.equals(desiredRole));

        // null -> returns false
        assertFalse(desiredRole.equals(null));

        // different types -> returns false
        assertFalse(desiredRole.equals(5.0f));

        // different values -> returns false
        assertFalse(desiredRole.equals(new DesiredRole("Data Scientist")));
    }
}
