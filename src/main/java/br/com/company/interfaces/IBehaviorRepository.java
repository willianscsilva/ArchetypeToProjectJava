package br.com.company.interfaces;

import br.com.company.entity.UserEntity;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

import java.io.Serializable;

public interface IBehaviorRepository extends Serializable {

	public JavaRDD<UserEntity> getRDD();

	public JavaPairRDD<Integer, UserEntity> getPairRDD(IUserKeyBy I);

}
