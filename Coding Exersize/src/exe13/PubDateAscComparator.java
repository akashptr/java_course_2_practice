package exe13;

import java.util.Comparator;

public class PubDateAscComparator implements Comparator<Book> {

	@Override
	public int compare(Book o1, Book o2) {
		int diffYear = o1.getYear() - o2.getYear();
		if(diffYear == 0) {
			return o1.compareTo(o2);
		}
		return diffYear;
	}
}