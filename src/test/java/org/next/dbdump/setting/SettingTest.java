package org.next.dbdump.setting;

import org.junit.Test;

import static org.junit.Assert.*;

public class SettingTest {

    @Test
    public void testGetDatabase() throws Exception {
        Setting setting = new Setting();
        System.out.println(setting.getPath());
    }
}