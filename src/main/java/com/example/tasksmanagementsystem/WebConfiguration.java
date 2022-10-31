package com.example.tasksmanagementsystem;

import com.example.tasksmanagementsystem.model.Student;
import com.example.tasksmanagementsystem.model.Task;
import com.example.tasksmanagementsystem.repository.StudentRepository;
import com.example.tasksmanagementsystem.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WebConfiguration {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository, TaskRepository repository2) {
		return args -> {
			Student rinzler = new Student(
				"Rinzler",
				"rinler@gmail.com",
				123421324L,
//					LocalDate.of(2000, Month.JANUARY, 5),
				"asdsadsad",
				"asdasdsad"
			);

			Student alex = new Student(
				"Alex",
				"alex@gmail.com",
				1975325L,
//					LocalDate.of(2000, Month.JANUARY, 5),
				"asdsadsad",
				"asdasdsad"
			);
			Task taskOne = new Task(
				"Task One",
				"Testing command line",
				rinzler
			);
			Task taskTwo = new Task(
				"Task 2",
				"Must work",
				rinzler
			);
//			repository2.saveAll(List.of(taskOne, taskTwo));
			repository.saveAll(List.of(rinzler, alex));
		};
	}

}
