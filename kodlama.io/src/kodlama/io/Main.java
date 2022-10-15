package kodlama.io;

import java.util.ArrayList;
import java.util.List;

import kodlama.io.business.CategoryManager;
import kodlama.io.business.CourseManager;
import kodlama.io.business.InstructorManager;
import kodlama.io.core.logging.DatabaseLogger;
import kodlama.io.core.logging.FileLogger;
import kodlama.io.core.logging.Logger;
import kodlama.io.core.logging.MailLogger;
import kodlama.io.dataAccess.CategoryDao;
import kodlama.io.dataAccess.HibernateCategoryDao;
import kodlama.io.dataAccess.HibernateCourseDao;
import kodlama.io.dataAccess.JdbcCategoryDao;
import kodlama.io.dataAccess.JdbcCourseDao;
import kodlama.io.dataAccess.JdbcInstructorDao;
import kodlama.io.entities.Category;
import kodlama.io.entities.Course;
import kodlama.io.entities.Instructor;

public class Main {

	public static void main(String[] args) throws Exception {

		Logger[] loggers = { new DatabaseLogger() };
		
		System.out.println("***************Kurs İşlemleri***************");
		System.out.println();
		List<Course> courses = new ArrayList<>(); // İş kurallarınında karşılaştırma yapabilmek için kursları tutacak liste oluşturduk.
		Course course1 = new Course(1, "Yazılım Geliştirici Yetiştirme Kursu- JAVA ",45);
		Course course2 = new Course(2, "Yazılım Geliştirici Yetiştirme Kursu- C# + ANGULAR ", 50);
		Course course3 = new Course(3, "Programlamaya Giriş İçin Temel Kurs", 15);
		courses.add(course1); // Listeye kurs bilgilerini ekliyoruz.
		courses.add(course2);
		courses.add(course3);
		
		
		Course course = new Course(4, "Yazılım Geliştirici Yetiştirme Kursu- JAVA + REACT",50);
		CourseManager courseManager = new CourseManager(new HibernateCourseDao(), loggers, courses);
		courseManager.add(course);	
		
		System.out.println();
		System.out.println("***************Kategori İşlemleri***************");
		
		Logger[] loggers2 = { new FileLogger() };
		List<Category> categories = new ArrayList<>();
		Category category1 = new Category(1,"Programlama");
		Category category2 = new Category(2,"Kişisel Gelişim");
		categories.add(category1);
		categories.add(category2);
		
		Category category = new Category(3,"Sağlık ve Fitness");
		CategoryManager categoryManager = new CategoryManager(new JdbcCategoryDao(), loggers2, categories);
		categoryManager.add(category);
		
		System.out.println();
		System.out.println("***************Eğitmen İşlemleri***************");	
		
		Logger[] loggers3 = { new MailLogger() };
		List<Instructor> instructories = new ArrayList<>();
		Instructor instructor1 = new Instructor("Atil ", "SAMANCIOĞLU");
		instructories.add(instructor1);
		
		Instructor instructor = new Instructor("Engin ", "DEMİROĞ");
		InstructorManager instructorManager = new InstructorManager(new JdbcInstructorDao(), loggers3, instructories);
		instructorManager.add(instructor);
		
		
		
	}
}

// Bir katman başka bir katmanın ( business, dataAccsess vb.) classını kullanıyorken sadece interfacesinden erişim kurmalıdır. ( entities dahil değildir.) 