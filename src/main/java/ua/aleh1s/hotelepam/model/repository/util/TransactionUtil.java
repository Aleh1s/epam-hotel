package ua.aleh1s.hotelepam.model.repository.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.transaction.Transaction;
import ua.aleh1s.hotelepam.transaction.exception.TransactionException;

import java.util.Optional;

public class TransactionUtil {

    private static final Logger log = LogManager.getLogger(TransactionUtil.class);

    public static <K, E, D extends SimpleDao<K, E>> Optional<E> runInsideTransactionWithReturn(
            DaoActionWithReturn<E, D> daoActionWithReturn, D dao) {
        E entity = null;
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                entity = daoActionWithReturn.execute(dao);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
            }
        } catch (TransactionException e) {
            log.error(e.getMessage(), e);
        }
        return Optional.ofNullable(entity);
    }
}
