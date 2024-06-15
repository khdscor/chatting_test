package chatting.chatting.springBatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class ExampleJobConfig {
    @Bean
    public Job simpleJob1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("simpleJob", jobRepository)
            .start(simpleStep1(jobRepository, platformTransactionManager))
            .next(simpleStep2(jobRepository, platformTransactionManager))
            .next(simpleStep3(jobRepository, platformTransactionManager))
            .build();
    }
    @Bean
    public Step simpleStep1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("simpleStep1", jobRepository)
            .tasklet(testTasklet1(), platformTransactionManager).build();
    }

    @Bean
    public Step simpleStep2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("simpleStep2", jobRepository)
            .tasklet(testTasklet2(), platformTransactionManager).build();
    }

    @Bean
    public Step simpleStep3(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("simpleStep3", jobRepository)
            .tasklet(new TestTasklet3(), platformTransactionManager).build();
    }

    @Bean
    public Tasklet testTasklet1(){
        return ((contribution, chunkContext) -> {
            log.info(">>>>> This is Step1");
            return RepeatStatus.FINISHED;
        });
    }

    @Bean
    public Tasklet testTasklet2(){
        return ((contribution, chunkContext) -> {
            log.info(">>>>> This is Step2");
            return RepeatStatus.FINISHED;
        });
    }
}
