package mocks;

import java.util.ArrayList;

import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.services.interfaces.IQuestionnaireServices;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.enums.DifficulteEnum;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.enums.LanguesEnum;

public class QuestsOKsImplMock implements IQuestionnaireServices {

    @Override
    public ArrayList<QuestionnaireDTO> parcourirQuestionnaireDTO(String chemin) {
        ArrayList<QuestionDTO> questions = chargerFichier(chemin);
        QuestionnaireDTO questionnaire = new QuestionnaireDTO();
        questionnaire.setId(1);
        questionnaire.setLibelleQuestionnaire("Sport niv 1");
        questionnaire.setListeQuestions(questions);

        ArrayList<QuestionnaireDTO> questionnaires = new ArrayList<>();
        questionnaires.add(questionnaire);
        return questionnaires;
    }

    @Override
    public ArrayList<QuestionDTO> chargerFichier(String chemin) {
        QuestionDTO question = new QuestionDTO();
        question.setId(1);
        question.setLibelleQuestionnaire("Sport niv 1");
        question.setNumeroQuestion(1);
        question.setLangue(LanguesEnum.fr);
        question.setLibelleQuestion(
                "De quel petit objet se munit le golfeur pour surelever sa balle avant de la frapper ?");
        question.setReponsesQuestion("Tee");
        question.setDifficulte(DifficulteEnum.FACILE);
        question.setExplication(
                "Le joueur peut poser sa balle sur une cheville de bois ou de plastique.");
        question.setReference("https://fr.wikipedia.org/wiki/Materiel_de_golf");

        ArrayList<QuestionDTO> questions = new ArrayList<>();
        questions.add(question);
        return questions;
    }
}
