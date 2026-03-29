package universite_Paris8.iut.qdev.tp2026.gr14.services.interfaces;

import java.util.List;

import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.entities.dto.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.CSVInexistantException;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.ChargerFichierException;
import universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions.DonneesCorrompuesException;

public interface IQuestionnaireServices {

    List<QuestionnaireDTO> parcourirQuestionnaireDTO(String path)
            throws ChargerFichierException;

    List<QuestionDTO> chargerFichier(String path)
            throws CSVInexistantException, DonneesCorrompuesException;
}


