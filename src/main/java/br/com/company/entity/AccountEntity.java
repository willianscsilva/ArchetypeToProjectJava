package br.com.company.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int accountId;

	private int ruleId;

	private String ruleName;

	private int periodId = 1;

	private int interval;

	/**
	 * 
	 * @return
	 */
	public int getAccountId() {
		return this.accountId;
	}

	/**
	 * 
	 * @param accountId
	 * @return
	 */
	public AccountEntity setAccountId(int accountId) {
		this.accountId = accountId;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public int getRuleId() {
		return this.ruleId;
	}

	/**
	 * 
	 * @param ruleId
	 * @return
	 */
	public AccountEntity setRuleId(int ruleId) {
		this.ruleId = ruleId;
		return this;
	}

	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return this.ruleName;
	}

	/**
	 * @param ruleName
	 *            the ruleName to set
	 */
	public AccountEntity setRuleName(String ruleName) {
		this.ruleName = ruleName;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public int getPeriodId() {
		return this.periodId;
	}

	/**
	 * 
	 * @param periodId
	 * @return
	 */
	public AccountEntity setPeriodId(int periodId) {
		this.periodId = periodId;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public int getInterval() {
		return this.interval;
	}

	/**
	 * 
	 * @param interval
	 * @return
	 */
	public AccountEntity setInterval(int interval) {
		this.interval = interval;
		return this;
	}
	
}
