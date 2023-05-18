package ch.cristiano.demo.externaltask;

import java.util.Random;
import java.util.Collections;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Configuration;

@Configuration
@ExternalTaskSubscription("orchestrate-something")
public class Worker implements ExternalTaskHandler {

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

    // Do the business logic
    System.out.println("Yeah, now you can orchestrate something :-) You could use data from the process variables: "
        + externalTask.getAllVariables());

    // Probably add some process variables
    Random generator = new Random();
    externalTaskService.complete(externalTask,
        Collections.singletonMap("resultValue1", generator.nextInt(100 - 10) + 10));
  }
}
