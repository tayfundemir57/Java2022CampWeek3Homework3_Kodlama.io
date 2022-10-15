package kodlama.io.business;

import java.util.ArrayList;
import java.util.List;

import kodlama.io.core.logging.Logger;
import kodlama.io.dataAccess.InstructorDao;
import kodlama.io.entities.Instructor;

public class InstructorManager {
	private InstructorDao instructorDao;
	private Logger[] loggers;
	List<Instructor> instructors = new ArrayList<Instructor>();

	public InstructorManager(InstructorDao instructorDao, Logger[] loggers, List<Instructor> instructors) {
		this.instructorDao = instructorDao;
		this.loggers = loggers;
		this.instructors = instructors;
	}

	public void add(Instructor instructor) {

		instructorDao.add(instructor);

		for (Logger logger : loggers) {
			logger.log(instructor.getFirstName()+" "+ instructor.getLastName());
		}

	}
}
