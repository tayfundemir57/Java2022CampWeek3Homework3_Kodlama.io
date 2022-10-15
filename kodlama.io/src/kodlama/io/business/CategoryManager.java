package kodlama.io.business;

import java.util.ArrayList;
import java.util.List;

import kodlama.io.core.logging.Logger;
import kodlama.io.dataAccess.CategoryDao;
import kodlama.io.entities.Category;

public class CategoryManager {

	private CategoryDao categoryDao;
	private Logger[] loggers;
	
	List<Category> categories = new  ArrayList<Category>();

	public CategoryManager(CategoryDao categoryDao, Logger[] loggers, List<Category> categories) {
		this.categoryDao = categoryDao;
		this.loggers = loggers;
		this.categories = categories;		

	}

	public void add(Category category) throws Exception {

		for(Category category1 : categories) {
			
			if(category1.getCategoryName()== category.getCategoryName()) {
				throw new Exception("Girilen Kategori İsminde Daha Önce Kategori Açılmış.!.");
			}
		}
		
		
		categoryDao.add(category);
		

		for (Logger logger : loggers) {
			logger.log(category.getCategoryName());
		}
	}

}
