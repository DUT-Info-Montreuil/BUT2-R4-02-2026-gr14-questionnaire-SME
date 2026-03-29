package universite_Paris8.iut.qdev.tp2026.gr14.entities.dto;

import universite_Paris8.iut.qdev.tp2026.gr14.utils.enums.DifficulteEnum;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.enums.LanguesEnum;

public class QuestionDTO {

    private int id;
    private String libelleQuestionnaire = "";
    private int numeroQuestion;
    private LanguesEnum langue;
    private String libelleQuestion = "";
    private String reponsesQuestion = "";
    private DifficulteEnum difficulte;
    private String explication = "";
    private String reference = "";

    // 1. Constructeur vide (Indispensable pour de nombreux frameworks)
    public QuestionDTO() {
    }

    // 2. Constructeur complet (Très utile pour ton futur CSVService)
    public QuestionDTO(int id, String libelleQuestionnaire, int numeroQuestion, LanguesEnum langue,
                       String libelleQuestion, String reponsesQuestion, DifficulteEnum difficulte,
                       String explication, String reference) {
        this.id = id;
        this.libelleQuestionnaire = libelleQuestionnaire;
        this.numeroQuestion = numeroQuestion;
        this.langue = langue;
        this.libelleQuestion = libelleQuestion;
        this.reponsesQuestion = reponsesQuestion;
        this.difficulte = difficulte;
        this.explication = explication;
        this.reference = reference;
    }

    // Getters et Setters
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

    public int getNumeroQuestion() {
        return numeroQuestion;
    }

    public void setNumeroQuestion(int numeroQuestion) {
        this.numeroQuestion = numeroQuestion;
    }

    public LanguesEnum getLangue() {
        return langue;
    }

    public void setLangue(LanguesEnum langue) {
        this.langue = langue;
    }

    public String getLibelleQuestion() {
        return libelleQuestion;
    }

    public void setLibelleQuestion(String libelleQuestion) {
        this.libelleQuestion = libelleQuestion;
    }

    public String getReponsesQuestion() {
        return reponsesQuestion;
    }

    public void setReponsesQuestion(String reponsesQuestion) {
        this.reponsesQuestion = reponsesQuestion;
    }

    public DifficulteEnum getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(DifficulteEnum difficulte) {
        this.difficulte = difficulte;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    // 3. Ajout d'une méthode toString() pour faciliter le debug dans la console
    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %s",
                numeroQuestion, libelleQuestion, difficulte, langue);
    }
}