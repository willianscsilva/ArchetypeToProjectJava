package br.com.company.constants;

import br.com.company.service.PeriodService;

public class
Frequency {

	protected enum FrequencyEnum {
		DAILY {
			@Override
			public String from(String format) {
				return new PeriodService(format).getTimeByHour(-2);
			}

			@Override
			public String to(String format) {
				return new PeriodService(format).getTimeByHour(-2);
			}
		},
		WEEKLY {
			@Override
			public String from(String format) {
				return new PeriodService(format).getTimeByDay(-7);
			}

			@Override
			public String to(String format) {
				return new PeriodService(format).getTimeByDay(-1);
			}
		},
		MONTHLY {
			@Override
			public String from(String format) {
				return new PeriodService(format).getTimeByMonth(-1);
			}

			@Override
			public String to(String format) {
				return new PeriodService(format).getTimeByDay(-1);
			}
		};

		public abstract String from(String format);

		public abstract String to(String format);
	}

	private static final String[] PERIODS = { "DAILY", "WEEKLY", "MONTHLY" };

	/**
	 * @param periodId
	 * @param format
	 * @return
	 */
	public static String fromDate(int periodId, String format) {
		return FrequencyEnum.valueOf(Frequency.PERIODS[periodId - 1]).from(format);
	}

	/**
	 * @param periodId
	 * @param format
	 * @return
	 */
	public static String toDate(int periodId, String format) {
		return FrequencyEnum.valueOf(Frequency.PERIODS[periodId - 1]).to(format);
	}

}
