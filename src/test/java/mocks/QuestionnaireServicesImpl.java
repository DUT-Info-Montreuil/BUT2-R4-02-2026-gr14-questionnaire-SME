package universite_Paris8.iut.qdev.tp2026.gr14.services.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.services.interfaces.IQuestionnaireServices;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.enums.DifficulteEnum;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.enums.LanguesEnum;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.CSVInexistantException;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.ChargerFichierException;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.DonneesCorrompuesException;

public class QuestionnaireServicesImpl implements IQuestionnaireServices {

    private static final String SEPARATEUR = ";";

    @Override
    public List<QuestionnaireDTO> parcourirQuestionnaireDTO(String path) throws ChargerFichierException {
        try {
            List<QuestionDTO> toutesLesQuestions = chargerFichier(path);

            // On regroupe les questions par libellé de questionnaire
            Map<String, List<QuestionDTO>> questionsGroupees = toutesLesQuestions.stream()
                    .collect(Collectors.groupingBy(QuestionDTO::getLibelleQuestionnaire));

            List<QuestionnaireDTO> questionnaires = new ArrayList<>();
            int idCompteur = 1;

            for (Map.Entry<String, List<QuestionDTO>> entry : questionsGroupees.entrySet()) {
                QuestionnaireDTO dto = new QuestionnaireDTO();
                dto.setId(idCompteur++);
                dto.setLibelleQuestionnaire(entry.getKey());
                dto.setListeQuestions(entry.getValue());
                questionnaires.add(dto);
            }

            return questionnaires;

        } catch (CSVInexistantException | DonneesCorrompuesException e) {
            // On encapsule l'exception checked dans la RuntimeException comme prévu par ton interface
            throw new ChargerFichierException(e.getMessage());
        }
    }

    @Override
    public List<QuestionDTO> chargerFichier(String path) throws CSVInexistantException, DonneesCorrompuesException {
        List<QuestionDTO> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String ligne;
            boolean premiereLigne = true;

            while ((ligne = br.readLine()) != null) {
                if (premiereLigne) { // Sauter l'entête du CSV
                    premiereLigne = false;
                    continue;
                }
                if (ligne.trim().isEmpty()) continue;

                questions.add(parserLigneEnQuestion(ligne));
            }
        } catch (IOException e) {
            throw new CSVInexistantException("Impossible d'accéder au fichier : " + path);
        }

        if (questions.isEmpty()) {
            throw new DonneesCorrompuesException("Le fichier est vide ou ne contient aucune donnée valide.");
        }

        return questions;
    }

    private QuestionDTO parserLigneEnQuestion(String ligne) throws DonneesCorrompuesException {
        String[] colonnes = ligne.split(SEPARATEUR);

        // On vérifie que la ligne a assez de colonnes (ajuste le nombre selon ton format CSV réel)
        if (colonnes.length < 9) {
            throw new DonneesCorrompuesException("Ligne corrompue (colonnes manquantes) : " + ligne);
        }

        try {
            QuestionDTO q = new QuestionDTO();
            q.setId(Integer.parseInt(colonnes[0].trim()));
            q.setLibelleQuestionnaire(colonnes[1].trim());
            q.setNumeroQuestion(Integer.parseInt(colonnes[2].trim()));
            q.setLangue(LanguesEnum.valueOf(colonnes[3].trim())); // Attention : doit être 'fr' ou 'en'
            q.setLibelleQuestion(colonnes[4].trim());
            q.setReponsesQuestion(colonnes[5].trim());
            q.setDifficulte(DifficulteEnum.valueOf(colonnes[6].trim().toUpperCase()));
            q.setExplication(colonnes[7].trim());
            q.setReference(colonnes[8].trim());
            return q;
        } catch (Exception e) {
            throw new DonneesCorrompuesException("Erreur de formatage sur la ligne : " + ligne);
        }
    }
}