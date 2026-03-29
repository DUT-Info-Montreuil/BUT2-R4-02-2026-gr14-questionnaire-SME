package mocks;

import java.util.ArrayList;

import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.services.interfaces.IQuestionnaireServices;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.ChargerFichierException;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.DonneesCorrompuesException;

public class QuestsInvalidesImplMock implements IQuestionnaireServices {

    private static final String MESSAGE = "Les donnees du questionnaire sont corrompues ou invalides.";

    @Override
    public ArrayList<QuestionnaireDTO> parcourirQuestionnaireDTO(String chemin) {
        throw new ChargerFichierException(MESSAGE);
    }

    @Override
    public ArrayList<QuestionDTO> chargerFichier(String chemin) throws DonneesCorrompuesException {
        throw new DonneesCorrompuesException(MESSAGE);
    }
}
