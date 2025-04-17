package lib;

public class TaxFunction {

	private static final int PTKP_SINGLE = 54000000;
	private static final int PTKP_MARRIED = 4500000;
	private static final int PTKP_CHILD = 1500000;
	private static final double TAX_RATE = 0.05;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking,
								   int deductible, boolean isSingle, int numberOfChildren) {

		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		int maxChildren = Math.min(numberOfChildren, 3);
		int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
		int nonTaxableIncome = calculateNonTaxableIncome(isSingle, maxChildren);
		int taxableIncome = annualIncome - deductible - nonTaxableIncome;

		return calculateTaxAmount(taxableIncome);
	}

	private static int calculateAnnualIncome(int salary, int additionalIncome, int months) {
		return (salary + additionalIncome) * months;
	}

	private static int calculateNonTaxableIncome(boolean isSingle, int numberOfChildren) {
		int ptkp = PTKP_SINGLE;
		if (!isSingle) {
			ptkp += PTKP_MARRIED + (numberOfChildren * PTKP_CHILD);
		}
		return ptkp;
	}

	private static int calculateTaxAmount(int taxableIncome) {
		if (taxableIncome <= 0) {
			return 0;
		}
		return (int) Math.round(taxableIncome * TAX_RATE);
	}
}
