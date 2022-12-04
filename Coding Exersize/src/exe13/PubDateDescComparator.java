package exe13;

import java.util.Comparator;

public class PubDateDescComparator implements Comparator<Book> {

	@Override
	public int compare(Book o1, Book o2) {
		int diffYear = o2.getYear() - o1.getYear();
		if(diffYear == 0) {
			return o1.compareTo(o2);
		}
		return diffYear;
	}
}