package br.com.company.model;

import br.com.company.application.Model;
import br.com.company.entity.UserEntity;
import br.com.company.interfaces.IModelFactory;
import br.com.company.multichannel.Context;
import br.com.company.repository.AppRepository;
import org.apache.spark.api.java.JavaRDD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class AppModel extends Model implements Serializable, IModelFactory {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AppModel.class);

	private JavaRDD<UserEntity> data;

	public AppModel(Context context) {
		this.context = context;
		this.data = this.getData();
	}

	/**
	 * 
	 * @return
	 */
	private JavaRDD<UserEntity> getData() {

		return (new AppRepository(this.context.getAccount())).getRDD();
	};

	@Override
	public void readData() {
		try {
			this.data.foreach(row -> {
				System.out.println(
						"Name: " + row.getName()
						         + " <==> " +
						"Age: "  + row.getAge()
				);
			});
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void cleaning() {
		this.data = null;
	}

}
