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
    ADD_CALIBRATION("exit"),
    EDIT_CALIBRATION("exit"),
    DELETE_CALIBRATION("exit"),
    REFRESH("refreshData");
    
    private String methodName;
    
    Act(String methodName) {
        this.methodName = methodName;
    }

    String getMethodName() {
        return methodName;
    }        
}
