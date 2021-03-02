package csci2020u.lab05;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentRecord {
    private final StringProperty SID = new SimpleStringProperty();
    private final FloatProperty Assignment = new SimpleFloatProperty();
    private final FloatProperty midTerm = new SimpleFloatProperty();
    private final FloatProperty finalExam = new SimpleFloatProperty();
    private final FloatProperty finalMark = new SimpleFloatProperty();
    private final StringProperty letterGrade =  new SimpleStringProperty();

    public StudentRecord(){
    }

	public StudentRecord(String SID, float assignment, float midterm, float finalexam) {
        this.SID.set(SID);
        this.Assignment.set(assignment);
        this.midTerm.set(midterm);
        this.finalExam.set(finalexam);
        this.finalMark.set(calculateFinalMark());
        this.letterGrade.set(calculateLetterGrade());
    }

    public final String getSID() {
        return this.SID.get();
    }

    public void setSID(String SID) {
        this.SID.set(SID);
    }

    public final float getAssignment() {
        return this.Assignment.get();
    }

    public void setAssignment(float assignment) {
        this.Assignment.set(assignment);
    }

    public final float getMidTerm() {
        return this.midTerm.get();
    }

    public  void setMidTerm(float midterm) {
        this.midTerm.set(midterm);
    }

    public final float getFinalExam() {
        return this.finalExam.get();
    }

    public void setFinalExam(float finalexam) {
        this.finalExam.set(finalexam);
    }

    public final float getFinalMark() {
        return this.finalMark.get();
    }

    public void setFinalMark(float fMark) {
        this.finalMark.set(fMark);
    }

    public final String getLetterGrade() {
        return this.letterGrade.get();
    }

    public void setLetterGrade(String grade) {
        this.letterGrade.set(grade);
    }

    public float calculateFinalMark() {
        return ((this.Assignment.get() * 0.2f) + (this.midTerm.get() * 0.3f) + (this.finalExam.get() * 0.5f));
    }

    public String calculateLetterGrade() {
        if(this.finalMark.get() >= 80.0f){
            return "A";
        } else if(this.finalMark.get() >= 70.0f && this.finalMark.get() < 80.0f) {
            return "B";
        } else if(this.finalMark.get() >= 60.0f && this.finalMark.get() < 70.0f) {
            return "C";
        } else if(this.finalMark.get() >= 50.0f && this.finalMark.get() < 60.0f) {
            return "D";
        } else {
            return "F";
        }
    }
}
