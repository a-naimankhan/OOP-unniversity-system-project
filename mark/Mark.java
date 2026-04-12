package mark;

import java.io.Serializable;

public class Mark implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private double firstAttestation;
	private double secondAttestation;
	private double examMark;
	private double finalAttestation;
	private AttestationType period;
	
	public Mark() {
		this.firstAttestation = 0;
		this.secondAttestation = 0;
		this.finalAttestation = 0;
	}
	public Mark(double currentMark, AttestationType period) {
		this.period = period;
		if (this.period == AttestationType.FIRST)
			this.firstAttestation = currentMark;
		else if (this.period == AttestationType.SECOND)
			this.secondAttestation = currentMark;
		else
			this.examMark = currentMark;
	}
	
	public void setMark(double currentMark) {
		if (this.period == AttestationType.FIRST) {
			this.setFirstAttestation(this.getFirstAttestation() + currentMark);
			if (this.getFirstAttestation() > 30) this.setFirstAttestation(30);
		}
		else if (this.period == AttestationType.SECOND) {
			this.setSecondAttestation(getSecondAttestation() + currentMark);
			if (this.getSecondAttestation() > 30) this.setSecondAttestation(30);
		}
		else this.setExamMark(currentMark);
	}

	
	public double getFirstAttestation() {
		return firstAttestation;
	}
	public double getSecondAttestation() {
		return secondAttestation;
	}
	public double getExamMark() {
		return examMark;
	}
	public void setFirstAttestation(double firstAttestation) {
		this.firstAttestation = firstAttestation;
	}
	public void setSecondAttestation(double secondAttestation) {
		this.secondAttestation = secondAttestation;
	}	
	public double getFinalAttestation() {
		return this.getFirstAttestation() + this.getSecondAttestation() + this.getExamMark();
	}
	public void setFinalAttestation(double finalAttestation) {
		this.finalAttestation = finalAttestation;
	}
	public void setExamMark(double examMark) {
		this.examMark = examMark;
	}
	public AttestationType getPeriod() {
		return period;
	}
	public void setPeriod(AttestationType period) {
		this.period = period;
	}
	
	public String convertToLetterMark() {
		String letterMark;
		if (finalAttestation >= 95 && finalAttestation <= 100) letterMark = "A";
		else if (finalAttestation >= 90 && finalAttestation <= 94) letterMark = "A-";
		else if (finalAttestation >= 85 && finalAttestation <= 89) letterMark = "B+";
		else if (finalAttestation >= 80 && finalAttestation <= 84) letterMark = "B";
		else if (finalAttestation >= 75 && finalAttestation <= 79) letterMark = "B-";
		else if (finalAttestation >= 70 && finalAttestation <= 74) letterMark = "C+";
		else if (finalAttestation >= 65 && finalAttestation <= 69) letterMark = "C";
		else if (finalAttestation >= 60 && finalAttestation <= 64) letterMark = "C-";
		else if (finalAttestation >= 55 && finalAttestation <= 59) letterMark = "D+";
		else if (finalAttestation >= 50 && finalAttestation <= 54) letterMark = "D";
		else letterMark = "F";
		
		return letterMark;
	}
	
	public double convertToGPA() {
		String letterMark = this.convertToLetterMark();
		double gpa;
		if (letterMark == "A") gpa = 4.00;
		else if (letterMark == "A-") gpa = 3.67;
		else if (letterMark == "B+") gpa = 3.33;
		else if (letterMark == "B") gpa = 3.00;
		else if (letterMark == "B-") gpa = 2.67;
		else if (letterMark == "C+") gpa = 2.33;
		else if (letterMark == "C") gpa = 2.00;
		else if (letterMark == "C-") gpa = 1.67;
		else if (letterMark == "D+") gpa = 1.33;
		else if (letterMark == "D") gpa = 1.00;
		else gpa = 0;
		
		return gpa;
	}
	@Override
	public String toString() {
		return "Mark [firstAttestation=" + getFirstAttestation() + ", secondAttestation=" + getSecondAttestation() + ", examMark="
				+ examMark + "]";
	}
	
	
		
}