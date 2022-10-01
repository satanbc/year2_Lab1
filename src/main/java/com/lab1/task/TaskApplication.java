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

			Book tempBook1 = new Book("Harry Potter and the Philosopher's Stone","1997","223","fantasy novel written by British author J. K. Rowling");
			Book tempBook2 = new Book("Harry Potter and the Chamber of Secrets","1999","480","fantasy novel written by British author J. K. Rowling and the second novel in the Harry Potter series");

			tempAuthor.addBook(tempBook1);
			tempBook1.setAuthor(tempAuthor);
			tempAuthor.addBook(tempBook2);
			tempBook2.setAuthor(tempAuthor);

			Character tempCharacter1 = new Character("Harry Potter","main");
			Character tempCharacter2 = new Character("Ron Weasley","secondary");

			tempBook1.addCharacter(tempCharacter1);
			tempBook1.addCharacter(tempCharacter2);

			tempBook2.addCharacter(tempCharacter1);
			tempBook2.addCharacter(tempCharacter2);

			if (tempBook1.getCharacters().get(0).getRole().equals(tempBook2.getCharacters().get(0).getRole()) && (tempBook1.getCharacters().get(0).getRole().equals("main") || tempBook1.getCharacters().get(0).getRole().equals("secondary"))){
				tempSeries.addBook(tempBook1);
				tempSeries.addBook(tempBook2);

				tempBook1.setSeries(tempSeries);
				tempBook2.setSeries(tempSeries);
			}

			session.save(tempBook1);
			session.save(tempBook2);

			session.save(tempCharacter1);
			session.save(tempCharacter2);

			session.getTransaction().commit();

			System.out.println("Done!");
		}
		finally {

			session.close();

			factory.close();
		}
	}
	}
