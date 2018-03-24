package fr.ua.heugue_ydee.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import fr.ua.heugue_ydee.model.DefiScore;
import fr.ua.heugue_ydee.model.IdentifierFoundException;
import fr.ua.heugue_ydee.model.Score;
import fr.ua.heugue_ydee.model.SprintScore;
import fr.ua.heugue_ydee.utils.DBAdapter;

/**
 *
 */
public class EndDialog extends Dialog {

    private boolean hidden;
    private Score score;

    private TextField usernameTextField;
    private DBAdapter db;
    private int difficulty;
    private BitmapFont font;

    private static final String DIALOG_TITLE = "Game Over";
    private static final String VALID_TEXT_BUTTON = "Save & quit";
    private static final String DIALOG_SKIN_PATH = "skin/flat-earth/skin/flat-earth-ui.json";
    private static final String SCORE_PREFIX = "Score: ";

    public EndDialog(DBAdapter db, int difficulty) {
        super(DIALOG_TITLE, new Skin(Gdx.files.internal(DIALOG_SKIN_PATH)));
        this.db = db;
        this.difficulty = difficulty;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(ScoreDisplayerStrategy.FONT_LOCATION));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = ScoreDisplayerStrategy.FONT_SIZE * 3;
        this.font = generator.generateFont(parameter);

        this.button(VALID_TEXT_BUTTON);
        this.usernameTextField = new TextField("", this.getSkin());
        scaleBy(5);
    }

    public void addContent() {
        this.text(SCORE_PREFIX + this.score.getScoreValue());
        this.getContentTable().row();
        this.text("Username: ");
        this.getContentTable().add(usernameTextField).width(100);
    }

    @Override
    protected void result(Object object) {
        String username = this.usernameTextField.getText();
        score.setName(username);

        if (score instanceof SprintScore) {
            SprintScore sprintScore = (SprintScore) score;
            try {
                this.db.addScoreSprint(sprintScore.getName(), sprintScore.getDuration(), difficulty);
            } catch (IdentifierFoundException e) {
            }
        } else {
            DefiScore defiScore = (DefiScore) score;
            try {
                this.db.addScoreDefi(defiScore.getName(), defiScore.getScore(), difficulty);
            } catch (IdentifierFoundException e) {
            }
        }

        Gdx.app.exit();
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
