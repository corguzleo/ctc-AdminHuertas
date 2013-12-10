/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.test;

/**
 *
 * @author OSCAR
 */
public enum EmployeeType {
    
    FULL_TIME("Full time"),
    
    PART_TIME("Part time"),
    
    CASUAL("Casual"),
    
    CONTRACTOR("Contractor");
    
    private String label;
    
    EmployeeType(String label) {
        this.label = label;
    }
    
    public String toString() {
        return this.label;
    }
}
