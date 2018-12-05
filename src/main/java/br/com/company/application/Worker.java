package br.com.company.application;

import br.com.company.entity.AccountEntity;
import br.com.company.interfaces.IModelFactory;
import br.com.company.multichannel.Context;
import br.com.company.multichannel.ModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Worker extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(Worker.class);

    protected AccountEntity accountEntity;
    private Context context;

    public Worker(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
        this.context = new Context(accountEntity);
    }

    public <T extends Model & IModelFactory> void process(Class<T> clazz) {
        try {
            T model = clazz.getConstructor(Context.class).newInstance(this.context);
            new ModelFactory(model, this.accountEntity);
        } catch (Exception e) {
            LOG.error("ERROR INSTANTIATING CLASS", e.getLocalizedMessage(), e);
        }
    }
}
