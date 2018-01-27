# DBLP Query Engine

DBLP is a computer science bibliography website. Starting in 1993 with a few HTML files, it has grown to become a reference for bibliographic information on major computer science publications. DBLP listed more than 3.66 million journal articles, conference papers, and other publications on computer science in July 2016, up from about 14,000 in 1995. All important journals on computer science are tracked. Proceedings papers of many conferences are also tracked. [Wikipedia]

This project is a GUI search engine which retrieves required information from the [DBLP dataset](http://dblp.org/xml/release/). The files are in XML format of [this](http://dblp.uni-trier.de/faq/What+do+I+find+in+dblp+xml) form.

### Functionalities implemented
  * Parse the XML file using appropriate parsing techniques.
  * Retrieve and display the results of the given queries on this dataset. 
  * Display the result count along with the data.
  * Perform Entity resolution. It simply means finding papers that belong to the same Author (entity) where the author might have different names. For e.g. In research papers, sometimes an author uses his/her full name and sometimes his/her initials (or might have a different middle name). The task of entity resolution is to identify both to be the same author. During search, both the papers should be returned.
  * Create a GUI using Java swing libraries to input the query parameters to the program.
  * If the data entered is invalid show appropriate message on the GUI itself.
  * If no results are returned, display the message in the result box.
  * Result box displays the first 20 results and gives user an option (button) to see next 20 results and so on.
  * Appropriate checks for values in the text boxes is present in the implementation and user is prompted for such values.
  
  Contributors: 
  Y S Ramya ([@meriki](https://github.com/meriki/))
  Manik Arora ([@maniaxiom](https://github.com/maniaxiom))
