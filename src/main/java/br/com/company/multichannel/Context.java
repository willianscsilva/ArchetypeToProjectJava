package br.com.company.multichannel;

import br.com.company.entity.AccountEntity;

import java.io.Serializable;

public class Context implements Serializable {

	private static final long serialVersionUID = 1L;

	private AccountEntity accountEntity;

	/**
	 * 
	 * @param accountEntity
	 */
	public Context(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}

	/**
	 * 
	 * @return
	 */
	public AccountEntity getAccount() {
		return this.accountEntity;
	}

}
