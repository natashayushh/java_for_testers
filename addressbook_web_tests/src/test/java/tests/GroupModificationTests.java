package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Target;

public class GroupModificationTests extends TestBase{

    @Test
    void canModifyGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("Group name", "Group header", "Group footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }

}
