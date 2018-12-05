package br.com.company.interfaces;

import br.com.company.entity.UserEntity;

import java.io.Serializable;

public interface IUserKeyBy extends Serializable {

	public abstract Integer call(UserEntity entity);

}
