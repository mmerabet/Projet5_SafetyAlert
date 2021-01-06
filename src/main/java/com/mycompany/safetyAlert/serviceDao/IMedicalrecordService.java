package com.mycompany.safetyAlert.serviceDao;

import com.mycompany.safetyAlert.model.Medicalrecord;

public interface IMedicalrecordService {
    boolean createMedicalrecord (Medicalrecord medicalrecord);
    boolean deleteMedicalrecord (Medicalrecord medicalrecord);
    boolean updateMedicalrecord (Medicalrecord medicalrecord);
}
