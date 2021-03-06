package com.mycompany.safetyAlert.dao;

import com.mycompany.safetyAlert.exceptions.DataAlreadyExistException;
import com.mycompany.safetyAlert.exceptions.DataNotFoundException;
import com.mycompany.safetyAlert.model.Firestation;
import com.mycompany.safetyAlert.model.Medicalrecord;
import com.mycompany.safetyAlert.model.Person;
import com.mycompany.safetyAlert.repository.DataRepository;
import com.mycompany.safetyAlert.serviceDao.IFirestationService;
import com.mycompany.safetyAlert.serviceDao.IMedicalrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalrecordDaoImpl implements MedicalrecordDao {

    @Autowired
    DataRepository dataRepository;

    @Autowired
    IMedicalrecordService medicalrecordService;

    @Override
    public boolean createMedicalrecord(Medicalrecord medicalrecord) {
        // on vérifie que la personne existe
        Person person = new Person();
        person.setLastName(medicalrecord.getLastName());
        person.setFirstName(medicalrecord.getFirstName());

        if (!dataRepository.database.getPersons().contains(person)){
            throw new DataNotFoundException("La personne " + person.getLastName() +" " + person.getFirstName() + " n'existe pas");
        }

        else {
        // On vérifie que l'enregistrement n'existe pas dans la Dao
        if ((!dataRepository.database.getMedicalrecords().contains(medicalrecord))) {
            medicalrecordService.createMedicalrecord(medicalrecord);
            return true;
        } else {
            throw new DataAlreadyExistException("L'enregistrement pour " + medicalrecord.getLastName() + " " + medicalrecord.getFirstName() + " existe déjà.");
        }
    }}

    @Override
    public boolean deleteMedicalrecord(Medicalrecord medicalrecord) {
        if (!medicalrecordService.deleteMedicalrecord(medicalrecord)){
            throw new DataNotFoundException("L'enregistrement pour " + medicalrecord.getLastName() + " " + medicalrecord.getFirstName() + " n'existe pas.");
        }
        return true;
    }

    @Override
    public boolean updateMedicalrecord(Medicalrecord medicalrecord) {
        if (!medicalrecordService.updateMedicalrecord(medicalrecord)) {
            throw new DataNotFoundException("L'enregistrement pour " + medicalrecord.getLastName() + " " + medicalrecord.getFirstName() +  " n'existe pas.");
        }
        return true;
    }
}
