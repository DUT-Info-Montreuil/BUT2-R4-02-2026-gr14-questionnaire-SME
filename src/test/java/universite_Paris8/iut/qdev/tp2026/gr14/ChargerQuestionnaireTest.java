package universite_Paris8.iut.qdev.tp2026.gr14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mocks.QuestsInexistantsImplMock;
import mocks.QuestsInvalidesImplMock;
import mocks.QuestsOKsImplMock;
import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.CSVInexistantException;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.DonneesCorrompuesException;

public class ChargerQuestionnaireTest {

    @Test
    public void questsOKTest() {
        QuestsOKsImplMock questOK = new QuestsOKsImplMock();

        ArrayList<QuestionDTO> questions = questOK.chargerFichier("src/test/resources/questionsQuizz_2025_V1.csv");

        assertEquals(1, questions.size());
        assertEquals("Sport niv 1", questions.get(0).getLibelleQuestionnaire());
        assertEquals("Tee", questions.get(0).getReponsesQuestion());
    }

    @Test
    public void questsCorrompuTest() {
        QuestsInvalidesImplMock questInv = new QuestsInvalidesImplMock();

        DonneesCorrompuesException exceptionCorrompu = assertThrows(
                DonneesCorrompuesException.class,
                () -> questInv.chargerFichier("src/test/resources/questionsQuizz_test_corrompu_meme_format.csv"));

        assertEquals("Les donnees du questionnaire sont corrompues ou invalides.", exceptionCorrompu.getMessage());
    }

    @Test
    public void questsIncompletTest() {
        QuestsInvalidesImplMock questInv = new QuestsInvalidesImplMock();

        DonneesCorrompuesException exceptionIncomplet = assertThrows(
                DonneesCorrompuesException.class,
                () -> questInv.chargerFichier("src/test/resources/questionsQuizz_test_incomplet_meme_format.csv"));

        assertEquals("Les donnees du questionnaire sont corrompues ou invalides.", exceptionIncomplet.getMessage());
    }

    @Test
    public void questsVideTest() {
        QuestsInvalidesImplMock questInv = new QuestsInvalidesImplMock();

        DonneesCorrompuesException exceptionVide = assertThrows(
                DonneesCorrompuesException.class,
                () -> questInv.chargerFichier("src/test/resources/questionsQuizz_test_vide_meme_format.csv"));

        assertEquals("Les donnees du questionnaire sont corrompues ou invalides.", exceptionVide.getMessage());
    }

    @Test
    public void questsInexistantTest() {
        QuestsInexistantsImplMock questInex = new QuestsInexistantsImplMock();

        CSVInexistantException exception = assertThrows(
                CSVInexistantException.class,
                () -> questInex.chargerFichier("src/test/resources/questionsMirages.csv"));

        assertEquals("Le fichier CSV demande est introuvable.", exception.getMessage());
    }

}
