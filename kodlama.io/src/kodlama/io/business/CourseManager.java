package kodlama.io.business;

import java.util.List;

import kodlama.io.core.logging.Logger;
import kodlama.io.dataAccess.CourseDao;
import kodlama.io.entities.Course;

public class CourseManager {
	private CourseDao courseDao;
	private Logger[] loggers;
	private List<Course> courses;

	public CourseManager(CourseDao courseDao, Logger[] loggers, List<Course> courses) {
		this.courseDao = courseDao;
		this.loggers = loggers;
		this.courses = courses;

	}

	public void add(Course course) throws Exception {

		for (Course course1 : courses) {

			if (course1.getCourseName() == course.getCourseName()) {
				throw new Exception("Aynı ürün bir daha girilemez");
			}else if (course.getCoursePrice() <= 0) {
				throw new Exception("Ürün fiyatı 0 dan küçük ve eşit olamaz.");
			}
		}
		
		courses.add(course); // Listeye kurs ekleme
		courseDao.add(course); // Veri tabanına ekleme

		for (Logger logger : loggers) {
			logger.log(course.getCourseName());
		}

	}
}