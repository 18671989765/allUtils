package com.util.allutil.spring.transaction;

import com.util.allutil.utils.SpringUtils;
import com.util.allutil.utils.ThreadPoolUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.concurrent.ExecutorService;

/**
 * 处理thread中事务处理
 */
public class ThreadTransaction {

    /**
     * 实现步骤：
     * 1：准备手动一个线程池开启一个线程
     * 2：手动处理数据库事务，保证数据的一致性
     */
    public void threadThranscaction() {
        //自定义线程池

            ExecutorService executorServiceInstance = ThreadPoolUtils.getExecutorServiceInstance();


    }


    /**
     * 开启一个线程
     */
    class ThreadDataSourceUpdate extends  Thread{
        @Override
        public void run() {
            //手动处理数据库事务，遇到异常则回滚
            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            //如果存在事务则支持当前事务，没有事务则开启一个事务
            defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

            PlatformTransactionManager transactionManager = SpringUtils.getBean(PlatformTransactionManager.class);
            TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
            try {

                //执行业务逻辑
                System.out.println("=========数据业务处理开始");


                System.out.println("=========数据处理完成");

                //提交事务
                transactionManager.commit(status);
            }catch (Exception e){
                e.printStackTrace();
                //事务回滚
                transactionManager.rollback(status);
            }

        }
    }

}
