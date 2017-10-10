package ua.annalonskaya.tests;

import org.junit.After;
import org.junit.Before;
import ua.annalonskaya.appmanager.ApplicationManager;

public class BaseTest {

    protected final ApplicationManager app = new ApplicationManager();

    @Before
    public void setUp(){
        app.init();
    }

    @After
    public void tearDown() {
        app.stop();
    }

}
