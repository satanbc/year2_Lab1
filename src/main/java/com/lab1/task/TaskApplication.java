package com.lab1.task;

import com.lab1.task.Entities.Author;
import com.lab1.task.Entities.Book;
import com.lab1.task.Entities.Character;
import com.lab1.task.Entities.Series;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Author.class)
				.addAnnotatedClass(Series.class)
				.addAnnotatedClass(Book.class)
				.addAnnotatedClass(Character.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			Author tempAuthor = new Author("Joanne Rowling", "31.07.1965");

			System.out.println("\nSaving the author ...");
			session.save(tempAuthor);
			System.out.println("Saved the author: " + tempAuthor);


			Series tempSeries = new Series("Harry Potter");

			tempAuthor.addSeries(tempSeries);
			tempSeries.setAuthor(tempAuthor);

			System.out.println("\nSaving series ...");
			session.save(tempSeries);
			System.out.println("Saved series: " + tempAuthor.getSeries());


			Book tempBook = new Book("Harry Potter and the Philosopher's Stone","1997","223","fantasy novel written by British author J. K. Rowling");

			tempAuthor.addBook(tempBook);
			tempBook.setAuthor(tempAuthor);

			tempSeries.addBook(tempBook);
			tempBook.setSeries(tempSeries);

			session.save(tempBook);


			Character tempCharacter1 = new Character("Harry Potter","main");
			Character tempCharacter2 = new Character("Ron Weasley","secondary");

			tempCharacter1.setSeries(tempSeries);
			tempCharacter2.setSeries(tempSeries);

			tempSeries.addCharacter(tempCharacter1);
			tempSeries.addCharacter(tempCharacter2);

			tempBook.addCharacter(tempCharacter1);
			tempBook.addCharacter(tempCharacter2);

			session.save(tempCharacter1);
			session.save(tempCharacter2);

			session.getTransaction().commit();

			System.out.println("Done!");
		}
		finally {

			// add clean up code
			session.close();

			factory.close();
		}
	}
	}
