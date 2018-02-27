package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.HighScoreDBHelper;

/**
 * Created by Utilisateur on 27/02/2018.
 */

public class HighScoreDBHelperTest {

    private HighScoreDBHelper helperDataBase = null;

    @Before
    public void createTablesDataBase(){
        helperDataBase = new HighScoreDBHelper(null);
    }

    @After
    public void deleteTablesDatabase(){
        helperDataBase.close();
    }

    @Test
    public void


}
