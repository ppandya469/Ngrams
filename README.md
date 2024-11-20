# Ngrams Word Counter

A Java-based implementation of Google's Ngrams word counter, designed to analyze text and generate insights on word sequences (n-grams). This project efficiently processes large datasets, calculates frequencies of n-grams, and provides insights into linguistic patterns and trends. 

---

## Demo

[Watch the Demo](#)  

---

## How It's Made:

**Tech Used:** Java  

### Development Process:
1. **Text Preprocessing:**  
   The input text is cleaned by removing punctuation, converting to lowercase, and tokenizing into words. This ensures consistency in n-gram generation.

2. **N-gram Generation:**  
   The program uses a sliding window algorithm to extract n-grams (sequences of n words) from the text. Users can specify the value of "n" to analyze anything from unigrams to higher-order n-grams.  

3. **Frequency Calculation:**  
   Each n-gram is stored in a data structure (e.g., `HashMap`), with its frequency count updated dynamically during processing. This allows for quick lookup and retrieval of results.  

4. **Performance Optimization:**  
   To handle large datasets efficiently:
   - The program uses buffered input streams for reading files.
   - Multi-threading is implemented to parallelize processing for larger inputs.
   - Memory usage is optimized by reusing objects and using efficient data structures.  

5. **Output:**  
   The results are displayed in an intuitive format, either as:
   - A sorted list of the most frequent n-grams.
   - A custom report for user-defined queries.  

---

## Optimizations

1. **Efficient Data Structures:**  
   - `HashMap` is used for frequency counts, ensuring O(1) average lookup and insertion time.  
   - Optional use of `TreeMap` for maintaining sorted order of n-grams by frequency.  

2. **Multi-threading:**  
   Large datasets are split into chunks processed concurrently by multiple threads, dramatically improving performance for big files.  

3. **Memory Management:**  
   - Implemented lazy loading for datasets to reduce memory consumption.  
   - String pooling and use of `StringBuilder` minimized unnecessary object creation.  

4. **Command-Line Integration:**  
   The program includes command-line options for specifying:
   - Input file path  
   - Value of "n" for n-grams  
   - Output format and file  

---

## Lessons Learned:

1. **Handling Big Data:**  
   Processing large text datasets presented challenges in memory and computation. Optimizing algorithms for efficiency was crucial for achieving reasonable performance.  

2. **Algorithm Design:**  
   Building the sliding window algorithm for n-gram generation taught me the importance of balancing readability and efficiency in code design.  

3. **Concurrency in Java:**  
   Implementing multi-threading highlighted the importance of thread safety and synchronization in shared data structures.  

4. **Real-World Applications:**  
   This project deepened my understanding of n-grams' applications, from language modeling to predictive text and natural language processing.  

5. **Scalability:**  
   Designing for scalability emphasized writing modular, reusable code capable of handling small and large datasets alike.  

This project showcases a strong understanding of Java fundamentals, algorithm optimization, and real-world text analysis techniques. It's an exciting step toward building more complex natural language processing systems!
