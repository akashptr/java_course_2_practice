package exe5;

public class APIResponseParser {
	/**
	    * Parses the input text and returns a Book instance containing
	    * the parsed data. 
	    * @param response text to be parsed
	    * @return Book instance containing parsed data
	    */
	    public static Book parse(String response) {
	       Book book = new Book();
	       String endRule = "<";
	       
	       String startRule = "<title>";		
	       String title = parse(response, startRule, endRule);
	       book.setTitle(title);
	       
	       // Your code
	       String authorName = parse(response, "<name>", endRule) ;
	       String publicationYear = parse(response, "<original_publication_year", endRule);
	       String averageRating = parse(response, "<average_rating", endRule);
	       String ratingsCount = parse(response, "<ratings_count", endRule);
	       String imageURL = parse(response, "<image_url", endRule);

	        book.setAuthor(authorName);
	        book.setPublicationYear(Integer.parseInt(publicationYear));
	        book.setAverageRating(Double.parseDouble(averageRating));
	        book.setRatingsCount(Integer.parseInt(ratingsCount.replaceAll(",", "")));
	        book.setImageUrl(imageURL);

	       return book;
	    }
	    
	    // write overloaded parse method with the 3 parameters response, startRule, endRule
	    private static String parse(String response, String startRule, String endRule) {
	        int tagIndex = response.indexOf(startRule);
	        if(tagIndex != -1) {
	            String element = response.substring(tagIndex, response.indexOf(endRule, tagIndex + 1));
	            String data = element.split(">")[1];
	            return data;
	        }
	        return "";
	    }
	    public static void main(String[] args) {
	       String response = "<work>" + 
	                               "<id type=\"integer\">2361393</id>" +
	                               "<books_count type=\"integer\">813</books_count>" +
	                               "<ratings_count type=\"integer\">1,16,315</ratings_count>" + 
	                               "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
	                               "<original_publication_year type=\"integer\">1854</original_publication_year>" +
	                               "<original_publication_month type=\"integer\" nil=\"true\"/>" +
	                               "<original_publication_day type=\"integer\" nil=\"true\"/>" +
	                               "<average_rating>3.79</average_rating>" +
	                               "<best_book type=\"Book\">" +
	                                   "<id type=\"integer\">16902</id>" +
	                                   "<title>Walden</title>" + 
	                                   "<author>" +
	                                       "<id type=\"integer\">10264</id>" + 
	                                       "<name>Henry David Thoreau</name>" + 
	                                   "</author>" +
	                                   "<image_url>" + 
	                                       "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
	                                   "</image_url>" +
	                                   "<small_image_url>" + 
	                                       "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
	                                   "</small_image_url>" +
	                               "</best_book>" +
	                           "</work>";
	       
	       Book b1 = APIResponseParser.parse(response);
	       System.out.println(b1.getTitle() + "\n" + b1.getAuthor() + "\n" +
	                            b1.getPublicationYear() + "\n" + b1.getAverageRating() +
	                            "\n" + b1.getRatingsCount() + "\n" + b1.getImageUrl());
	   }
}
