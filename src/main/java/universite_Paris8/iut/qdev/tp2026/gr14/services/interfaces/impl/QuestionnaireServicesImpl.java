package universite_Paris8.iut.qdev.tp2026.gr14.services.interfaces.impl;



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
        public List<QuestionnaireDTO> parcourirQuestionnaireDTO(String path)
                throws ChargerFichierException {
            try {
                List<QuestionDTO> toutesLesQuestions = chargerFichier(path);

                // Regrouper les questions par libellé de questionnaire
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
                // On wrap les checked exceptions en RuntimeException comme prévu par l'interface
                throw new ChargerFichierException(e.getMessage());
            }
        }

        @Override
        public List<QuestionDTO> chargerFichier(String path)
                throws CSVInexistantException, DonneesCorrompuesException {

            List<QuestionDTO> questions = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String ligne;
                boolean premiereLigne = true;

                while ((ligne = br.readLine()) != null) {
                    // Sauter la ligne d'en-tête si elle existe
                    if (premiereLigne) {
                        premiereLigne = false;
                        // Si la première ligne ne commence pas par un chiffre, c'est un header
                        if (!ligne.trim().isEmpty() && !Character.isDigit(ligne.trim().charAt(0))) {
                            continue;
                        }
                    }

                    if (ligne.trim().isEmpty()) continue;

                    questions.add(parserLigneEnQuestion(ligne));
                }

            } catch (IOException e) {
                throw new CSVInexistantException("Le fichier CSV demande est introuvable.");
            }

            if (questions.isEmpty()) {
                throw new DonneesCorrompuesException("Les donnees du questionnaire sont corrompues ou invalides.");
            }

            return questions;
        }

        /**
         * Parse une ligne CSV en QuestionDTO.
         * Colonnes : id ; libelleQuestionnaire ; numeroQuestion ; langue ;
         *            libelleQuestion ; reponsesQuestion ; difficulte ; explication ; reference
         *
         * @throws DonneesCorrompuesException si la ligne est trop courte ou mal formatée
         */
        private QuestionDTO parserLigneEnQuestion(String ligne) throws DonneesCorrompuesException {
            String[] colonnes = ligne.split(SEPARATEUR);

            if (colonnes.length < 9) {
                throw new DonneesCorrompuesException("Les donnees du questionnaire sont corrompues ou invalides.");
            }

            try {
                QuestionDTO q = new QuestionDTO();
                q.setId(Integer.parseInt(colonnes[0].trim()));
                q.setLibelleQuestionnaire(colonnes[1].trim());
                q.setNumeroQuestion(Integer.parseInt(colonnes[2].trim()));
                q.setLangue(LanguesEnum.valueOf(colonnes[3].trim()));
                q.setLibelleQuestion(colonnes[4].trim());
                q.setReponsesQuestion(colonnes[5].trim());
                // Le CSV stocke la difficulté en int (1,2,3) → on mappe vers l'enum
                q.setDifficulte(parseDifficulte(colonnes[6].trim()));
                q.setExplication(colonnes[7].trim());
                q.setReference(colonnes[8].trim());
                return q;

            } catch (Exception e) {
                throw new DonneesCorrompuesException("Les donnees du questionnaire sont corrompues ou invalides.");
            }
        }


        private DifficulteEnum parseDifficulte(String valeur) {
            switch (valeur) {
                case "1": return DifficulteEnum.DEBUTANT;
                case "2": return DifficulteEnum.INTERMEDIAIRE;
                case "3": return DifficulteEnum.EXPERT;
                default:  return DifficulteEnum.valueOf(valeur.toUpperCase());
            }
        }
    }

