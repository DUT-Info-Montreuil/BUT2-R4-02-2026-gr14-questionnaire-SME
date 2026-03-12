package universite_Paris8.iut.qdev.tp2026.gr14.entities.dto;

import java.util.List;

public class QuestionnaireDTO {

    private int id;
    private String libelleQuestionnaire;
    private List<QuestionDTO> listeQuestions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelleQuestionnaire() {
        return libelleQuestionnaire;
    }

    public void setLibelleQuestionnaire(String libelleQuestionnaire) {
        this.libelleQuestionnaire = libelleQuestionnaire;
    }

    public List<QuestionDTO> getListeQuestions() {
        return listeQuestions;
    }

    public void setListeQuestions(List<QuestionDTO> listeQuestions) {
        this.listeQuestions = listeQuestions;
    }
}
