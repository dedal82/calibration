/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

/**
 *
 * @author vynnyk
 */
enum Act {
    EXIT("exit"),
    ADD_CALIBRATION("addCalibrationAct"),
    EDIT_CALIBRATION("editCalibrationAct"),
    DELETE_CALIBRATION("deleteCalibrationAct"),
    REFRESH("refreshData"),
    TYPE_METERS("typeMetersDictionary");
    
    private String methodName;
    
    Act(String methodName) {
        this.methodName = methodName;
    }

    String getMethodName() {
        return methodName;
    }        
}
