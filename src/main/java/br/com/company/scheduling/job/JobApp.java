package br.com.company.scheduling.job;

import br.com.company.application.Worker;
import br.com.company.entity.AccountEntity;
import br.com.company.model.AppModel;
import org.quartz.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class JobApp implements Job, Serializable, JobInterface {
	private static final long serialVersionUID = 1L;

	int THREAD_NUMBER = 4;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		this.JobAsync();
	}

	@Override
	public void JobAsync() {
		try {
			ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);

			HashMap<String, Object> map = new HashMap<>();
			List<HashMap<String, Object>> list = new ArrayList<>();

			map.put("id", 123456);
			map.put("ruleId", 1);
			map.put("ruleName", "test");

			list.add(map);

			Iterator<HashMap<String, Object>> queue = list.iterator();

			while (queue.hasNext()) {

				HashMap<String, Object> hashMap = queue.next();

				AccountEntity accountEntity = new AccountEntity();
				accountEntity.setAccountId(Integer.valueOf(hashMap.get("id").toString()))
						.setRuleId(Integer.valueOf(hashMap.get("ruleId").toString()))
						.setRuleName(hashMap.get("ruleId").toString());

				executor.submit(() -> {
					Worker worker = new Worker(accountEntity);
					worker.process(AppModel.class);
				});

			}
			executor.shutdown();

			while (!executor.isTerminated()) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}