package br.com.company.repository;

import br.com.company.application.Repository;
import br.com.company.entity.AccountEntity;
import br.com.company.entity.UserEntity;
import br.com.company.interfaces.IBehaviorRepository;
import br.com.company.interfaces.IUserKeyBy;
import br.com.company.spark.SparkContextSingleton;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppRepository extends Repository implements Serializable, IBehaviorRepository {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AppRepository.class);

	private AccountEntity accountEntity;

	public AppRepository(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}

	@Override
	public JavaRDD<UserEntity> getRDD() {
		try {
			List<UserEntity> list = new ArrayList<>();

			UserEntity userEntity = new UserEntity();
			userEntity.setName("Will");
			userEntity.setAge(36);
			list.add(userEntity);

			return SparkContextSingleton.get().parallelize(list);
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			return SparkContextSingleton.get().emptyRDD();
		}
	}

	@Override
	public JavaPairRDD<Integer, UserEntity> getPairRDD(IUserKeyBy I) {
		try {
			return this.getRDD().keyBy(new Function<UserEntity, Integer>() {
				@Override
				public Integer call(UserEntity userEntity) throws Exception {
					return I.call(userEntity);
				}
			});
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public JavaPairRDD<Integer, UserEntity> getPairRDDByAge() {
		return this.getPairRDD(new IUserKeyBy() {
			@Override
			public Integer call(UserEntity entity) {
				return null;
			}
		});
	}

}
