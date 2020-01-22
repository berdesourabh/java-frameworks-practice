package com.batchdemo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@EnableBatchProcessing
@SpringBootApplication
public class BatchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchDemoApplication.class, args);
	}

}

//Configuration if Batch service
@Configuration
class BatchConfig {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Autowired
	DemoTasklet demoTasklet;

	@Autowired
	DemoReader reader;

	@Autowired
	DemoProcessor demoProcessor;

	@Autowired
	DemoWriter writer;

	//Registering Job
	//Incrementer: Created Id for job in table
	//flow: takes Step
	@Bean
	public Job demoJob() {
		return jobBuilderFactory.get("demo-job").incrementer(new RunIdIncrementer()).flow(demoTaskletStep()).next(secondStep()).end().build();
	}

	//Creating Step using stepbuilder
	@Bean
	public Step demoTaskletStep() {
		return stepBuilderFactory.get("demoTaskletStep").tasklet(demoTasklet).build();
	}

	@Bean
	public Step secondStep() {
		return stepBuilderFactory.get("secondStep").
				<Demo, Demo>chunk(2).reader(reader).processor(demoProcessor).writer(writer).build();
	}
 }

@Component
class DemoTasklet implements Tasklet {

	 @Override
	 public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		 System.out.println("Tasklet executed");
		 return RepeatStatus.FINISHED;
	 }
 }

 class Demo {
	private int id;
	private String name;

	 public int getId() {
		 return id;
	 }

	 public void setId(int id) {
		 this.id = id;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public Demo(int id, String name) {
		 this.id = id;
		 this.name = name;
	 }

	 @Override
	 public String toString() {
		 return "Demo{" +
				 "id=" + id +
				 ", name='" + name + '\'' +
				 '}';
	 }
 }

 @Component
 @StepScope
 class DemoReader implements ItemReader<Demo> {

	Stack<Demo> demos = new Stack<>();

	@PostConstruct
	public void initReader() {
		demos.add(new Demo(1, "demo"));
		demos.add(new Demo(2, "demo1"));
		demos.add(new Demo(3, "demo2"));
	}
	 @Override
	 public Demo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(demos.isEmpty()) {
			return null;
		}
		return demos.pop();
	 }
 }

 @Component
 class DemoProcessor implements ItemProcessor<Demo, Demo> {

	 @Override
	 public Demo process(Demo demo) throws Exception {
		 System.out.println(demo);
	 	return demo;
	 }
 }

@Component
class DemoWriter implements ItemWriter<Demo> {

	@Override
	public void write(List<? extends Demo> list) throws Exception {
		System.out.println(list);
	}
}