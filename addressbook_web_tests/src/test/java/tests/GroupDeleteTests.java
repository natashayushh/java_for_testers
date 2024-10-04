package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupDeleteTests extends TestBase {
    @Test
    public void CanDeleteGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("Group name", "Group header", "Group footer"));
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }
    @Test
    void CanDeleteAllGroupsAtOnce() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("Group name", "Group header", "Group footer"));
        }
        app.groups().removaAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}
