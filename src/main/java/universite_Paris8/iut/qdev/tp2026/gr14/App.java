package universite_Paris8.iut.qdev.tp2026.gr14;

import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.services.interfaces.impl.QuestionnaireServicesImpl;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        QuestionnaireServicesImpl service = new QuestionnaireServicesImpl();

        List<QuestionnaireDTO> questionnaires = service.parcourirQuestionnaireDTO(
                "src/test/resources/questionsQuizz_2025_V1.csv"
        );

        for (QuestionnaireDTO q : questionnaires) {
            System.out.println(q.getLibelleQuestionnaire() + " → " + q.getListeQuestions().size() + " question(s)");
        }
    }
}
