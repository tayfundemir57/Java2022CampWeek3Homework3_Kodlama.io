package kodlama.io.dataAccess;

import kodlama.io.entities.Instructor;

public class HibernateInstructorDao implements InstructorDao{

	@Override
	public void add(Instructor instructor) {
		System.out.println("HIBERNATE ile veritabanına eklendi");
		
	}

}
