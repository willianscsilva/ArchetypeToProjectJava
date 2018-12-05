package br.com.company.multichannel;

import br.com.company.entity.AccountEntity;
import br.com.company.interfaces.IModelFactory;

import java.io.Serializable;

public class ModelFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	public ModelFactory(IModelFactory factory, AccountEntity entity) {
		factory.readData();
		System.out.println("Finalized Application");
		factory.cleaning();		
		System.gc();
		System.runFinalization();
	}

}
