package mocks;

import java.util.ArrayList;

import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.services.interfaces.IQuestionnaireServices;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.CSVInexistantException;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.ChargerFichierException;

public class QuestsInexistantsImplMock implements IQuestionnaireServices {

    private static final String MESSAGE = "Le fichier CSV demande est introuvable.";

    @Override
    public ArrayList<QuestionnaireDTO> parcourirQuestionnaireDTO(String chemin) {
        throw new ChargerFichierException(MESSAGE);
    }

    @Override
    public ArrayList<QuestionDTO> chargerFichier(String chemin) throws CSVInexistantException {
        throw new CSVInexistantException(MESSAGE);
    }
}
