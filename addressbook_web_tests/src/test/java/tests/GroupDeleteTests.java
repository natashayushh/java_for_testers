package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupDeleteTests extends TestBase {
    @Test
    public void CanDeleteGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("Group name", "Group header", "Group footer"));
        }
        app.groups().removeGroup();
    }

}
