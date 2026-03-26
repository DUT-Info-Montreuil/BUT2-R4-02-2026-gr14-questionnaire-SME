package universite_Paris8.iut.qdev.tp2026.gr14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mocks.QuestsInexistantsImplMock;
import mocks.QuestsInvalidesImplMock;
import mocks.QuestsOKsImplMock;
import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.ChargerFichierException;

public class ParcourirQuestionnaireTest {

    @Test
    public void parcourirQuestionnaireOKTest() {
        QuestsOKsImplMock questOK = new QuestsOKsImplMock();

        ArrayList<QuestionnaireDTO> questionnaires = questOK
                .parcourirQuestionnaireDTO("src/test/resources/questionsQuizz_2025_V1.csv");

        assertEquals(1, questionnaires.size());
        assertEquals("Sport niv 1", questionnaires.get(0).getLibelleQuestionnaire());
        assertEquals(1, questionnaires.get(0).getListeQuestions().size());
    }

    @Test
    public void parcourirQuestionnaireCorrompuTest() {
        QuestsInvalidesImplMock questInv = new QuestsInvalidesImplMock();

        ChargerFichierException exception = assertThrows(
                ChargerFichierException.class,
                () -> questInv.parcourirQuestionnaireDTO("src/test/resources/questionsQuizz_test_corrompu_meme_format.csv"));

        assertEquals("Les donnees du questionnaire sont corrompues ou invalides.", exception.getMessage());
    }

    @Test
    public void parcourirQuestionnaireIncompletTest() {
        QuestsInvalidesImplMock questInv = new QuestsInvalidesImplMock();

        ChargerFichierException exception = assertThrows(
                ChargerFichierException.class,
                () -> questInv.parcourirQuestionnaireDTO("src/test/resources/questionsQuizz_test_incomplet_meme_format.csv"));

        assertEquals("Les donnees du questionnaire sont corrompues ou invalides.", exception.getMessage());
    }

    @Test
    public void parcourirQuestionnaireVideTest() {
        QuestsInvalidesImplMock questInv = new QuestsInvalidesImplMock();

        ChargerFichierException exception = assertThrows(
                ChargerFichierException.class,
                () -> questInv.parcourirQuestionnaireDTO("src/test/resources/questionsQuizz_test_vide_meme_format.csv"));

        assertEquals("Les donnees du questionnaire sont corrompues ou invalides.", exception.getMessage());
    }

    @Test
    public void parcourirQuestionnaireInexistantTest() {
        QuestsInexistantsImplMock questInex = new QuestsInexistantsImplMock();

        ChargerFichierException exception = assertThrows(
                ChargerFichierException.class,
                () -> questInex.parcourirQuestionnaireDTO("src/test/resources/questionsMirages.csv"));

        assertEquals("Le fichier CSV demande est introuvable.", exception.getMessage());
    }
}
