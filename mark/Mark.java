package mark;

import java.io.Serializable;

public class Mark implements Serializable {

	private static final long serialVersionUID = 1L;

	private double firstAttestation;
	private double secondAttestation;
	private double examMark;
	private AttestationType period;

	public Mark() {
		this.firstAttestation = 0;
		this.secondAttestation = 0;
		this.examMark = 0;
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
			this.firstAttestation += currentMark;
			if (this.firstAttestation > 30) this.firstAttestation = 30;
		} else if (this.period == AttestationType.SECOND) {
			this.secondAttestation += currentMark;
			if (this.secondAttestation > 30) this.secondAttestation = 30;
		} else {
			this.examMark += currentMark;
			if (this.examMark > 40) this.examMark = 40;
		}
	}

	public double getFirstAttestation() { return firstAttestation; }
	public void setFirstAttestation(double v) { this.firstAttestation = v; }

	public double getSecondAttestation() { return secondAttestation; }
	public void setSecondAttestation(double v) { this.secondAttestation = v; }

	public double getExamMark() { return examMark; }
	public void setExamMark(double v) { this.examMark = v; }

	public double getFinalAttestation() {
		return firstAttestation + secondAttestation + examMark;
	}

	public AttestationType getPeriod() { return period; }
	public void setPeriod(AttestationType period) { this.period = period; }

	public String convertToLetterMark() {
		double total = getFinalAttestation();
		if (total >= 95) return "A";
		if (total >= 90) return "A-";
		if (total >= 85) return "B+";
		if (total >= 80) return "B";
		if (total >= 75) return "B-";
		if (total >= 70) return "C+";
		if (total >= 65) return "C";
		if (total >= 60) return "C-";
		if (total >= 55) return "D+";
		if (total >= 50) return "D";
		return "F";
	}

	public double convertToGPA() {
		switch (convertToLetterMark()) {
			case "A":  return 4.00;
			case "A-": return 3.67;
			case "B+": return 3.33;
			case "B":  return 3.00;
			case "B-": return 2.67;
			case "C+": return 2.33;
			case "C":  return 2.00;
			case "C-": return 1.67;
			case "D+": return 1.33;
			case "D":  return 1.00;
			default:   return 0.00;
		}
	}

	@Override
	public String toString() {
		return "Mark [att1=" + firstAttestation + ", att2=" + secondAttestation
				+ ", exam=" + examMark + ", total=" + getFinalAttestation()
				+ " (" + convertToLetterMark() + ")]";
	}
}
